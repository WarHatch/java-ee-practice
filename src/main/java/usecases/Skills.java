package usecases;

import entities.Skill;
import lombok.Getter;
import lombok.Setter;
import persistence.SkillsDAO;

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

    @Getter
    @Setter
    private Skill skillToCreate = new Skill();

    @Getter
    private List<Skill> allSkills;

    @PostConstruct
    public void init(){
        loadSkills();
    }

    public void loadSkills() {
        this.allSkills = skillsDAO.loadAll();
    }

    @Transactional
    public String createSkill(){
        this.skillsDAO.persist(skillToCreate);
        return "success";
    }
}
