package isys.practice.models;


import lombok.*;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.*;
import java.time.LocalTime;

@Entity @Getter @Setter @ToString @NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY, generator="movie_gen")
    @SequenceGenerator(name="movie_gen", sequenceName="MOVIE_SEQ")
    private Long movie_id;
    private String title;
    /*private String description;
    private int year;
    private String playerUrl;
    private double IMDBRating;
    private LocalTime duration;*/
    @ManyToMany
    @JoinTable(name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genre = new ArrayList<>();

    public void setGenre(List<Genre> genre){
        this.genre = genre;
    }

    public Movie(Long movie_id, String title /*,String description, int year, String playerUrl, double IMDBRating, LocalTime duration*/) {
        this.movie_id = movie_id;
        this.title = title;
/*        this.description = description;
        this.year = year;
        this.playerUrl = playerUrl;
        this.IMDBRating = IMDBRating;
        this.duration = duration;*/
    }

    public Movie(String title /*, String description, int year, String playerUrl, double IMDBRating, LocalTime duration*/) {
        this.title = title;
/*        this.description = description;
        this.year = year;
        this.playerUrl = playerUrl;
        this.IMDBRating = IMDBRating;
        this.duration = duration;*/
    }
}