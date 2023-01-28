package com.komix.user;

import java.io.Serializable;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;

import komix.dao.UserDAO;
import komix.entities.User;

@Named
@RequestScoped
public class UserEditBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private User user = new User();
	private String selectedRole;
	private String role;

	@EJB
	UserDAO userDAO;

	@Inject
	FacesContext context;

	@Inject
	Flash flash;

	public void updateRole() {
		role = user.getAccountrole();
		if (user.getIdUser() != 0) {
			try {
				user = userDAO.get(user.getIdUser());
				user.setAccountrole(role);
				// zapisz zmiany
				userDAO.update(user);
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "zaktualizowano", null));

			} catch (Exception e) {
				e.printStackTrace();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"wystąpił błąd podczas aktualizacji" + e.getMessage(), null));

			}
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nie wybrano użytkownika do edycji", null));

		}


	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void load() {
		if (user.getIdUser() != 0) {
			user = userDAO.get(user.getIdUser());
		}
	}

	//

	public String getSelectedRole() {
		return selectedRole;
	}

	public void setSelectedRole(String selectedRole) {
		this.selectedRole = selectedRole;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
