package com.erx.client.impl;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.erx.beans.Diagnosys;
import com.erx.beans.Medicine;
import com.erx.obj.Dose;
import com.erx.obj.Patient;
import com.erx.obj.PrescriptionRow;
import com.erx.ops.Session;

public class ObjectsProvider {
	private ObjectsProvider(){};
	
	public static Session newSession(Patient p, List<String> diagnosys){
		SessionImpl session = new SessionImpl(UUID.randomUUID().toString(), p);
		int i = 0;
		for (String d : diagnosys) {
			session.addDiagnosys(new Diagnosys(i++, d));
		}
		return session;
	}

	public static PrescriptionRow getPrescription(Medicine medicine, Set<Dose> doses, int numUnits) {
		return new PrescriptionRow(medicine.getId(), medicine, doses, numUnits);
	}

}
