package com.komix.entry;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;

import komix.dao.AuthorDAO;
import komix.entities.Creator;

@Named
@RequestScoped
public class AuthorListBB implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Creator author = new Creator();
	private String searchTerm;
	private List<Creator> authors;
	private List<Creator> searchResults;
	
	@EJB
	AuthorDAO authorDAO;
	
	@Inject
	FacesContext context;
	
	@Inject
	Flash flash;
	
	public Creator getAuthor() {
		return author;
	}
	
	public String getSearchTerm() {
	    return searchTerm;
	}
	
	public void setSearchTerm(String searchTerm) {
	    this.searchTerm = searchTerm;
	}
	
	public List<Creator> getSearchResults() {
		return searchResults;
	}
	
	public List<Creator> getAuthors() {
		authors = authorDAO.getCreators();
		return authors;
	}
	
	public void searchAuthors() {
		searchResults = authorDAO.searchCreators(searchTerm);
	}

}
