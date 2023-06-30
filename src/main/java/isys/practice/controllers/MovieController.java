package isys.practice.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import isys.practice.dto.MovieDTO;
import isys.practice.models.Genre;
import isys.practice.models.Movie;
import isys.practice.repositories.GenreRepository;
import isys.practice.services.GenreService;
import isys.practice.services.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/movies")
@Api(tags="Фильмы", value="Контроллер для работы с фильмами")
public class MovieController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private GenreService genreService;

    @GetMapping("/")
    @ApiOperation(
            value = "Найти все",
            notes = "Метод для получения списка всех фильмов"
    )
    public ResponseEntity<?> getAllMovies(){
        log.info("Received request to get all movies");
        List<Movie> movies = movieService.findAll();
        if(movies==null) {
            log.info("No movies found");
            return ResponseEntity.ok("Nothing found");
        }
        log.info("Returning {} movies", movies.size());
        return ResponseEntity.ok().body(movies);
    }

    @GetMapping("/{id}")
    @ApiOperation(
            value = "Поиск по id",
            notes = "Метод для поиска фильма по id"
    )
    public ResponseEntity<?> getMovieById(@PathVariable(value="id") UUID movieId){
        log.info("Received request to get Movie with id {}", movieId);
        Movie movie = movieService.findById(movieId);
        if(movie==null) {
            log.info("Movie with id  {} does not exist", movieId);
            return ResponseEntity.ok("Movie with id "+movieId+" does not exist");
        }
        log.info("Returning Movie with id {} ", movieId);
        return ResponseEntity.ok().body(movie);

    }

    @PostMapping("/")
    @ApiOperation(
            value = "Добавить",
            notes = "Метод для добавления нового фильма"
    )
    public ResponseEntity<?> saveMovie(@RequestBody MovieDTO movieDTO) {
        log.info("Received request to save Movie {}", movieDTO.toString());
        Movie newMovie = new Movie(movieDTO);
        newMovie.setGenres(new ArrayList<>());
        boolean tmp = true; // проверка что все связанные сфильмом жанрыдействительно существуют
        if (movieDTO.getGenres().size()!=0){
            for(Genre gen: movieDTO.getGenres()){
                Genre newGen = genreService.findById(gen.getId());
                if (newGen!=null){
                    newMovie.addGenreToList(gen);
                }
                else{
                    tmp = false;
                    break;
                }
            }
            if (tmp){
                log.info("Movie has been saved {}", movieDTO.toString());
                return ResponseEntity.ok().body(movieService.save(newMovie));
            }
            else {
                log.info("Error saving Movie, wrong genre id");
                return ResponseEntity.ok("Wrong genre id");
            }
        }
        log.info("Movie has been saved {}", movieDTO.toString());
        return ResponseEntity.ok().body(movieService.save(newMovie));
    }

    @PutMapping("/{id}")
    @ApiOperation(
            value = "Обновить",
            notes = "Метод для обновления фильма"
    )
    public ResponseEntity<?> updateMovie(@PathVariable(value="id") UUID movieId, @RequestBody MovieDTO movieDTO){
        log.info("Received request to update Movie {}", movieDTO.toString());
        Movie movie = movieService.findById(movieId);
        if(movie==null) {
            return ResponseEntity.ok("Movie with id "+movieId+" does not exist");
        }
        movie.setGenres(new ArrayList<>());
        boolean tmp = true;  // проверка что все связанные сфильмом жанрыдействительно существуют
        if (movieDTO.getGenres().size()!=0){
            for(Genre gen: movieDTO.getGenres()){
                Genre newGen = genreService.findById(gen.getId());
                if (newGen!=null){
                    movie.addGenreToList(gen);
                }
                else{
                    tmp = false;
                    break;
                }
            }
            if (tmp){
                Movie updateMovie = movieService.update(movie,movieDTO);
                log.info("Movie has been updated {}", movieDTO.toString());
                return ResponseEntity.ok().body(updateMovie);
            }
            else {
                log.info("Error updating Movie, wrong genre id");
                return ResponseEntity.ok("Wrong genre id");
            }
        }
        else{
            Movie updateMovie = movieService.update(movie,movieDTO);
            log.info("Movie has been updated {}", movieDTO.toString());
            return ResponseEntity.ok().body(updateMovie);
        }
    }


    @DeleteMapping("/{id}")
    @ApiOperation(
            value = "Удалить",
            notes = "Метод для удаления фильма"
    )
    public ResponseEntity<?> deleteMovie(@PathVariable(value="id") UUID movieId){
        log.info("Received request to delete Movie with id {}", movieId);
        Movie movie = movieService.findById(movieId);
        if(movie==null) {
            log.info("Movie with id {} does not exists", movieId);
            return ResponseEntity.ok("Movie with id "+movieId+" does not exist");
        }
        movieService.delete(movie);
        log.info("Movie with id {} has been deleted", movieId);
        return ResponseEntity.ok().build();
    }

}
