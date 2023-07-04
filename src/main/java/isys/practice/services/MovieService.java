package isys.practice.services;

import isys.practice.dto.MovieDTO;
import isys.practice.models.Genre;
import isys.practice.models.Movie;
import isys.practice.repositories.GenreRepository;
import isys.practice.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final GenreRepository genreRepository;
    private final MovieRepository movieRepository;
    private final GenreService genreService;

    public Page<Movie> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return movieRepository.findAll(pageable);
    }

    public Movie findById(UUID id){
        return movieRepository.findById(id).orElse(null);
    }

    public Movie save(MovieDTO movieDTO) {
        Movie newMovie = dtoToMovie(new Movie(), movieDTO);
        newMovie.setCreateDate(LocalDateTime.now());
        newMovie.setUpdateDate(LocalDateTime.now());

        newMovie.setGenres(new ArrayList<>());

        boolean allGenresExists = true;

        if (movieDTO.getGenres().size()!=0){

            for(Genre gen: movieDTO.getGenres()){
                Genre newGen = genreService.findById(gen.getId());
                if (newGen!=null){
                    newMovie.addGenreToList(gen);
                }
                else{
                    allGenresExists  = false;
                    break;
                }
            }
            if (allGenresExists){
                return movieRepository.save(newMovie);
            }
            return null;
        }
        return movieRepository.save(newMovie);
    }

    public boolean delete(UUID id) {
        Movie movie = findById(id);
        if (movie == null) {
            return false;
        }
        movieRepository.delete(movie);
        return true;
    }

    public Movie update(Movie newMovie) {
        Movie oldMovie = findById(newMovie.getId());
        if(oldMovie==null) {
            return null;
        }

        boolean allGenresExists = true;

        for(Genre gen: newMovie.getGenres()){
            Genre newGen = genreService.findById(gen.getId());
            if (newGen==null){
                allGenresExists = false;
                break;
            }
        }
        if(allGenresExists){
            newMovie.setUpdateDate(LocalDateTime.now());
            return movieRepository.save(newMovie);
        }
        return null;
    }

    public Movie dtoToMovie(Movie movie, MovieDTO movieDTO){
        movie.setTitle(movieDTO.getTitle());
        movie.setDescription(movieDTO.getDescription());
        movie.setYear(movieDTO.getYear());
        movie.setPlayerUrl(movieDTO.getPlayerUrl());
        movie.setImdbRating(movieDTO.getIMDBRating());
        movie.setDuration(movieDTO.getDuration());
        return movie;
    }
}
