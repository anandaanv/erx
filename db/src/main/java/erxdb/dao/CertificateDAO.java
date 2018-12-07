package erxdb.dao;

import javax.persistence.EntityManager;

import erxdb.Certificate;
import erxdb.persistence.PersistenceWrapper;

public enum CertificateDAO {
	
	S_INSTANCE;
	
	private EntityManager em = PersistenceWrapper.getEntitymanager();

	public void saveCertificate(Certificate c) {
		em.persist(c);
	}
	
}
