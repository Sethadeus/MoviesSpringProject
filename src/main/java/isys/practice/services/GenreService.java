package isys.practice.services;

import isys.practice.models.Genre;
import isys.practice.repositories.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    public List<Genre> findAll(){
        return genreRepository.findAll();
    }

    public Genre findById(UUID id){
        return genreRepository.findById(id).orElse(null);
    }

    public Genre findByTitle(String title){
        return genreRepository.findByTitle(title).orElse(null);
    }

    public Genre save(String title) {
        return genreRepository.save(new Genre(title));
    }

    public void delete(Genre genre) {
        genreRepository.delete(genre);
    }

    public Genre update(Genre genre) {
        return genreRepository.save(genre);
    }

}
