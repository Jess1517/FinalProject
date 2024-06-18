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
public class StudioData {
	

	private Long studioId; 
	private String studioname; 
	private Set<FilmsResponse> films = new HashSet<>(); 

	public StudioData(Studio studio) {
		studioId = studio.getStudioId(); 
		studioname = studio.getStudioname(); 
		
		for(Films film : studio.getFilms()) {
			films.add(new FilmsResponse(film)); 
		}
	}
	
	@Data
	@NoArgsConstructor
	static class FilmsResponse {
	private Long filmsId;
	private String filmname; 
	
	
	
	
	FilmsResponse(Films films) { 
		filmsId = films.getFilmsId();
		filmname = films.getFilmname(); 
//		
//		for(Genre genre : films.getGenres()) {
//			genres.add(genre.getGenrename()); 
//		}
	}
}

}
