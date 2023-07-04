package isys.practice.services;

import isys.practice.models.Genre;
import isys.practice.repositories.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    public Page<Genre> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return genreRepository.findAll(pageable);
    }

    public Genre findById(UUID id){
        return genreRepository.findById(id).orElse(null);
    }

    public Genre save(String title) {
        return genreRepository.save(new Genre(title));
    }

    public boolean delete(UUID id) {
        Genre genre = findById(id);
        if (genre == null) {
            return false;
        }
        genreRepository.delete(genre);
        return true;
    }

    public Genre update(Genre genre) {
        Genre newGenre = findById(genre.getId());
        if(newGenre==null) {
            genre.setUpdateDate(LocalDateTime.now());
            return genreRepository.save(genre);
        }
        return null;
    }

}
