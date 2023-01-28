package komix.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import komix.entities.Creator;
import komix.entities.Entry;
import komix.entities.EntryHasCreator;

@Stateless
public class EntryHasCreatorDAO {
	@PersistenceContext()
	EntityManager em;

	public List<Creator> getCreatorsForEntity(int idEntry) {
		List<EntryHasCreator> l = em.createQuery(
				"SELECT c FROM EntryHasCreator c WHERE id_entry=:idEntry")
				.setParameter("idEntry", idEntry)
				.getResultList();
		
		List<Creator> c = new ArrayList<>();
		for(EntryHasCreator x : l) {
			c.add(x.getCreator());
		}
		return c;
	}
	
	public void addCreatorToEntry(int idEntry, int idCreator) {
	    // retrieve the selected creator from the database using the entities manager
		Entry entry = em.find(Entry.class, idEntry);
	    Creator creator = em.find(Creator.class, idCreator);
	    // create a new EntryHasCreator object
	    EntryHasCreator entryHasCreator = new EntryHasCreator();
	    entryHasCreator.setCreator(creator);
	    entryHasCreator.setEntry(entry);
	    // add the EntryHasCreator object to the entry's list of creators
	    entry.getEntryHasCreators().add(entryHasCreator);
	}
}
