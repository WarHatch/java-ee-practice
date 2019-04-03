package entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@EqualsAndHashCode
@Getter
@Setter
@MappedSuperclass
@ToString
public abstract class IdEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
}
