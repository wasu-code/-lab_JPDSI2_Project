package com.komix.entry;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;

import komix.dao.AuthorDAO;
import komix.entities.Creator;

@Named
@RequestScoped
public class AuthorEditBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private Creator author = new Creator();

	@EJB
	AuthorDAO authorDAO;

	@Inject
	FacesContext context;

	@Inject
	Flash flash;

	public Creator getAuthor() {
		return author;
	}

	public void setAuthor(Creator author) {
		this.author = author;
	}

	public void load() {
		if (author.getIdCreator() != 0) {
			author = authorDAO.get(author.getIdCreator());
		}
	}

	public String save() {
		if (author.getIdCreator() == 0) {// new author
			try {
				authorDAO.insert(author);
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dodano autora do bazy", null));
				
			} catch (Exception e) {
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu", null));
				return "";
			}
		} else {
			try {
				authorDAO.update(author);
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Zaktualizowano dane autora", null));
				
			} catch (Exception e) {
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu", null));
				return "";
			}
		}
		return "/index";
	}

}
