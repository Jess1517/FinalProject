package films.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Studio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long studioId; 
	private String studioname; 
	

	@EqualsAndHashCode.Exclude 
	@ToString.Exclude 
	
	@OneToMany(mappedBy = "studio", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Films> films = new HashSet<>(); 

}






