package usecases.cdi.jpa;

import entities.Hero;
import entities.Skill;
import persistence.HeroesDAO;
import persistence.SkillsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

import lombok.Getter;

@Model //RequestScoped
public class HeroSkill {

    @Inject
    private SkillsDAO skillsDAO;
    @Inject
    private HeroesDAO heroesDAO;

    @Getter
    private Hero hero = new Hero();
    @Getter
    private Skill skill = new Skill();

    @Getter
    private List<Hero> heroesOfSkill;

    @PostConstruct
    public void init(){
        loadHeroesOfSkill();
    }

    public void loadHeroesOfSkill(){
        this.heroesOfSkill = skillsDAO.getSkillHeroes(this.skill.getId());
    }

    @Transactional
    public void mapHeroToSkill() {
        Hero hero = heroesDAO.loadOne(this.hero.getId());
        Skill skill = skillsDAO.loadOne(this.skill.getId());
        skill.addHero(hero);
        skillsDAO.merge(skill);
    }

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
