package eRxDB.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import eRxDB.UserRole;
import eRxDB.persistence.PersistenceWrapper;

public enum UserRoleDAO {
	
	S_INSTANCE;
	
	private EntityManager em = PersistenceWrapper.getEntitymanager();

	public UserRole findByUserId(String userId, String roleName) throws NoResultException{
		Query q = em.createNamedQuery("UserRole.FindByUserRole");
		q.setParameter("user", UserDAO.S_INSTANCE.findByUserId(userId));
		q.setParameter("role", RoleDAO.S_INSTANCE.findByRoleName(roleName));
		return (UserRole) q.getSingleResult();
	}

	public void saveUserRole(eRxDB.UserRole u) {
		em.persist(u);
	}
	
}
