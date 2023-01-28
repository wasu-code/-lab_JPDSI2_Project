package komix.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import komix.entities.Creator;

@Stateless
public class AuthorDAO {
	@PersistenceContext()
	EntityManager em;
	
	public void insert(Creator creator) {
		em.persist(creator);
	}
	
	public Creator update(Creator creator) {
		return em.merge(creator);
	}
	
	public void delete(Creator creator) {
		em.remove(creator);
	}
	
	public Creator get(Object id) {
		return em.find(Creator.class, id);
	}
	
	public List<Creator> getCreators() {
		return em.createQuery("SELECT c FROM Creator c", Creator.class).getResultList();
	}
	
	public List<Creator> searchCreators(String searchTerm) {
	    // use the entities manager to perform the search
	    Query query = em.createQuery("SELECT c FROM Creator c WHERE c.firstName LIKE :searchTerm OR c.lastName LIKE :searchTerm OR c.nickname LIKE :searchTerm OR c.aliases LIKE :searchTerm");
	    query.setParameter("searchTerm", "%" + searchTerm + "%");
	    return query.getResultList();
	}
	
}
