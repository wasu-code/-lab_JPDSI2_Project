package com.komix.user;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import javax.inject.Inject;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.application.FacesMessage;

import komix.dao.UserDAO;
import komix.entities.User;

@Named
@ViewScoped
public class UserRegisterBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private User user = new User();
	private User loaded = null;
	
	@EJB
	UserDAO userDAO;
	
	@Inject
	FacesContext context;
	
	@Inject
	Flash flash;

	public User getUser() {
		return user;
	}
	
	public String register() {

		try {
			user.setAccountrole("user");
			//++ sprawdunikalność loginu
			if (userDAO.isLoginAvailable(user.getLogin())) {
				userDAO.insert(user);
			} else {
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "login zajęty", null));
				return "register";
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu" + e.getMessage(), null));
			return "error";
		}

		return "index";
	}
	
	public void onLoad() throws java.io.IOException {
		// 1. load person passed through session
		// HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		// loaded = (Person) session.getAttribute("person");

		// 2. load person passed through flash
		/*loaded = (User) flash.get("user");

		// cleaning: attribute received => delete it from session
		if (loaded != null) {
			user = loaded;
			// session.removeAttribute("person");
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
			// if (!context.isPostback()) { //possible redirect
			// context.getExternalContext().redirect("personList.xhtml");
			// context.responseComplete();
			// }
		}*/

	}
	
}
