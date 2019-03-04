package usecases;

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
    private int heroToMapToId;

    @Getter
    private List<Skill> allSkills;

    @PostConstruct
    public void init(){
        loadSkills();
    }

    public void loadSkills() {
        this.allSkills = skillsDAO.loadAll();
    }

    public void mapSkillToHero() {
        Hero hero = heroes.getHero(this.heroToMapToId);
        skillToCreate.addHero(hero);
    }

    @Transactional
    public String createSkill(){
        // Hero must use GenerationType.IDENTITY to ensure id starts from 1
        if (heroToMapToId != 0)
            mapSkillToHero();
        this.skillsDAO.persist(skillToCreate);
        return "success";
    }
}
