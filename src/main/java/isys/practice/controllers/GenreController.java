package isys.practice.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import isys.practice.Responses.ApiResponse;
import isys.practice.Responses.ApiResponseWithPages;
import isys.practice.models.Genre;
import isys.practice.services.GenreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/genres")
@Api(tags = "Жанры", value = "Контроллер для работы с жанрами фильмов")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping("/")
    @ApiOperation(
            value = "Найти все",
            notes = "Метод для получения списка всех жанров"
    )
    public ResponseEntity<?> getAllGenres(@RequestParam int page, @RequestParam int size) {
        log.info("Received request to get all genres");

        Page<Genre> genrePage = genreService.findAll(page, size);
        List<Genre> genres = genrePage.getContent();

        if (genres.isEmpty()) {
            return ResponseEntity.ok(new ApiResponseWithPages<>("NOT_FOUND", genres, 0, 0, 0));
        }
        return ResponseEntity.ok(new ApiResponseWithPages<>("", genres, genrePage.getTotalElements(), genrePage.getTotalPages(),genrePage.getNumber() + 1));
    }

    @GetMapping("/{id}")
    @ApiOperation(
            value = "Поиск по id",
            notes = "Метод для получения жанра по id"
    )
    public ResponseEntity<?> getGenreById(@PathVariable(value = "id") UUID genreId) {
        log.info("Received request to get Genre with id {}", genreId);

        Genre genre = genreService.findById(genreId);

        if (genre == null) {
            return ResponseEntity.ok(new ApiResponse<>("NOT_FOUND", null));
        }
        return ResponseEntity.ok(new ApiResponse<>("", genre));
    }

    @PostMapping("/")
    @ApiOperation(
            value = "Добавление",
            notes = "Метод для добавления нового жанра"
    )
    public ResponseEntity<?> saveGenre(@RequestBody String title) {
        log.info("Received request to save Genre with title {}", title);
        try {
            Genre savedGenre = genreService.save(title);
            return ResponseEntity.ok(new ApiResponse<>("", savedGenre));
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.ok(new ApiResponse<>("ALREADY_EXISTS", null));
        }
    }

    @PutMapping("/")
    @ApiOperation(
            value = "Обновление по id",
            notes = "Метод для обновления жанра по id"
    )
    public ResponseEntity<?> updateGenre(@RequestBody Genre genre) {
        log.info("Received request to update Genre with id {}", genre.getId());

        Genre updateGenre = genreService.update(genre);

        if (updateGenre != null) {
            return ResponseEntity.ok(new ApiResponse<>("", updateGenre));
        }
        return ResponseEntity.ok(new ApiResponse<>("NOT_EXISTS", null));
    }


    @DeleteMapping("/{id}")
    @ApiOperation(
            value = "Удаление по id",
            notes = "Метод для удаления жанра по id"
    )
    public ResponseEntity<?> deleteGenreById(@PathVariable UUID id) {
        log.info("Received request to delete Genre with id {}", id);

        if (genreService.delete(id)){
            return ResponseEntity.ok(new ApiResponse<>("", null));
        }
        return ResponseEntity.ok(new ApiResponse<>("NOT_EXISTS", null));
    }

}
