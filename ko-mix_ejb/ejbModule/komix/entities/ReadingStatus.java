package komix.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the reading_status database table.
 * 
 */
@Entity
@Table(name="reading_status")
@NamedQuery(name="ReadingStatus.findAll", query="SELECT r FROM ReadingStatus r")
public class ReadingStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idReading_status;

	private int chapters;

	private int pages;

	private int volumes;

	//bi-directional many-to-one association to Entry
	@ManyToOne
	private Entry entry;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="readingStatuses")
	private List<User> users;

	public ReadingStatus() {
	}

	public int getIdReading_status() {
		return this.idReading_status;
	}

	public void setIdReading_status(int idReading_status) {
		this.idReading_status = idReading_status;
	}

	public int getChapters() {
		return this.chapters;
	}

	public void setChapters(int chapters) {
		this.chapters = chapters;
	}

	public int getPages() {
		return this.pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getVolumes() {
		return this.volumes;
	}

	public void setVolumes(int volumes) {
		this.volumes = volumes;
	}

	public Entry getEntry() {
		return this.entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}