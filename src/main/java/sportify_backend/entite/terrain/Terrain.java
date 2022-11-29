package sportify_backend.entite.terrain;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "terrain")
public class Terrain {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private int numberOfPlayer;
	private String disponibility_from;
	private String disponibility_to;
	private int price;
	@Column(name = "imgfilename")
	private String imgFileName;
	private String location;
	private String description;
}
