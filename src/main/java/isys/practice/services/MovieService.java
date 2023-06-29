package isys.practice.services;

import isys.practice.dto.MovieDTO;
import isys.practice.models.Genre;
import isys.practice.models.Movie;
import isys.practice.repositories.GenreRepository;
import isys.practice.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final GenreRepository genreRepository;
    private final MovieRepository movieRepository;

    public List<Movie> findAll(){
        return (List<Movie>) movieRepository.findAll();
    }

    public Movie findById(UUID id){
        return movieRepository.findById(id).orElse(null);
    }

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    public void delete(Movie movie) {
        movieRepository.delete(movie);
    }

    public Movie update(Movie movie, MovieDTO movieDTO) {
        movie.update(movieDTO);
        return movieRepository.save(movie);
    }
}
