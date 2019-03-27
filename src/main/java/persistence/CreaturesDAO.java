package persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import entities.Creature;
import entities.Stat;

@ApplicationScoped
public class CreaturesDAO {
    @PersistenceContext
    protected EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Creature> loadAll() {
        return em.createNamedQuery("Creature.findAll", Creature.class).getResultList();
    }

    public List<Stat> loadCreatureStats(int creatureId) {
        List<Stat> results = em.createNamedQuery("Stat.findAllByCreatureId", Stat.class)
                .setParameter("creatureId", creatureId)
                .getResultList();
        return results;
    }

    public void persist(Creature creature) {
        this.em.persist(creature);
    }

    public void merge(Creature creature) {
        this.em.merge(creature);
    }
}