package komix.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the entry_has_creator database table.
 * 
 */
@Embeddable
public class EntryHasCreatorPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int entry_idEntry;

	@Column(insertable=false, updatable=false)
	private int creator_idCreator;

	public EntryHasCreatorPK() {
	}
	public int getEntry_idEntry() {
		return this.entry_idEntry;
	}
	public void setEntry_idEntry(int entry_idEntry) {
		this.entry_idEntry = entry_idEntry;
	}
	public int getCreator_idCreator() {
		return this.creator_idCreator;
	}
	public void setCreator_idCreator(int creator_idCreator) {
		this.creator_idCreator = creator_idCreator;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EntryHasCreatorPK)) {
			return false;
		}
		EntryHasCreatorPK castOther = (EntryHasCreatorPK)other;
		return 
			(this.entry_idEntry == castOther.entry_idEntry)
			&& (this.creator_idCreator == castOther.creator_idCreator);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.entry_idEntry;
		hash = hash * prime + this.creator_idCreator;
		
		return hash;
	}
}