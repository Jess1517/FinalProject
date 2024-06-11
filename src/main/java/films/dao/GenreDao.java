package films.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import films.entity.Genre;


public interface GenreDao extends JpaRepository<Genre, Long> {

	Set<Genre> findAllByGenrenameIn(java.util.Set<String> genrename);

}
