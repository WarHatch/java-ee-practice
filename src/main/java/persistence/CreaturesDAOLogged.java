package persistence;

import entities.Creature;
import entities.Stat;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import java.util.List;

@ApplicationScoped
@Specializes
public class CreaturesDAOLogged extends CreaturesDAO {

    @Override
    public List<Creature> loadAll() {
        System.out.println(this.em.toString() + " > Called loadAll");
        return em.createNamedQuery("Creature.findAll", Creature.class).getResultList();
    }

    @Override
    public List<Stat> loadCreatureStats(int creatureId) {
        System.out.println(this.em.toString() + " > Called loadCreatureStats(id:" + creatureId + ")");
        List<Stat> results = em.createNamedQuery("Stat.findAllByCreatureId", Stat.class)
                .setParameter("creatureId", creatureId)
                .getResultList();
        return results;
    }
}