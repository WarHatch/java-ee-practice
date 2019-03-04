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
        @NamedQuery(name = "Creature.findAll", query = "select a from Creature as a")
})
@Table
public class Creature extends IdEntity implements Serializable {
    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    public Creature(String name) {
        this.name = name;
    }
}