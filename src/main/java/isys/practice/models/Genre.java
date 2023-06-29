package isys.practice.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "genre")
public class Genre extends BaseEntity{
    private String title;

    @JsonIgnoreProperties("genres")
    @ManyToMany(mappedBy = "genres")
    @JsonBackReference
    private List<Movie> movies = new ArrayList<>();

    public Genre(String title){
        this.title = title;
        setCreateDate(LocalDateTime.now());
        setUpdateDate(LocalDateTime.now());
    }

}

