package films.controller.model;

import films.entity.Genre;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class GenreData {
	private Long genreId;
	private String genrename;
	
public GenreData(Genre genre) {
	genreId = genre.getGenreId();
	genrename = genre.getGenrename();
	
}

}











