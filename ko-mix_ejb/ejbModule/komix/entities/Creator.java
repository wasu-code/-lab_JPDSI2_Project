package komix.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the creator database table.
 * 
 */
@Entity
@NamedQuery(name="Creator.findAll", query="SELECT c FROM Creator c")
public class Creator implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCreator;

	private String aliases;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	private String nickname;

	//bi-directional many-to-one association to CreatorSocial
	@OneToMany(mappedBy="creator")
	private List<CreatorSocial> creatorSocials;

	//bi-directional many-to-one association to EntryHasCreator
	@OneToMany(mappedBy="creator")
	private List<EntryHasCreator> entryHasCreators;

	public Creator() {
	}

	public int getIdCreator() {
		return this.idCreator;
	}

	public void setIdCreator(int idCreator) {
		this.idCreator = idCreator;
	}

	public String getAliases() {
		return this.aliases;
	}

	public void setAliases(String aliases) {
		this.aliases = aliases;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public List<CreatorSocial> getCreatorSocials() {
		return this.creatorSocials;
	}

	public void setCreatorSocials(List<CreatorSocial> creatorSocials) {
		this.creatorSocials = creatorSocials;
	}

	public CreatorSocial addCreatorSocial(CreatorSocial creatorSocial) {
		getCreatorSocials().add(creatorSocial);
		creatorSocial.setCreator(this);

		return creatorSocial;
	}

	public CreatorSocial removeCreatorSocial(CreatorSocial creatorSocial) {
		getCreatorSocials().remove(creatorSocial);
		creatorSocial.setCreator(null);

		return creatorSocial;
	}

	public List<EntryHasCreator> getEntryHasCreators() {
		return this.entryHasCreators;
	}

	public void setEntryHasCreators(List<EntryHasCreator> entryHasCreators) {
		this.entryHasCreators = entryHasCreators;
	}

	public EntryHasCreator addEntryHasCreator(EntryHasCreator entryHasCreator) {
		getEntryHasCreators().add(entryHasCreator);
		entryHasCreator.setCreator(this);

		return entryHasCreator;
	}

	public EntryHasCreator removeEntryHasCreator(EntryHasCreator entryHasCreator) {
		getEntryHasCreators().remove(entryHasCreator);
		entryHasCreator.setCreator(null);

		return entryHasCreator;
	}

}