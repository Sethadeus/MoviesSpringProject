package isys.practice.dto;

import isys.practice.models.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter @Setter
@ToString
public class MovieDTO {
    private String title;
    private String description;
    private int year;
    private String playerUrl;
    private double IMDBRating;
    private LocalTime duration;
    private List<Genre> genres;

}
