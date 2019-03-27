package entities;

import lombok.*;

import javax.enterprise.inject.Alternative;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, exclude = "heroes")
@ToString
@Entity
@Table(name = "Skill")
@Alternative
public class TestSkill extends Skill implements Serializable {
    protected String pictureUrl = "https://via.placeholder.com/150";
}
