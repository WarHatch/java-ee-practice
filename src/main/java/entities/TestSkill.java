package entities;

import interceptors.Logged;
import lombok.*;

import javax.enterprise.inject.Alternative;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@EqualsAndHashCode(callSuper = true, exclude = "heroes")
@ToString(callSuper = true)
@Entity
@Alternative
public class TestSkill extends Skill implements ISkill {
    protected String pictureUrl = "https://via.placeholder.com/150";
}
