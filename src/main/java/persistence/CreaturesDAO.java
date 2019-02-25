package persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import entities.Creature;

@ApplicationScoped
public class CreaturesDAO {
    @PersistenceContext
    private EntityManager em;

    public List<Creature> loadAll() {
        return em.createNamedQuery("Creature.findAll", Creature.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Creature creature) {
        this.em.persist(creature);
    }
}