package isys.practice.repositories;

import isys.practice.models.Genre;
import isys.practice.models.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {
    @NonNull
    Page<Movie> findAll(@NonNull Pageable pageable);
}

