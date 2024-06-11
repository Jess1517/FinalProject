package films.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import films.entity.Films;

public interface FilmsDao extends JpaRepository<Films, Long> {

}
