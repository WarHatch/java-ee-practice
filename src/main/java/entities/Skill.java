package entities;

import lombok.*;

import javax.enterprise.inject.Default;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, exclude = "heroes")
@ToString(callSuper = true)
@Entity
@NamedQueries({
        @NamedQuery(name = "Skill.findAll", query = "select a from Skill as a"),
        @NamedQuery(name = "Skill.findById", query = "select a from Skill as a where a.id = :skillId"),
        @NamedQuery(name = "Skill.findAllByHeroId",
                query = "select distinct sk from Skill as sk join sk.heroes as hero join hero.skills as skill where hero.id = :heroId")
})
@Table
public class Skill extends IdEntity implements ISkill {
    @Column(unique = true)
    @Size(max = 50)
    protected String name;

    protected byte levelRequirement;

    @Size(max = 250)
    protected String description;

    protected String pictureUrl;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "skills")
    protected Set<Hero> heroes = new HashSet<>();

    @Override
    public void addHero(Hero hero) {
        heroes.add(hero);
        hero.getSkills().add(this);
    }

    @Override
    public void removeHero(Hero hero) {
        heroes.remove(hero);
        hero.getSkills().remove(this);
    }
}
