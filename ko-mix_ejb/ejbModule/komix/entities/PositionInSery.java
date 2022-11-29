package komix.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the position_in_series database table.
 * 
 */
@Entity
@Table(name="position_in_series")
@NamedQuery(name="PositionInSery.findAll", query="SELECT p FROM PositionInSery p")
public class PositionInSery implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPosition_in_series;

	private int position;

	//bi-directional many-to-one association to Entry
	@OneToMany(mappedBy="positionInSery")
	private List<Entry> entries;

	//bi-directional many-to-one association to Sery
	@ManyToOne
	@JoinColumn(name="Series_idSeries")
	private Sery sery;

	public PositionInSery() {
	}

	public int getIdPosition_in_series() {
		return this.idPosition_in_series;
	}

	public void setIdPosition_in_series(int idPosition_in_series) {
		this.idPosition_in_series = idPosition_in_series;
	}

	public int getPosition() {
		return this.position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public List<Entry> getEntries() {
		return this.entries;
	}

	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}

	public Entry addEntry(Entry entry) {
		getEntries().add(entry);
		entry.setPositionInSery(this);

		return entry;
	}

	public Entry removeEntry(Entry entry) {
		getEntries().remove(entry);
		entry.setPositionInSery(null);

		return entry;
	}

	public Sery getSery() {
		return this.sery;
	}

	public void setSery(Sery sery) {
		this.sery = sery;
	}

}