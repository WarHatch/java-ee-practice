package usecases.cdi;

import entities.Skill;
import lombok.Getter;
import persistence.SkillsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Model
public class Skills implements Serializable {

    @Inject
    private SkillsDAO skillsDAO;

    @Getter
    private List<Skill> allSkills;

    @PostConstruct
    public void init(){
        loadSkills();
    }

    public void loadSkills() {
        this.allSkills = skillsDAO.loadAll();
    }
}