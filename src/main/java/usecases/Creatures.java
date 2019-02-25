package usecases;

import entities.Creature;
import lombok.Getter;
import lombok.Setter;
import persistence.CreaturesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Model
public class Creatures implements Serializable {

    @Inject
    private CreaturesDAO creaturesDAO;

    @Getter
    @Setter
    private Creature creatureToCreate = new Creature();

    private List<Creature> allCreatures;
    @PostConstruct
    public void init(){
        loadCreatures();
    }

    public void loadCreatures() {
        // TODO this is a mock implementation - later we will connect it to real data store
        this.allCreatures = creaturesDAO.loadAll();
    }

    public List<Creature> getAllCreatures(){
        return allCreatures;
    }

    @Transactional
    public String createCreature(){
        this.creaturesDAO.persist(creatureToCreate);
        return "success";
    }
}