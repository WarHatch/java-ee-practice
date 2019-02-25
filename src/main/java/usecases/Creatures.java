package usecases;

import entities.Creature;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Model
public class Creatures implements Serializable {

    private List<Creature> allCreatures;
    @PostConstruct
    public void init(){
        loadCreatures();
    }

    public void loadCreatures() {
        // TODO this is a mock implementation - later we will connect it to real data store
        List<Creature> creatures = new ArrayList<Creature>();
        creatures.add(new Creature("Jordan"));
        creatures.add(new Creature("Kobe"));
        this.allCreatures = creatures;
    }

    public List<Creature> getAllCreatures(){
        return allCreatures;
    }
}