package entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@NamedQueries({
        @NamedQuery(name = "Creature.findAll", query = "select a from Creature as a"),
        @NamedQuery(name = "Creature.count", query = "select count(a) from Creature as a")
})
@Table
@XmlRootElement //TODO: test this out
public class Creature extends IdEntity implements Serializable {
    @Size(max = 50)
    private String name;

    public Creature(String name) {
        this.name = name;
    }
}