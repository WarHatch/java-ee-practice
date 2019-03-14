package usecases.cdi.dao;

import entities.Hero;
import entities.Skill;
import persistence.SkillsDAO;

import lombok.Getter;
import lombok.Setter;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Model
public class Skills implements Serializable {

    @Inject
    private SkillsDAO skillsDAO;

    // use HeroesDAO instead?
    @Inject
    private Heroes heroes;

    @Getter
    @Setter
    private Skill skillToCreate = new Skill();

    @Getter
    @Setter
    private int heroIdToMapTo;

    @Getter
    @Setter
    private int skillIdToMap;

    @Getter
    @Setter
    private int heroesSearchSkillId;

    @Getter
    private List<Skill> allSkills;

    @Getter
    private List<Hero> skillHeroes;

    @PostConstruct
    public void init(){
        loadSkills();
        loadSkillHeroes();
    }

    private Skill getSkillById(int id) {
        for(Skill sk : allSkills){
            if(sk.getId() == id)
                return sk;
        }
        return null;
    }

    public void loadSkills() {
        this.allSkills = skillsDAO.loadAll();
    }

    public void loadSkillHeroes(){
        this.skillHeroes = skillsDAO.getSkillHeroes(this.heroesSearchSkillId);
    }

    @Transactional
    public void mapSkillToHero() {
        Hero hero = heroes.getHero(this.heroIdToMapTo);
        skillToCreate.addHero(hero);
        this.skillsDAO.merge(skillToCreate);
    }

    //FIXME: prevent duplicates on 'HERO_SKILL' table
    @Transactional
    public void mapHeroToSkill() {
        Hero hero = heroes.getHero(this.heroIdToMapTo);
        Skill skill = this.getSkillById(this.skillIdToMap);
        skill.addHero(hero);
        this.skillsDAO.merge(skill);
    }

    @Transactional
    public String createSkill(){
        // Hero must use GenerationType.IDENTITY to ensure id starts from 1
        if (heroIdToMapTo != 0){
            Hero hero = heroes.getHero(this.heroIdToMapTo);
            skillToCreate.addHero(hero);
        }
        this.skillsDAO.persist(skillToCreate);
        return "success";
    }
}
