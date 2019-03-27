package usecases.cdi.jpa;

import asynchronous.AsyncComponent;
import entities.Creature;
import entities.Stat;
import persistence.CreaturesDAO;
import persistence.StatsDAO;

import lombok.Getter;
import javax.annotation.PostConstruct;

import org.omnifaces.cdi.ViewScoped;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Named
@ViewScoped //Application scope would allow to see preloaded asyncCreatureCount
public class Creatures implements Serializable {

    @Inject
    private CreaturesDAO creaturesDAO;
    @Inject
    private StatsDAO statDAO;
    @Inject
    private AsyncComponent asyncComponent;

    @Getter
    private List<Creature> allCreatures;

    private Future<String> asyncCreatureCount;

    @Getter
    private Creature creatureToCreate = new Creature();
    @Getter
    private Stat statToCreate = new Stat();

    @PostConstruct
    public void init(){
        loadCreatures();
        try {
            getAsyncCreatureCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadCreatures() {
        this.allCreatures = creaturesDAO.loadAll();
    }

    public String getAsyncCreatureCount() throws ExecutionException, InterruptedException {
        if (asyncCreatureCount == null) {
            asyncCreatureCount = asyncComponent.asyncGetCreatureCount();
            return "I just have called AsyncComponent. Result is ready? " + asyncCreatureCount.isDone();
        } else {
            if (asyncCreatureCount.isDone()) {
                String result = asyncCreatureCount.get();
                asyncCreatureCount = null;
                return "Result is loaded, and it is: " + result;
            } else {
                return "Result is not yet ready... please wait a moment...";
            }
        }
    }

    public List<Stat> getFreshCreatureStats(int creatureId) {
        return creaturesDAO.loadCreatureStats(creatureId);
    }

    @Transactional
    public String createCreature(){
        this.creaturesDAO.persist(creatureToCreate);
        return "success";
    }

    @Transactional
    public String createStat() {
        this.statDAO.persist(statToCreate);
        return "success";
    }
}