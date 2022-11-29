package komix.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the creator_socials database table.
 * 
 */
@Entity
@Table(name="creator_socials")
@NamedQuery(name="CreatorSocial.findAll", query="SELECT c FROM CreatorSocial c")
public class CreatorSocial implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCreator_socials;

	private String link;

	private String nick;

	private String platform;

	@Lob
	@Column(name="uploader_comment")
	private String uploaderComment;

	//bi-directional many-to-one association to Creator
	@ManyToOne
	private Creator creator;

	public CreatorSocial() {
	}

	public int getIdCreator_socials() {
		return this.idCreator_socials;
	}

	public void setIdCreator_socials(int idCreator_socials) {
		this.idCreator_socials = idCreator_socials;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getNick() {
		return this.nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPlatform() {
		return this.platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getUploaderComment() {
		return this.uploaderComment;
	}

	public void setUploaderComment(String uploaderComment) {
		this.uploaderComment = uploaderComment;
	}

	public Creator getCreator() {
		return this.creator;
	}

	public void setCreator(Creator creator) {
		this.creator = creator;
	}

}