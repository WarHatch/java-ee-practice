package usecases.cdi.jpa;

import entities.Hero;
import entities.ISkill;
import lombok.Getter;
import lombok.Setter;
import persistence.HeroesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Model
public class Heroes implements Serializable {

    @Inject
    private HeroesDAO heroesDAO;

    @Getter
    @Setter
    private Hero heroToCreate = new Hero();

    @Getter
    @Setter
    private int heroIdToMapTo;

    @Getter
    private List<Hero> allHeroes;

    @PostConstruct
    public void init(){
        loadHeroes();
    }

    public void loadHeroes() {
        this.allHeroes = heroesDAO.loadAll();
    }

    public Hero getHero(int heroId) { return heroesDAO.loadOne(heroId); }

    public List<ISkill> getHeroSkills(int heroId) {
        return heroesDAO.getHeroSkills(heroId);
    }

    @Transactional
    public String createHero(){
        this.heroesDAO.persist(heroToCreate);
        return "success";
    }
}
