package komix.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the source database table.
 * 
 */
@Entity
@NamedQuery(name="Source.findAll", query="SELECT s FROM Source s")
public class Source implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSource;

	@Lob
	private String isOfficial;

	private String link;

	private String platform;

	private String price;

	//bi-directional many-to-one association to Entry
	@ManyToOne
	private Entry entry;

	public Source() {
	}

	public int getIdSource() {
		return this.idSource;
	}

	public void setIdSource(int idSource) {
		this.idSource = idSource;
	}

	public String getIsOfficial() {
		return this.isOfficial;
	}

	public void setIsOfficial(String isOfficial) {
		this.isOfficial = isOfficial;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPlatform() {
		return this.platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Entry getEntry() {
		return this.entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}

}