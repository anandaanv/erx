package erxdb.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import erxdb.User;
import erxdb.persistence.PersistenceWrapper;

public enum UserDAO {
	
	S_INSTANCE;
	
	private EntityManager em = PersistenceWrapper.getEntitymanager();

	public User findByUserId(String userId) throws NoResultException{
		Query q = em.createNamedQuery("User.FindById");
		q.setParameter("userId", userId);
		return (User) q.getSingleResult();
	}
	
	
	public User findByUserIdAndPassword(String userId, String password) throws NoResultException{
		Query q = em.createNamedQuery("User.FindByIdAndPassword");
		q.setParameter("userId", userId);
		q.setParameter("password", password);
		return (User) q.getSingleResult();
	}

	public void saveUser(erxdb.User u) {
		em.persist(u);
	}
	
}
