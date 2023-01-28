package com.komix.entry;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;

import komix.dao.EntryDAO;
import komix.dao.EntryHasCreatorDAO;
import komix.entities.Creator;
import komix.entities.Entry;
import komix.entities.EntryHasCreator;

@Named
@RequestScoped
public class EntryEditBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private Entry entry = new Entry();
	private Creator creator = new Creator();
	private EntryHasCreator entryHasCreator = new EntryHasCreator();
	private List<Creator> creators;

	@EJB
	EntryDAO entryDAO;
	@EJB
	EntryHasCreatorDAO entryHasCreatorDAO;

	@Inject
	FacesContext context;

	@Inject
	Flash flash;

	public Entry getEntry() {
		return entry;
	}

	public Creator getCreator() {
		return creator;
	}

	public void addCreatorToEntry() {
		entryHasCreatorDAO.addCreatorToEntry(entry.getIdEntry(), creator.getIdCreator());
	}

	public void load() {
		if (entry.getIdEntry() != 0) {
			entry = entryDAO.get(entry.getIdEntry());

			/*
			 * creators = entryHasCreatorDAO.getCreatorsForEntity(entry.getIdEntry());
			 * creator = creators.get(0);
			 */
		}
	}

	public String save() {

		/* take parameters and update or add depending on wether id is received */
		if (entry.getIdEntry() == 0) {// new entry
			try {
				entryDAO.insert(entry);
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dodano pozycję do bazy", null));

			} catch (Exception e) {
				e.printStackTrace();
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu", null));
				return "";
			}
		} else {// update existing entry
			try {
				entryDAO.update(entry);
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Zaktualizowano dane pozycji", null));

			} catch (Exception e) {
				e.printStackTrace();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"wystąpił błąd podczas aktualizacji danych", null));
			}

		}
		if (creator.getIdCreator() != 0) {
			try {
				addCreatorToEntry();
			} catch (Exception e) {
				e.printStackTrace();
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas dodawania autora: ", null));

			}
		}

		return "/public/list";
	}

	public String lock(byte x) {
		load();
		entry.setIsLocked((byte) x);
		try {
			entryDAO.update(entry);
			if (x==1) {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "zablokowano edycję", null));
			} else {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "odblokowano edycję", null));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas aktualizacji danych", null));

		}
		return "/index";
	}

}
