package komix.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the series database table.
 * 
 */
@Entity
@Table(name="series")
@NamedQuery(name="Sery.findAll", query="SELECT s FROM Sery s")
public class Sery implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSeries;

	@Lob
	private String description;

	private String name;

	//bi-directional many-to-one association to PositionInSery
	@OneToMany(mappedBy="sery")
	private List<PositionInSery> positionInSeries;

	public Sery() {
	}

	public int getIdSeries() {
		return this.idSeries;
	}

	public void setIdSeries(int idSeries) {
		this.idSeries = idSeries;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PositionInSery> getPositionInSeries() {
		return this.positionInSeries;
	}

	public void setPositionInSeries(List<PositionInSery> positionInSeries) {
		this.positionInSeries = positionInSeries;
	}

	public PositionInSery addPositionInSery(PositionInSery positionInSery) {
		getPositionInSeries().add(positionInSery);
		positionInSery.setSery(this);

		return positionInSery;
	}

	public PositionInSery removePositionInSery(PositionInSery positionInSery) {
		getPositionInSeries().remove(positionInSery);
		positionInSery.setSery(null);

		return positionInSery;
	}

}