package com.komix.user;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;

import komix.dao.UserDAO;
import komix.entities.User;

@Named
@RequestScoped
public class UserListBB implements Serializable {
	private static final long serialVersionUID = 1L;
	private User user = new User();
	private String searchTerm;
	private List<User> searchResults;
	private String selectedRole;
	
	@EJB
	UserDAO userDAO;
	
	@Inject
	FacesContext context;
	
	@Inject
	Flash flash;
	
	public void searchUsers() {
		searchResults = userDAO.searchUsers(searchTerm);
	}
	
	//

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	public List<User> getSearchResults() {
		return searchResults;
	}

	public void setSearchResults(List<User> searchResults) {
		this.searchResults = searchResults;
	}

	public String getSelectedRole() {
		return selectedRole;
	}

	public void setSelectedRole(String selectedRole) {
		this.selectedRole = selectedRole;
	}
	

}
