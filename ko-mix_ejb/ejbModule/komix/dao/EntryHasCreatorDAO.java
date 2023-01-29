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
	
	public List<EntryHasCreator> getCreators(Entry entry){
		List<EntryHasCreator> l = em.createQuery(
				"SELECT c FROM EntryHasCreator c WHERE Entry_idEntry=:idEntry")
				.setParameter("idEntry", entry.getIdEntry())
				.getResultList();
		
		return l;
	}

	public void addCreatorToEntry(int idEntry, int idCreator) {
		em.createNativeQuery("INSERT INTO entry_has_creator (Entry_idEntry, Creator_idCreator, role) VALUES (:idEntry, :idCreator, NULL)")
			.setParameter("idEntry", idEntry)
			.setParameter("idCreator", idCreator)
			.executeUpdate();
	}
}
