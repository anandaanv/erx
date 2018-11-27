package com.erx.ops;

import com.erx.beans.Diagnosys;
import com.erx.beans.Medicine;
import com.erx.obj.Dose;
import java.util.List;
import java.util.Set;

import com.erx.obj.Patient;
import com.erx.obj.PrescriptionRow;

public interface Session {

	List<Diagnosys> getDiagnosys();

	List<PrescriptionRow> getPrescriptions();

	void addDiagnosys(Diagnosys d);

	void addPrescription(PrescriptionRow row);

	List<Medicine> getPossibleMedicines();

	void addPrescription(Medicine medicine, Set<Dose> doses, int numUnits);

	Patient getPatient();

	String getId();

	void setPrescriptionId(int prescriptionId);

	int getPrescriptionId();
	
	String getCertPassword();
	void setCertPassword(String pwd);

}