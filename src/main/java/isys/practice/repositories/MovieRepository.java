package isys.practice.repositories;

import isys.practice.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {

}

