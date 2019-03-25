package usecases.cdi.jpa;

import entities.Creature;
import entities.Stat;
import lombok.Getter;
import persistence.CreaturesDAO;
import persistence.StatsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Model
public class Creatures implements Serializable {

    @Inject
    private CreaturesDAO creaturesDAO;
    @Inject
    private StatsDAO statDAO;

    @Getter
    private List<Creature> allCreatures;

    @Getter
    private Creature creatureToCreate = new Creature();
    @Getter
    private Stat statToCreate = new Stat();

    @PostConstruct
    public void init(){
        loadCreatures();
    }

    public void loadCreatures() {
        this.allCreatures = creaturesDAO.loadAll();
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