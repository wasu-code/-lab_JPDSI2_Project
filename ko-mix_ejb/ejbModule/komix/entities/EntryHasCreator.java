package komix.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the entry_has_creator database table.
 * 
 */
@Entity
@Table(name="entry_has_creator")
@NamedQuery(name="EntryHasCreator.findAll", query="SELECT e FROM EntryHasCreator e")
public class EntryHasCreator implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EntryHasCreatorPK id;

	private String role;

	//bi-directional many-to-one association to Creator
	@ManyToOne
	@JoinColumn(name="Creator_idCreator", insertable=false, updatable=false)//??
	private Creator creator;

	//bi-directional many-to-one association to Entry
	@ManyToOne
	@JoinColumn(name="Entry_idEntry", insertable=false, updatable=false)//??
	private Entry entry;

	public EntryHasCreator() {
	}

	public EntryHasCreatorPK getId() {
		return this.id;
	}

	public void setId(EntryHasCreatorPK id) {
		this.id = id;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Creator getCreator() {
		return this.creator;
	}

	public void setCreator(Creator creator) {
		this.creator = creator;
	}

	public Entry getEntry() {
		return this.entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}

}