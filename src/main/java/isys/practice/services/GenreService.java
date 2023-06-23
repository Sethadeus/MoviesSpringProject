package isys.practice.services;

import isys.practice.models.Genre;
import isys.practice.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> findAll(){
        return (List<Genre>) genreRepository.findAll();
    }

    public Genre findById(Long id){
        return genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found with id: " + id));
    }

    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    public void delete(Genre genre) {
        genreRepository.delete(genre);
    }

    public Genre update(Genre genre) {
        return genreRepository.save(genre);
    }
}
