package isys.practice.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import isys.practice.models.Genre;
import isys.practice.services.GenreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/genres")
@Api(tags="Жанры", value="Контроллер для работы с жанрами фильмов")
public class GenreController {

    @Autowired
    GenreService genreService;

    @GetMapping("/")
    @ApiOperation(
            value = "Найти все",
            notes = "Метод для получения списка всех жанров"
    )
    public ResponseEntity<?> getAllGenres(){
        log.info("Received request to get all genres");
        List<Genre> genres = genreService.findAll();
        if(genres==null) {
            log.info("No genres found");
            return ResponseEntity.ok("Nothing found");
        }
        log.info("Returning {} genres", genres.size());
        return ResponseEntity.ok().body(genres);
    }

    @GetMapping("/{id}")
    @ApiOperation(
            value = "Поиск по id",
            notes = "Метод для поиска жанра по id"
    )
    public ResponseEntity<?> getGenreById(@PathVariable(value="id") UUID genreId){
        log.info("Received request to get Genre with id {}", genreId);
        Genre genre = genreService.findById(genreId);
        if(genre==null) {
            log.info("Genre with id  {} does not exist", genreId);
            return ResponseEntity.ok("Genre with id "+genreId+" does not exist");
        }
        log.info("Returning Genre with id {} ", genreId);
        return ResponseEntity.ok().body(genre);

    }

    @GetMapping("/search")
    @ApiOperation(
            value = "Поиск по названию",
            notes = "Метод для поиска жанра по названию"
    )
    public ResponseEntity<?> getGenreByTitle(@RequestParam("title") String title){
        log.info("Received request to get Genre with title {}", title);
        Genre genre = genreService.findByTitle(title);
        if(genre==null) {
            log.info("Genre with title {} does not exist", title);
            return ResponseEntity.ok("Genre with title "+title+" does not exist");
        }
        log.info("Returning Genre with title {}", title);
        return ResponseEntity.ok().body(genre);

    }

    @PostMapping("/")
    @ApiOperation(
            value = "Добавление",
            notes = "Метод для добавления нового жанра"
    )
    public ResponseEntity<?> saveGenre(@RequestParam String title) {
        log.info("Received request to save Genre with title {}", title);
        try {
            Genre savedGenre = genreService.save(title);
            log.info("Genre with title {} has been saved", title);
            return ResponseEntity.ok(savedGenre);
        } catch (DataIntegrityViolationException ex) {
            log.info("Genre with title {} already exists", title);
            return ResponseEntity.ok("Genre with title "+title+" already exists");
        }
    }

    @PutMapping("/update")
    @ApiOperation(
            value = "Обновление по названию",
            notes = "Метод для обновления жанра, принимает старое названи и новое"
    )
    public ResponseEntity<?> updateGenreByTitle(@RequestParam("title") String oldTitle, @RequestParam String newTitle){
        log.info("Received request to update Genre with title {}", oldTitle);
        Genre newGenre = genreService.findByTitle(oldTitle);
        if(newGenre==null) {
            log.info("Genre with title {} does not exists", oldTitle);
            return ResponseEntity.ok("Genre with title "+oldTitle+" does not exist");
        }
        newGenre.setTitle(newTitle);
        newGenre.setUpdateDate(LocalDateTime.now());
        Genre updateGenre = genreService.update(newGenre);
        log.info("Genre with title {} has been updated, now it has title {} ", oldTitle, newTitle);
        return ResponseEntity.ok().body(updateGenre);
    }

    @PutMapping("/{id}")
    @ApiOperation(
            value = "Обновление по id",
            notes = "Метод для обновления жанра по id"
    )
    public ResponseEntity<?> updateGenre(@PathVariable UUID id, @RequestParam String title){
        log.info("Received request to update Genre with id {}", id);
        Genre newGenre = genreService.findById(id);
        if(newGenre==null) {
            log.info("Genre with id {} does not exists", id);
            return ResponseEntity.ok("Genre with id "+id+" does not exist");
        }

        newGenre.setTitle(title);
        newGenre.setUpdateDate(LocalDateTime.now());
        Genre updateGenre = genreService.update(newGenre);
        log.info("Genre with id {} has been updated", id);
        return ResponseEntity.ok().body(updateGenre);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(
            value = "Удаление по id",
            notes = "Метод для удаления жанра по id"
    )
    public ResponseEntity<?> deleteGenreById(@PathVariable UUID id){
        log.info("Received request to delete Genre with id {}", id);
        Genre genre = genreService.findById(id);
        if(genre==null) {
            log.info("Genre with id {} does not exists", id);
            return ResponseEntity.ok("Genre with id "+id+" does not exist");
        }
        genreService.delete(genre);
        log.info("Genre with id {} has been deleted", id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    @ApiOperation(
            value = "Удаление по названию",
            notes = "Метод для удаления жанра по названию"
    )
    public ResponseEntity<?> deleteGenreByTitle(@RequestParam("title") String title){
        log.info("Received request to delete Genre with title {}", title);
        Genre genre = genreService.findByTitle(title);
        if(genre==null) {
            log.info("Genre with title {} does not exists", title);
            return ResponseEntity.ok("Genre with title "+title+" does not exist");
        }
        genreService.delete(genre);
        log.info("Genre with title {} has been deleted", title);
        return ResponseEntity.ok().build();
    }

}
