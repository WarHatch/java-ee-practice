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

    public Stat loadById(int id) {
        return em.createNamedQuery("Stat.findById", Stat.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public void persist(Stat stat) {
        this.em.persist(stat);
    }

    public void updateAndFlush(Stat stat) {
        em.merge(stat);
        em.flush();
    }
}
