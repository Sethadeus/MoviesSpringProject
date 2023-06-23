package isys.practice.services;

import isys.practice.models.Genre;
import isys.practice.models.Movie;
import isys.practice.repositories.GenreRepository;
import isys.practice.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> findAll(){
        return (List<Movie>) movieRepository.findAll();
    }

    public Movie findById(Long id){
        return movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found with id: " + id));
    }

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    public void delete(Movie movie) {
        movieRepository.delete(movie);
    }

    public Movie update(Movie movie) {
        return movieRepository.save(movie);
    }
}
