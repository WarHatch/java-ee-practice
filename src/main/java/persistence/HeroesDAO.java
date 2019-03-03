package persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import entities.Hero;
import entities.Skill;

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

    public List<Skill> getHeroSkills(int heroId) {
        List<Skill> results = em.createNamedQuery("Skill.findAllByHeroId", Skill.class)
                .setParameter("heroId", heroId)
                .getResultList();
        return results;
    }

    public void persist(Hero hero) {
        this.em.persist(hero);
    }
}