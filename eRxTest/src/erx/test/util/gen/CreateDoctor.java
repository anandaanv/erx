package erx.test.util.gen;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.pdfbox.io.IOUtils;

import com.erx.ex.UserNotFoundException;
import com.erx.obj.Certificate;
import com.erx.obj.Doctor;
import com.erx.obj.User;
import com.erx.service.handler.ERxHelper;
import com.erx.user.doctor.RegisterNewDoctor;
import com.erx.user.doctor.RegisterUser;

import eRxDB.Role;
import eRxDB.persistence.PersistenceWrapper;

public class CreateDoctor {

	public static void main(String[] args) throws FileNotFoundException, IOException, UserNotFoundException {
//		createRoles();
		
		createDoctor();
	}



	private static void createRoles() {
		Role doc = new Role();
		doc.setRoleName("DOCTOR");
		Role user = new Role();
		user.setRoleName("USER");
		Role pharma = new Role();
		pharma.setRoleName("PHARMA");
		
		PersistenceWrapper.getEntitymanager().getTransaction().begin();
		PersistenceWrapper.save(doc);
		PersistenceWrapper.save(user);
		PersistenceWrapper.save(pharma);
		PersistenceWrapper.getEntitymanager().getTransaction().commit();
		
	}



	private static void createDoctor() throws IOException, FileNotFoundException, UserNotFoundException {
		User user = new User();
		user.setEmail("anand@dheemantech.com");
		String password = "password";
		user.setEncryptedPassword(ERxHelper.encrypt(password));
		user.setName("Anand Vaidya");
		user.setPhoneNumber("7722078812");
		user.setUserId("anand1711");
		
		RegisterUser reg = new RegisterUser(user);
		reg.processRegistration();
		reg.completeRegistration();
		 
		
		Doctor doc = new Doctor(0, "Anand Vaidya", "MD", "66555332", user);
		Certificate cer = new Certificate();
		cer.setUser(user);
		cer.setSignImage(IOUtils.toByteArray(new FileInputStream("resources/certificate.png")));
		cer.setCertificate(IOUtils.toByteArray(new FileInputStream("resources/keystore.pfx")));
		
		RegisterNewDoctor docReg = new RegisterNewDoctor(doc);
		docReg.processRegistration();
		
		docReg.updateCertificate(cer);
	}
}
