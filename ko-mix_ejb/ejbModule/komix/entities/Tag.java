package komix.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tags database table.
 * 
 */
@Entity
@Table(name="tags")
@NamedQuery(name="Tag.findAll", query="SELECT t FROM Tag t")
public class Tag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idTags;

	private String description;

	private byte isNSFW;

	@Column(name="tag_name")
	private String tagName;

	//bi-directional many-to-many association to Entry
	@ManyToMany
	@JoinTable(
		name="entry_has_tags"
		, joinColumns={
			@JoinColumn(name="Tags_idTags")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Entry_idEntry")
			}
		)
	private List<Entry> entries;

	public Tag() {
	}

	public int getIdTags() {
		return this.idTags;
	}

	public void setIdTags(int idTags) {
		this.idTags = idTags;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte getIsNSFW() {
		return this.isNSFW;
	}

	public void setIsNSFW(byte isNSFW) {
		this.isNSFW = isNSFW;
	}

	public String getTagName() {
		return this.tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public List<Entry> getEntries() {
		return this.entries;
	}

	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}

}