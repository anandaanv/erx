package eRxTest;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.erx.beans.Medicine;
import com.erx.cache.MedicineCache;
import com.erx.client.ifc.SessionManager;
import com.erx.client.impl.SessionManagerImpl;
import com.erx.obj.Dose;
import com.erx.obj.Patient;
import com.erx.ops.Session;
import com.erx.service.input.ERxCreateOrderOutput;

public class ClientTest {

	@Before
	public void init(){
//		CacheInitializer.initialize();
		MedicineCache.S_INSTANCE.toString();
	}
	
	@Test
	public void test() {
//		createPr("Mayur", "8149707832");
//		createPr("Arati", "9823041332", "0000000001");
//		createPr("Gaurish", "9890820441", "0000000004");
//		createPr("Kushal", "8983328793", "0000000003");
	}

	private void createPr(String name, String phoneNumber, String patientId) {
		SessionManager mgr = new SessionManagerImpl(); 
		Patient p = new Patient();
		p.setId(patientId);
//		p.setName("Anand");
//		p.setPhoneNumber("7722078812");
		p.setName(name);
		p.setPhoneNumber(phoneNumber);
		
		Session session = mgr.createSession(p);
		session.addDiagnosys(CacheInitializer.D1);
		session.addDiagnosys(CacheInitializer.D2);
		List<Medicine> meds = session.getPossibleMedicines();
		if (meds.isEmpty()) {
			System.out.println("meds is empty..................................");
		}
		session.addPrescription(meds.get(0), Collections.singleton(Dose.AFTER_BREAKFAST), 30);
		session.addPrescription(meds.get(2), Collections.singleton(Dose.BEFORE_SLEEPING), 30);
		ERxCreateOrderOutput op = (ERxCreateOrderOutput) mgr.prepareSession(session);
		session.setPrescriptionId(op.getId());
		mgr.completeSession(session);
	}
	
	@Test
	public void testAkshay() {
		SessionManager mgr = new SessionManagerImpl(); 
		Patient p = new Patient();
		p.setId("0000000002");
		p.setName("Akshay");
		p.setPhoneNumber("9922996630");
		Session session = mgr.createSession(p);
		session.addDiagnosys(CacheInitializer.D1);
		session.addDiagnosys(CacheInitializer.D2);
		List<Medicine> meds = session.getPossibleMedicines();
		if (meds.isEmpty()) {
			System.out.println("meds is empty..................................");
		}
		session.addPrescription(meds.get(0), Collections.singleton(Dose.AFTER_BREAKFAST), 30);
		session.addPrescription(meds.get(2), Collections.singleton(Dose.BEFORE_SLEEPING), 30);
		ERxCreateOrderOutput op = (ERxCreateOrderOutput) mgr.prepareSession(session);
		session.setPrescriptionId(op.getId());
		session.setCertPassword("anandpass");
		mgr.completeSession(session);
	}

}
