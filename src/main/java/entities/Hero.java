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
    @NamedQuery(name = "Hero.findAll", query = "select a from Hero as a")
})
@Table
public class Hero implements Serializable {
    @Id
    private int id;

    @Column(unique = true, nullable = false)
    @Size(max = 50)
    private String name;

    @Size(max = 25)
    // alliance, horde
    private String faction;

    @ManyToMany
    private List<Skill> skills = new ArrayList<>();
}
