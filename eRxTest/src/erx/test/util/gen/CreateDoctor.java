package erx.test.util.gen;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.pdfbox.io.IOUtils;

import com.erx.service.handler.ERxHelper;

import eRxDB.Certificate;
import eRxDB.Doctor;
import eRxDB.persistence.PersistenceWrapper;

public class CreateDoctor {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Doctor doc = new Doctor();
		doc.setName("Arati Vaidya");
		doc.setRegno("66555332");
		doc.setQualification("MD");
		String username = "arati";
		doc.setUsername(username);
		String password = "password";
		
		doc.setEncryptedPassword(ERxHelper.encrypt(password));
		
		Certificate cer = new Certificate();
		cer.setKey(ERxHelper.encrypt(username));
		cer.setSignImage(IOUtils.toByteArray(new FileInputStream("resources/certificate.png")));
		cer.setValue(IOUtils.toByteArray(new FileInputStream("resources/keystore.pfx")));
		
		
		PersistenceWrapper.getEntitymanager().getTransaction().begin();
		PersistenceWrapper.getEntitymanager().persist(doc);
		PersistenceWrapper.getEntitymanager().persist(cer);
		PersistenceWrapper.getEntitymanager().getTransaction().commit();
	}
	
	
	
	/**
	 * @deprecated Use {@link ERxHelper#encrypt(String)} instead
	 */
	public static String encrypt(String passwordToHash){
		return ERxHelper.encrypt(passwordToHash);
	}
}
