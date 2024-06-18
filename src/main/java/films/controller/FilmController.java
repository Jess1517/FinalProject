package films.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import films.controller.model.FilmsData;
import films.controller.model.GenreData;
import films.controller.model.StudioData;
import films.entity.Genre;
import films.service.FilmService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {
	@Autowired
	private FilmService filmService; 
	
	@PostMapping("/studio")
	@ResponseStatus(code = HttpStatus.CREATED)
	public StudioData insertStudio(
			@RequestBody StudioData studioData) { 
		log.info("Creating studio ()", studioData); 
		return filmService.saveStudio(studioData); 
		
	}
	
	@PutMapping("/studio/{studioId}")
	public StudioData updateStudio(@PathVariable Long studioId,
			@RequestBody StudioData studioData) { 
		studioData.setStudioId(studioId);
		log.info("Updating studio {}", studioData);
		return filmService.saveStudio(studioData);
	}
	
	@GetMapping("/studio")
	public List<StudioData> retrieveAllStudios() {
		log.info("Retrieve all studios called."); 
		return filmService.retrieveAllContributors(); 
	}

	
	@GetMapping("/studio/{studioId}")
	public StudioData retrieveStudioById(@PathVariable Long studioId) {
		log.info("Retrieving studio with ID={}", studioId);
		return filmService.retrieveStudioById(studioId); 
		
	}
	@DeleteMapping("/studio")
	public void deleteAllStudios() {
		log.info("Attempting to delete all studios");
		throw new UnsupportedOperationException(
				"Deleteing all studios is not allowed."); 
		
	}
	
	@PostMapping("/studio/{studioId}/film")
	@ResponseStatus(code = HttpStatus.CREATED)
	public FilmsData insertFilms(@PathVariable Long studioId,
			@RequestBody FilmsData filmsData)  {
		
		log.info("Creating film {} for studio with ID= {}", filmsData,
				studioId);
		
		return filmService.saveFilms(studioId, filmsData); 
	}
	
	@PutMapping("/studio/{studioId}/film/{filmId}") 
	public FilmsData updateFilms(@PathVariable Long studioId,
			@PathVariable Long filmId,
			@RequestBody FilmsData filmsData)  {
		
		filmsData.setFilmsId(filmId);
		
		log.info("Creating film {} for studio with ID= {}", filmsData,
				studioId);
		
		return filmService.saveFilms(studioId, filmsData); 
	}
	@DeleteMapping("/studio/{studioId}")
	public Map<String, String> deleteStudioById(
			@PathVariable Long studioId) {
		log.info("Deleting studio with ID={}", studioId); 
		
		filmService.deleteStudioById(studioId);
		
		return Map.of("message", "Deletion of studio with ID=" + studioId
				+ "was successful.");
	}
	
	@GetMapping("/studio/{studioId}/film/{filmId}") 
	public FilmsData retrieveFilmsById(@PathVariable Long studioId,
			@PathVariable Long filmId) {
		log.info("Retrieving films with ID={} for studio with ID={}",
				filmId, studioId); 
		
		return filmService.retrieveFilmsById(studioId, filmId); 
		
	}
	
	@GetMapping("/films")
	public List<FilmsData> retrieveAllFilms() {
		log.info("Retrieve all films called.");
		return filmService.retrieveAllFilms(); 
	}
	
	@PostMapping("/film/{filmId}/genre")
	@ResponseStatus(code = HttpStatus.CREATED)
	public GenreData insertGenre(@PathVariable Long filmId,
			@RequestBody GenreData genreData)  {
		
		log.info("Creating film {} for genre with ID= {}", genreData,
				filmId);
		
		return filmService.saveGenre(filmId, genreData); 
	}
}
	
	



