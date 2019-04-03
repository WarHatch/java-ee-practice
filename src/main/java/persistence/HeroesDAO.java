package persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import entities.Hero;
import entities.ISkill;

@ApplicationScoped
public class HeroesDAO {
    @PersistenceContext
    private EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Hero> loadAll() {
        return em.createNamedQuery("Hero.findAll", Hero.class).getResultList();
    }

    public Hero loadOne(int heroId) {
        return em.createNamedQuery("Hero.findById", Hero.class)
            .setParameter("heroId", heroId)
            .getSingleResult();
    }

    public List<ISkill> getHeroSkills(int heroId) {
        List<ISkill> results = em.createNamedQuery("Skill.findAllByHeroId", ISkill.class)
                .setParameter("heroId", heroId)
                .getResultList();
        return results;
    }

    public void persist(Hero hero) {
        this.em.persist(hero);
    }
}