package isys.practice.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Tag;
import isys.practice.models.Genre;
import isys.practice.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genre")
@Api(tags="Жанры", description="Контроллер для работы с жанрами фильмов")
public class GenreController {
    @Autowired
    GenreService genreService;

    @GetMapping("/genres")
    @ApiOperation(
            value = "Найти все",
            notes = "Метод для получения списка всех жанров"
    )
    public List<Genre> getAllGenres(){
        return genreService.findAll();
    }

    @GetMapping("/genres/{id}")
    @ApiOperation(
            value = "Найти",
            notes = "Метод для получения жанра по id"
    )
    public ResponseEntity<Genre> getMovieById(@PathVariable(value="id") Long genreId){

        Genre genre = genreService.findById(genreId);

        if(genre==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(genre);

    }
    @PostMapping("/genres")
    @ApiOperation(
            value = "Добавить",
            notes = "Метод для добавления нового жанра"
    )
    public Genre saveGenre(@RequestBody Genre genre) {
        return genreService.save(genre);
    }

    @PutMapping("/genres/{id}")
    @ApiOperation(
            value = "Обновить",
            notes = "Метод для обновления жанра"
    )
    public ResponseEntity<Genre> updateGenre(@PathVariable(value="id") Long genreId,@RequestBody Genre genre){

        Genre newGenre = genreService.findById(genreId);
        if(newGenre==null) {
            return ResponseEntity.notFound().build();
        }

        newGenre.setTitle(genre.getTitle());


        Genre updateMovie = genreService.update(newGenre);
        return ResponseEntity.ok().body(updateMovie);

    }


    @DeleteMapping("/genres/{id}")
    @ApiOperation(
            value = "Удалить",
            notes = "Метод для удаления жанра"
    )
    public ResponseEntity<Genre> deleteGenre(@PathVariable(value="id") Long genreId){

        Genre genre = genreService.findById(genreId);
        if(genre==null) {
            return ResponseEntity.notFound().build();
        }
        genreService.delete(genre);

        return ResponseEntity.ok().build();
    }

}
