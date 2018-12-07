package erxdb.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceWrapper extends Persistence {

	private static EntityManagerFactory emfactory = Persistence.
			createEntityManagerFactory("erxdb");
	private static EntityManager entitymanager = emfactory.
			createEntityManager( );
	
	public static EntityManager getEntitymanager() {
		return entitymanager;
	}

	public static void save(Object o){
		
//		entitymanager.getTransaction( ).begin( );
		
		entitymanager.persist( o );
//		entitymanager.getTransaction( ).commit( );		
	}
	
	public static void close(){
		entitymanager.close( );
		emfactory.close( );
	}
	
	
}
