package com.komix.entry;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;


import komix.dao.EntryDAO;
import komix.entities.Entry;

@Named
@RequestScoped
public class EntryListBB  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Entry entry = new Entry();
	private List<Entry> entries;
	private String searchTitle = "";
	
	@EJB
	EntryDAO entryDAO;
	
	@Inject
	FacesContext context;
	
	@Inject
	Flash flash;
	
	public Entry getEntry() {
		return entry;
	}
	
	public String getSearchTitle() {
		return searchTitle;
	}
	
	public void setSearchTitle(String searchTitle) {
		this.searchTitle = searchTitle; 
	}
	
	public List<Entry> getEntries() {
		//entries = entryDAO.getEntries();
		entries = entryDAO.search(searchTitle);
        return entries;
    }
	
}
