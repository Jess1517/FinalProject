package films.controller.model;


import java.util.HashSet;
import java.util.Set;

import films.entity.Films;
import films.entity.Genre;
import films.entity.Studio;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class FilmsData {
	private Long filmsId;
	private String filmname; 
	private Set<String> genrenames = new HashSet<>(); 
	

public FilmsData(Films films) {
	filmsId = films.getFilmsId();
	filmname = films.getFilmname();
	
	
	for(Genre genre : films.getGenres()) {
		genrenames.add(genre.getGenrename());
	}
	
}
	
////@Data
//@NoArgsConstructor
//public static class FilmsStudio {
//	private Long studioId; 
//	private String studioname; 
//	
//public FilmsStudio(Studio studio) {
//	studioId = studio.getStudioId();
//	studioname = studio.getStudioname();
//	
//	}
//}
	



}
