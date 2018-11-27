package eRxDB.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import eRxDB.Doctor;
import eRxDB.UserRole;
import eRxDB.persistence.PersistenceWrapper;

public enum DoctorDAO {
	
	S_INSTANCE;
	
	private EntityManager em = PersistenceWrapper.getEntitymanager();

	public void saveDoctor(Doctor doc){
		em.persist(doc);
		em.refresh(doc);
	}
	
	public Doctor findByUserId(String userId) throws NoResultException{
		UserRole role = UserRoleDAO.S_INSTANCE.findByUserId(userId, "DOCTOR");
		return em.find(Doctor.class, role.getRefId());
	}
	
}
