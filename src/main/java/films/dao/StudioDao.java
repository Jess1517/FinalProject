package films.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import films.entity.Studio;

public interface StudioDao extends JpaRepository<Studio, Long> {

	Optional<Studio> findByStudioname(String studioName);

}
