package films.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import films.controller.model.FilmsData;
import films.controller.model.StudioData;
import films.dao.FilmsDao;
import films.dao.GenreDao;
import films.dao.StudioDao;
import films.entity.Films;
import films.entity.Genre;
import films.entity.Studio;

@Service
public class FilmService {

	@Autowired
	private StudioDao studioDao; 
	
	@Autowired
	private GenreDao genreDao; 
	
	@Autowired
	private FilmsDao filmsDao; 
	
	@Transactional(readOnly = false)
	public StudioData saveStudio(StudioData studioData) {
		Long studioId = studioData.getStudioId();
		Studio studio = findOrCreateStudio(studioId, 
				studioData.getStudioname());  
	
		setFieldsInStudio(studio, studioData); 
		return new StudioData(studioDao.save(studio)); 
	}

	private void setFieldsInStudio(Studio studio,
			StudioData studioData) {
		 {
	  studio.setStudioId(studioData.getStudioId());
	  studio.setStudioname(studioData.getStudioname()); 
	  }
		
	}

	private Studio findOrCreateStudio(Long studioId, 
			String studioName) { 
		Studio studio; 
		
		if(Objects.isNull(studioId)) { 
			Optional<Studio> opStudio = 
					studioDao.findByStudioname(studioName); 
			
			if(opStudio.isPresent()) {
				throw new DuplicateKeyException(
						"Studio with name" + studioName + "already exists.");
			}
			
			studio = new Studio(); 
		}
		else {
			studio = findStudioById(studioId); 
		}
		
		return studio; 
	}

	private Studio findStudioById(Long studioId) {
		return studioDao.findById(studioId)
			.orElseThrow(() -> new NoSuchElementException("Studio with ID=" + studioId + "was not found."));
	}
	@Transactional(readOnly = true) 
	public List<StudioData> retrieveAllContributors() {
		List<Studio> studios = studioDao.findAll(); 
		List<StudioData> response = new LinkedList<>();
		
		for(Studio studio : studios) { 
			response.add(new StudioData(studio)); 
		}
		
		return response;
		
	}

	@Transactional(readOnly = true)
	public StudioData retrieveStudioById(Long studioId) {
		Studio studio = findStudioById(studioId);
		return new StudioData(studio); 
	}
	
	@Transactional(readOnly = false)
	public void deleteStudioById(Long studioId) {
		Studio studio = findStudioById(studioId);
		studioDao.delete(studio);
		
	}
    
	@Transactional(readOnly = false)
	public FilmsData saveFilms(Long studioId,
			FilmsData filmsData) {
	Studio studio = findStudioById(studioId);
	
	Set<Genre> genres = genreDao.findAllByGenrenameIn(filmsData.getGenrenames());
	
	Films films = findOrCreateFilms(filmsData.getFilmsId());
	setFilmsFields(films, filmsData); 
	
	films.setStudio(studio);
	studio.getFilms().add(films); 
	
	for(Genre genre : genres) {
		genre.getFilms().add(films);
		films.getGenres().add(genre);
	}
	
	Films dbFilms = filmsDao.save(films);
	return new FilmsData(dbFilms);
}

	private void setFilmsFields(Films films, FilmsData filmsData) {
		films.setFilmname(films.getFilmname());
		films.setGenres(films.getGenres());
		films.setStudio(films.getStudio());
		films.setFilmsId(films.getFilmsId());
		
	}

	private Films findOrCreateFilms(Long filmsId) {
		Films films;
		
		if(Objects.isNull(filmsId)) {
			films = new Films(); 
		}
		else {
			films = findFilmsById(filmsId);
		
		}
		return films;
	}

	private Films findFilmsById(Long filmsId) {
		return filmsDao.findById(filmsId)
				.orElseThrow(() -> new NoSuchElementException(
						"Films with ID=" + "does not exist."));
	}
		@Transactional(readOnly = true)
		public FilmsData retrieveFilmsById(Long studioId, Long filmId) {
			findStudioById(studioId); 
			Films films = findFilmsById(filmId); 
			
			if(films.getStudio().getStudioId() != studioId) {
				throw new IllegalStateException("Films with ID=" + filmId +
						" is not owned by studio with ID=" + studioId); 
				
			}
			
			return new FilmsData(films); 
	}
 
}
