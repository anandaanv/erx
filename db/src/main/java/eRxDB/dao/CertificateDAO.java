package eRxDB.dao;

import javax.persistence.EntityManager;

import eRxDB.Certificate;
import eRxDB.persistence.PersistenceWrapper;

public enum CertificateDAO {
	
	S_INSTANCE;
	
	private EntityManager em = PersistenceWrapper.getEntitymanager();

	public void saveCertificate(Certificate c) {
		em.persist(c);
	}
	
}
