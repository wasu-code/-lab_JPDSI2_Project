package komix.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import komix.entities.Entry;

@Stateless
public class EntryDAO {
	@PersistenceContext()
	EntityManager em;
	
	public void insert(Entry entry) {
		em.persist(entry);
	}
	
	public Entry update(Entry entry) {
		return em.merge(entry);
	}
	
	public void delete (Entry entry) {
		em.remove(entry);
	}
	
	public Entry get(Object id) {
		return em.find(Entry.class, id);
	}
	
	public List<Entry> getEntries() {
		return em.createQuery("SELECT e from Entry e", Entry.class).getResultList();
	}
	
	public List<Entry> search(String title) {
		return em.createQuery("SELECT e from Entry e WHERE e.titleEng LIKE :title OR e.titleOrg LIKE :title", Entry.class)
				.setParameter("title", "%" + title + "%")
				.getResultList();
	}

}
