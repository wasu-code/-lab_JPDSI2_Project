package komix.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUser;

	private String accountrole;

	private String email;

	private String login;

	private String password;

	//bi-directional many-to-many association to ReadingStatus
	@ManyToMany
	@JoinTable(
		name="user_has_reading_status"
		, joinColumns={
			@JoinColumn(name="User_idUser")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Reading_status_idReading_status")
			}
		)
	private List<ReadingStatus> readingStatuses;

	public User() {
	}

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getAccountrole() {
		return this.accountrole;
	}

	public void setAccountrole(String accountrole) {
		this.accountrole = accountrole;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<ReadingStatus> getReadingStatuses() {
		return this.readingStatuses;
	}

	public void setReadingStatuses(List<ReadingStatus> readingStatuses) {
		this.readingStatuses = readingStatuses;
	}

}