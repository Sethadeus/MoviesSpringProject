package isys.practice.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import isys.practice.Responses.ApiResponse;
import isys.practice.Responses.ApiResponseWithPages;
import isys.practice.dto.MovieDTO;
import isys.practice.models.Genre;
import isys.practice.models.Movie;
import isys.practice.repositories.GenreRepository;
import isys.practice.services.GenreService;
import isys.practice.services.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<?> getAllMovies(@RequestParam int page, @RequestParam int size) {
        log.info("Received request to get all movies");

        Page<Movie> moviePage = movieService.findAll(page, size);
        List<Movie> movies = moviePage.getContent();

        if (movies.isEmpty()) {
            return ResponseEntity.ok(new ApiResponseWithPages<>("NOT_FOUND", movies, 0, 0, 0));
        }
        return ResponseEntity.ok(new ApiResponseWithPages<>("", movies, moviePage.getTotalElements(), moviePage.getTotalPages(),moviePage.getNumber() + 1));
    }

    @GetMapping("/{id}")
    @ApiOperation(
            value = "Поиск по id",
            notes = "Метод для получения фильма по id"
    )
    public ResponseEntity<?> getMovieById(@PathVariable(value = "id") UUID movieId) {
        log.info("Received request to get Movie with id {}", movieId);

        Movie movie = movieService.findById(movieId);

        if (movie == null) {
            return ResponseEntity.ok(new ApiResponse<>("NOT_FOUND", null));
        }
        return ResponseEntity.ok(new ApiResponse<>("", movie));
    }

    @PostMapping("/")
    @ApiOperation(
            value = "Добавить",
            notes = "Метод для добавления нового фильма"
    )
    public ResponseEntity<?> saveMovie(@RequestBody MovieDTO movieDTO) {
        log.info("Received request to save Movie {}", movieDTO.toString());
        Movie savedMovie = movieService.save(movieDTO);
        if (savedMovie==null){
            return ResponseEntity.ok(new ApiResponse<>("WRONG_ID", null));
        }
        return ResponseEntity.ok(new ApiResponse<>("", savedMovie));
    }

    @PutMapping("/")
    @ApiOperation(
            value = "Обновить",
            notes = "Метод для обновления фильма"
    )
    public ResponseEntity<?> updateMovie(@RequestBody Movie movie){
        log.info("Received request to update Movie {}", movie.toString());
        Movie updatedMovie = movieService.update(movie);
        if (updatedMovie==null){
            return ResponseEntity.ok(new ApiResponse<>("WRONG_ID", null));
        }
        return ResponseEntity.ok(new ApiResponse<>("", updatedMovie));
    }


    @DeleteMapping("/{id}")
    @ApiOperation(
            value = "Удалить",
            notes = "Метод для удаления фильма"
    )
    public ResponseEntity<?> deleteMovie(@PathVariable(value="id") UUID movieId){
        log.info("Received request to delete Movie with id {}", movieId);

        if (movieService.delete(movieId)){
            return ResponseEntity.ok(new ApiResponse<>("", null));
        }
        return ResponseEntity.ok(new ApiResponse<>("NOT_EXISTS", null));
    }

}
