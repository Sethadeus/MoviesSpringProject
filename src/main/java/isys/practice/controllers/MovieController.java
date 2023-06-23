package isys.practice.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import isys.practice.models.Movie;
import isys.practice.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
@Api(tags="Фильмы", description="Контроллер для работы с фильмами")
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping("/movies")
    @ApiOperation(
            value = "Найти все",
            notes = "Метод для получения списка всех фильмов"
    )
    public List<Movie> getAllMovies(){
        return movieService.findAll();
    }

    @GetMapping("/movies/{id}")
    @ApiOperation(
            value = "Найти",
            notes = "Метод для получения фильма по id"
    )
    public ResponseEntity<Movie> getMovieById(@PathVariable(value="id") Long movieId){

        Movie movie = movieService.findById(movieId);

        if(movie==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(movie);

    }

    @PostMapping("/movies")
    @ApiOperation(
            value = "Добавить",
            notes = "Метод для добавления нового фильма"
    )
    public Movie saveMovie(@RequestBody Movie movie) {
        return movieService.save(movie);
    }

    @PutMapping("/movies/{id}")
    @ApiOperation(
            value = "Обновить",
            notes = "Метод для обновления фильма"
    )
    public ResponseEntity<Movie> updateMovie(@PathVariable(value="id") Long movieId,@RequestBody Movie movie){

        Movie newMovie = movieService.findById(movieId);
        if(newMovie==null) {
            return ResponseEntity.notFound().build();
        }

        newMovie.setTitle(movie.getTitle());

        Movie updateMovie = movieService.update(newMovie);
        return ResponseEntity.ok().body(updateMovie);
    }


    @DeleteMapping("/movies/{id}")
    @ApiOperation(
            value = "Удалить",
            notes = "Метод для удаления фильма"
    )
    public ResponseEntity<Movie> deleteMovie(@PathVariable(value="id") Long genreId){

        Movie movie = movieService.findById(genreId);
        if(movie==null) {
            return ResponseEntity.notFound().build();
        }
        movieService.delete(movie);

        return ResponseEntity.ok().build();
    }

}
