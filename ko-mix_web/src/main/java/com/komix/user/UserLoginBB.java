package com.komix.user;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.simplesecurity.RemoteClient;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import komix.dao.UserDAO;
import komix.entities.User;

@Named
@RequestScoped
public class UserLoginBB implements Serializable{
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
	
	public String login() {
		FacesContext ctx = FacesContext.getCurrentInstance();

		try {
			//userDAO.insert(user);
			List<User> u = userDAO.getUser(user.getLogin(), user.getPassword());
			if(u.isEmpty()) {
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne dane logowania", null));
				return null;
			} else {
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Dane poprawne. Rola: " + u.get(0).getAccountrole(), null));
				
				//save in RemoteClient and store it in session
				RemoteClient<User> client = new RemoteClient<User>(); //create new RemoteClient
				client.setDetails(u.get(0));
				client.getRoles().add(u.get(0).getAccountrole());
			
				//store RemoteClient with request info in session (needed for SecurityFilter)
				HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
				client.store(request);
				
				//add to session(?)
				ctx.getExternalContext().getSessionMap().put("role",u.get(0).getAccountrole());

				// and enter the system (now SecurityFilter will pass the request)
				return "index";
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas odczytu z bazy danych" + e.getMessage(), null));
			return "error";
		}

	}
	
	public String logout(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		//Invalidate session
		// - all objects within session will be destroyed
		// - new session will be created (with new ID)
		session.invalidate();
		return "index";
	}
	
	public void onLoad() throws java.io.IOException {
		// 1. load person passed through session
		//HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		//loaded = (User) session.getAttribute("role");

		// 2. load person passed through flash
		/*loaded = (User) flash.get("role");

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
