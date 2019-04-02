package entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@NamedQueries({
        @NamedQuery(name = "Stat.findAll",
                query = "select a from Stat as a"),
        @NamedQuery(name = "Stat.findById",
                query = "select a from Stat a where a.id = :id"),
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

    private String specialAttribute;

    @Version
    private Integer optLockVersion;

    @ManyToOne
    @JoinColumn(name = "CREATURE_ID")
    private Creature creature = new Creature();
}



