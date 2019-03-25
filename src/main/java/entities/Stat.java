package entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@NamedQueries({
    @NamedQuery(name = "Stat.findAll", query = "select a from Stat as a"),
    @NamedQuery(name = "Stat.findAllByCreatureId",
            query = "select a from Stat a where a.creature.id = :creatureId")
})
@Table
public class Stat extends IdEntity implements Serializable {
    @Size(max = 50)
    // green, blue, red
    private String color;

    private short threat;

    private short attack;

    private short health;

    @Version
    private Integer optLockVersion;

    @ManyToOne
    @JoinColumn(name = "creature_id")
    private Creature creature = new Creature();

    public Stat(String color, short threat, short attack, short health) {
        this.color = color;
        this.threat = threat;
        this.attack = attack;
        this.health = health;
    }

    @Override
    public String toString() {
        return "Stat " + id + "\ncolor:" + color + " threat:" + threat + " attack:" + attack + " health:" + health;
    }
}



