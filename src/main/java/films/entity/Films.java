package films.entity;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Entity
@Data
public class Films {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long filmsId;
	private String filmname; 
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "film_genre", joinColumns = @JoinColumn(name = "films_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
	private Set<Genre> genres = new HashSet<>(); 
	
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "studio_id")
	private Studio studio;


	
	
	
}


