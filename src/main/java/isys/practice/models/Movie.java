package isys.practice.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import isys.practice.dto.MovieDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.*;

@Entity @Getter @Setter @ToString @NoArgsConstructor
@Table(name = "movie")
public class Movie extends BaseEntity{
    private String title;
    private String description;
    private int year;
    private String playerUrl;
    private double imdbRating;
    private LocalTime duration;

    @JsonIgnoreProperties("movies")
    @ManyToMany
    @JoinTable(name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres = new ArrayList<>();

    public void addGenreToList(Genre gen){
        genres.add(gen);
    }

}