package entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;
import lombok.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Creature.findAll", query = "select a from Creature as a")
})
@Table(name = "CREATURE")
public class Creature implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    @Getter
    private String name;

    public Creature() {
    }

    public Creature(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Creature creature = (Creature) o;
        return Objects.equals(id, creature.id) &&
                Objects.equals(name, creature.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}