package isys.practice.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "roles")
public class Role {
    @Id
    @Column(columnDefinition = "uuid")
    @GeneratedValue(generator = "uuid-ossp")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public Role(ERole name) {
        this.name = name;
    }
}
