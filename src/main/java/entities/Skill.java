package entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@NamedQueries({
    @NamedQuery(name = "Skill.findAll", query = "select a from Skill as a"),
    @NamedQuery(name = "Skill.findAllByHeroId",
            query = "select sk from Skill as sk join sk.heroes as hero join hero.skills as skill where hero.id = :heroId")
})
@Table
public class Skill implements Serializable{
    @Id
    private int id;

    @Column(unique = true)
    @Size(max = 50)
    private String name;

    private byte levelRequirement;

    @Size(max = 250)
    private String description;

    @ManyToMany(mappedBy = "skills")
//    @JoinTable(name = "hero_skill",
//            joinColumns = @JoinColumn(name = "skill_id"),
//            inverseJoinColumns = @JoinColumn(name = "hero_id")
//    )
    private List<Hero> heroes;

    public void addHero(Hero hero) {
        heroes.add(hero);
        hero.getSkills().add(this);
    }

    public void removeHero(Hero hero) {
        heroes.remove(hero);
        hero.getSkills().remove(this);
    }
}
