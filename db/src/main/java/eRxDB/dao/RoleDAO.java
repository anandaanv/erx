package eRxDB.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import eRxDB.Role;
import eRxDB.persistence.PersistenceWrapper;

public enum RoleDAO {
	
	S_INSTANCE;
	
	private EntityManager em = PersistenceWrapper.getEntitymanager();

	public Role findByRoleName(String roleName) throws NoResultException{
		Query q = em.createNamedQuery("Role.FindByName");
		q.setParameter("roleName", roleName);
		return (Role) q.getSingleResult();
	}
	
}
