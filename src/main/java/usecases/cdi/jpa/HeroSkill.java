package usecases.cdi.jpa;

import entities.Hero;
import entities.ISkill;
import entities.Skill;
import interceptors.Logged;
import persistence.HeroesDAO;
import persistence.SkillsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

import lombok.Getter;

@Model //RequestScoped and Named
public class HeroSkill {

    @Inject
    private SkillsDAO skillsDAO;
    @Inject
    private HeroesDAO heroesDAO;

    @Inject
    @Getter
    private ISkill skill;
    @Getter
    private Hero hero = new Hero();

    @Getter
    private List<Hero> heroesOfSkill;

    @PostConstruct
    public void init(){
        loadHeroesOfSkill();
    }

    public void loadHeroesOfSkill(){
        this.heroesOfSkill = skillsDAO.getSkillHeroes(this.skill.getId());
    }

    @Logged
    @Transactional
    public void mapHeroToSkill() {
        Hero hero = heroesDAO.loadOne(this.hero.getId());
        ISkill skill = skillsDAO.loadOne(this.skill.getId());
        skill.addHero(hero);
        skillsDAO.merge(skill);
    }

    @Logged
    @Transactional
    public String createSkillOfHero(){
        // Hero must use GenerationType.IDENTITY to ensure id starts from 1
        if (this.hero.getId() != 0){
            Hero hero = heroesDAO.loadOne(this.hero.getId());
            this.skill.addHero(hero);
        }
        this.skillsDAO.persist(this.skill);
        return "success";
    }

}
