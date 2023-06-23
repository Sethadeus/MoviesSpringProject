package isys.practice.services;

import isys.practice.models.Genre;
import isys.practice.models.Movie;
import isys.practice.repositories.GenreRepository;


import isys.practice.repositories.MovieRepository;
import org.springframework.stereotype.Service;


@Service
public class GenreMovieService {
    private final GenreRepository genreRepository;

    private final MovieRepository movieRepository;

    public GenreMovieService(GenreRepository genreRepository, MovieRepository movieRepository) {
        this.genreRepository = genreRepository;
        this.movieRepository = movieRepository;
    }

    public void saveMovieWithGenres(Movie movie) {
        genreRepository.saveAll(movie.getGenre());
        movieRepository.save(movie);
    }

    public void saveGenreWithMovies(Genre genre) {
        movieRepository.saveAll(genre.getMovie());
        genreRepository.save(genre);
    }

}
