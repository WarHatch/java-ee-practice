package entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@EqualsAndHashCode
@Getter
@Setter
@MappedSuperclass
public abstract class IdEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
}
