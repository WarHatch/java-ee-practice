package persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import entities.Hero;
import entities.ISkill;

@ApplicationScoped
public class SkillsDAO {
    @PersistenceContext
    private EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<ISkill> loadAll() {
        return em.createNamedQuery("Skill.findAll", ISkill.class).getResultList();
    }

    public ISkill loadOne(int skillId) {
        return em.createNamedQuery("Skill.findById", ISkill.class)
                .setParameter("skillId", skillId)
                .getSingleResult();
    }

    public List<Hero> getSkillHeroes(int skillId) {
        return em.createNamedQuery("Hero.findAllBySkillId", Hero.class)
                .setParameter("skillId", skillId)
                .getResultList();
    }

    public void persist(ISkill skill) {
        this.em.persist(skill);
    }

    public void merge(ISkill skill) {
        this.em.merge(skill);
    }
}
