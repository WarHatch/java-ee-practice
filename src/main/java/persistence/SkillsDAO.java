package persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import entities.Hero;
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

    public Skill loadOne(int skillId) {
        return em.createNamedQuery("Skill.findById", Skill.class)
                .setParameter("skillId", skillId)
                .getSingleResult();
    }

    public List<Hero> getSkillHeroes(int skillId) {
        return em.createNamedQuery("Hero.findAllBySkillId", Hero.class)
                .setParameter("skillId", skillId)
                .getResultList();
    }

    public void persist(Skill skill) {
        this.em.persist(skill);
    }

    public void merge(Skill skill) {
        this.em.merge(skill);
    }
}
