package usecases.cdi.jpa;

import entities.ISkill;
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
    private List<ISkill> allSkills;

    @PostConstruct
    public void init(){
        loadSkills();
    }

    public void loadSkills() {
        this.allSkills = skillsDAO.loadAll();
    }
}