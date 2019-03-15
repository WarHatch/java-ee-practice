package entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, exclude="skills")
@Entity
@NamedQueries(value = {
        @NamedQuery(name = "Hero.findAll", query = "select a from Hero as a"),
        @NamedQuery(name = "Hero.findById", query = "select a from Hero as a where a.id = :heroId"),
        @NamedQuery(name = "Hero.findAllBySkillId",
                query = "select distinct he from Hero as he join he.skills as skill join skill.heroes as hero where skill.id = :skillId")
})
@Table
public class Hero extends IdEntity implements Serializable {
    @Column(unique = true, nullable = false)
    @Size(max = 50)
    private String name;

    @Size(max = 25)
    // alliance, horde
    private String faction;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Skill> skills = new HashSet<>();

    public void addSkill(Skill skill) {
        skills.add(skill);
        skill.getHeroes().add(this);
    }

    public void removeSkill(Skill skill) {
        skills.remove(skill);
        skill.getHeroes().remove(this);
    }
}
