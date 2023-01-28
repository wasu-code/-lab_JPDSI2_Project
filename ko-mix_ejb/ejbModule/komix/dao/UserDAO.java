package komix.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import komix.entities.Creator;
import komix.entities.User;

@Stateless
public class UserDAO {
	@PersistenceContext()
	EntityManager em;

	public void insert(User user) {//stwórz encję
		em.persist(user);
	}
	
	public User update(User user) {
		return em.merge(user);
	}
	
	public void delete (User user) {
		em.remove(em.merge(user));
	}
	
	public User get(Object id) {
		return em.find(User.class, id);
	}
	
	public List<User> getUser(String login, String pass) {

		List<User> u = em.createQuery(
			    "SELECT role FROM User as role WHERE login = :login AND password = :pass")
			    .setParameter("login", login)
			    .setParameter("pass", pass)
			    .setMaxResults(1)
			    .getResultList();
		return u;
	}
	
	public boolean isLoginAvailable(String login) {
		List<User> u = em.createQuery(
			    "SELECT role FROM User as role WHERE login = :login")
			    .setParameter("login", login)
			    .setMaxResults(1)
			    .getResultList();
		return u.isEmpty();
	}
	
	public List<User> searchUsers(String searchTerm) {
	    Query query = em.createQuery("SELECT u FROM User u WHERE u.login LIKE :searchTerm");
	    query.setParameter("searchTerm", "%" + searchTerm + "%");
	    return query.getResultList();
	}
	
}
