package persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import entities.Creature;
import entities.Stat;

@ApplicationScoped
public class StatsDAO {
    @PersistenceContext
    private EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Stat> loadAll() {
        return em.createNamedQuery("Stat.findAll", Stat.class).getResultList();
    }

    public void persist(Stat stat) {
        this.em.persist(stat);
    }
}
