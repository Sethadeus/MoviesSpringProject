package isys.practice.models;

import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Genre {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long genre_id;
    private String title;

    @ManyToMany(mappedBy = "genre")
    private List<Movie> movie = new ArrayList<>();


    public Genre(String title) {
        this.title = title;
    }
}

