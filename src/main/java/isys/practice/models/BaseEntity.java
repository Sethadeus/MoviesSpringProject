package isys.practice.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    @Id
    @Column(columnDefinition = "uuid")
    @GeneratedValue(generator = "uuid-ossp")
    private UUID id;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
