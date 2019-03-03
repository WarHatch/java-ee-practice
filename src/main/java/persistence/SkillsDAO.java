package persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import entities.Skill;

@ApplicationScoped
public class SkillsDAO {
    @PersistenceContext
    private EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Skill> loadAll() {
        return em.createNamedQuery("Skill.findAll", Skill.class).getResultList();
    }

    public void persist(Skill skill) {
        this.em.persist(skill);
    }
}
