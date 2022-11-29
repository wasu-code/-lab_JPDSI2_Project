package komix.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the entry database table.
 * 
 */
@Entity
@NamedQuery(name="Entry.findAll", query="SELECT e FROM Entry e")
public class Entry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idEntry;

	private String aliases;

	@Column(name="chapter_count")
	private int chapterCount;

	@Column(name="cover_url")
	private String coverUrl;

	private byte isLocked;

	@Column(name="original_lang")
	private String originalLang;

	@Column(name="page_count")
	private int pageCount;

	@Column(name="publish_year")
	private int publishYear;

	@Column(name="reading_direction")
	private String readingDirection;

	@Column(name="title_eng")
	private String titleEng;

	@Column(name="title_org")
	private String titleOrg;

	@Column(name="volume_count")
	private int volumeCount;

	//bi-directional many-to-one association to PositionInSery
	@ManyToOne
	@JoinColumn(name="Position_in_series_idPosition_in_series")
	private PositionInSery positionInSery;

	//bi-directional many-to-one association to Type
	@ManyToOne
	private Type type;

	//bi-directional many-to-one association to EntryHasCreator
	@OneToMany(mappedBy="entry")
	private List<EntryHasCreator> entryHasCreators;

	//bi-directional many-to-many association to Tag
	@ManyToMany(mappedBy="entries")
	private List<Tag> tags;

	//bi-directional many-to-one association to ReadingStatus
	@OneToMany(mappedBy="entry")
	private List<ReadingStatus> readingStatuses;

	//bi-directional many-to-one association to Source
	@OneToMany(mappedBy="entry")
	private List<Source> sources;

	public Entry() {
	}

	public int getIdEntry() {
		return this.idEntry;
	}

	public void setIdEntry(int idEntry) {
		this.idEntry = idEntry;
	}

	public String getAliases() {
		return this.aliases;
	}

	public void setAliases(String aliases) {
		this.aliases = aliases;
	}

	public int getChapterCount() {
		return this.chapterCount;
	}

	public void setChapterCount(int chapterCount) {
		this.chapterCount = chapterCount;
	}

	public String getCoverUrl() {
		return this.coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public byte getIsLocked() {
		return this.isLocked;
	}

	public void setIsLocked(byte isLocked) {
		this.isLocked = isLocked;
	}

	public String getOriginalLang() {
		return this.originalLang;
	}

	public void setOriginalLang(String originalLang) {
		this.originalLang = originalLang;
	}

	public int getPageCount() {
		return this.pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPublishYear() {
		return this.publishYear;
	}

	public void setPublishYear(int publishYear) {
		this.publishYear = publishYear;
	}

	public String getReadingDirection() {
		return this.readingDirection;
	}

	public void setReadingDirection(String readingDirection) {
		this.readingDirection = readingDirection;
	}

	public String getTitleEng() {
		return this.titleEng;
	}

	public void setTitleEng(String titleEng) {
		this.titleEng = titleEng;
	}

	public String getTitleOrg() {
		return this.titleOrg;
	}

	public void setTitleOrg(String titleOrg) {
		this.titleOrg = titleOrg;
	}

	public int getVolumeCount() {
		return this.volumeCount;
	}

	public void setVolumeCount(int volumeCount) {
		this.volumeCount = volumeCount;
	}

	public PositionInSery getPositionInSery() {
		return this.positionInSery;
	}

	public void setPositionInSery(PositionInSery positionInSery) {
		this.positionInSery = positionInSery;
	}

	public Type getType() {
		return this.type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public List<EntryHasCreator> getEntryHasCreators() {
		return this.entryHasCreators;
	}

	public void setEntryHasCreators(List<EntryHasCreator> entryHasCreators) {
		this.entryHasCreators = entryHasCreators;
	}

	public EntryHasCreator addEntryHasCreator(EntryHasCreator entryHasCreator) {
		getEntryHasCreators().add(entryHasCreator);
		entryHasCreator.setEntry(this);

		return entryHasCreator;
	}

	public EntryHasCreator removeEntryHasCreator(EntryHasCreator entryHasCreator) {
		getEntryHasCreators().remove(entryHasCreator);
		entryHasCreator.setEntry(null);

		return entryHasCreator;
	}

	public List<Tag> getTags() {
		return this.tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public List<ReadingStatus> getReadingStatuses() {
		return this.readingStatuses;
	}

	public void setReadingStatuses(List<ReadingStatus> readingStatuses) {
		this.readingStatuses = readingStatuses;
	}

	public ReadingStatus addReadingStatus(ReadingStatus readingStatus) {
		getReadingStatuses().add(readingStatus);
		readingStatus.setEntry(this);

		return readingStatus;
	}

	public ReadingStatus removeReadingStatus(ReadingStatus readingStatus) {
		getReadingStatuses().remove(readingStatus);
		readingStatus.setEntry(null);

		return readingStatus;
	}

	public List<Source> getSources() {
		return this.sources;
	}

	public void setSources(List<Source> sources) {
		this.sources = sources;
	}

	public Source addSource(Source source) {
		getSources().add(source);
		source.setEntry(this);

		return source;
	}

	public Source removeSource(Source source) {
		getSources().remove(source);
		source.setEntry(null);

		return source;
	}

}