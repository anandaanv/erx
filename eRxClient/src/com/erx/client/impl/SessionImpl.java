package com.erx.client.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.erx.beans.Diagnosys;
import com.erx.beans.Medicine;
import com.erx.cache.MedicineCache;
import com.erx.cache.MedicineDiagnosysCache;
import com.erx.obj.Dose;
import com.erx.obj.Patient;
import com.erx.obj.PrescriptionRow;
import com.erx.ops.Session;

public class SessionImpl implements Session {
	private static class Coverage implements Comparable<Coverage>{
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((med == null) ? 0 : med.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Coverage other = (Coverage) obj;
			if (med == null) {
				if (other.med != null)
					return false;
			} else if (!med.equals(other.med))
				return false;
			return true;
		}
		Medicine med;
		int count;
		@Override
		public int compareTo(Coverage o) {
			return Integer.valueOf(count).compareTo(Integer.valueOf(o.count));
		}
	}
	

	private String id;
	private List<Diagnosys> diagnosys = new ArrayList<>();
	private List<PrescriptionRow> prescriptions = new ArrayList<>();
	private Patient patient;
	private int prescriptionId;
	
	@Override
	public int getPrescriptionId() {
		return prescriptionId;
	}

	@Override
	public void setPrescriptionId(int prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	public SessionImpl(String id, Patient patient) {
		super();
		this.id = id;					// session id
		this.patient = patient;			// contains patient id, name, ph. no
	}

	@Override
	public String getId() {
		return id;
	}
	
	@Override
	public Patient getPatient() {
		return patient;
	}
	/* (non-Javadoc)
	 * @see com.erx.obj.Session#getDiagnosys()
	 */
	@Override
	public List<Diagnosys> getDiagnosys(){
		return Collections.unmodifiableList(diagnosys);
	}
	
	/* (non-Javadoc)
	 * @see com.erx.obj.Session#getPrescriptions()
	 */
	@Override
	public List<PrescriptionRow> getPrescriptions(){
		return Collections.unmodifiableList(prescriptions);
	}
	
	/* (non-Javadoc)
	 * @see com.erx.obj.Session#addDiagnosys(com.erx.beans.Diagnosys)
	 */
	@Override
	public void addDiagnosys(Diagnosys d){
		diagnosys.add(d);					// adds given Diagnosys d to the diagnosys list.
	}
	
	/* (non-Javadoc)
	 * @see com.erx.obj.Session#addPrescription(com.erx.obj.PrescriptionRow)
	 */
	@Override
	public void addPrescription(PrescriptionRow row){
		prescriptions.add(row);
	}
	
	@Override
	public void addPrescription(Medicine medicine, Set<Dose> doses, int numUnits){
		PrescriptionRow row = ObjectsProvider.getPrescription(medicine, doses, numUnits);
		addPrescription(row);
	}

	@Override
	public List<Medicine> getPossibleMedicines() {				//create list of medicines for each available diagnosys.
		HashMap<Coverage, Coverage> coverages = new HashMap<>();
		for (Diagnosys d : this.diagnosys) {
			List<Medicine> meds = MedicineDiagnosysCache.S_INSTANCE.getMedicines(d);
			if(meds == null || meds.isEmpty()){
				meds = MedicineCache.S_INSTANCE.getAllMedicines();
			}
			for (Medicine medicine : meds) {
				Coverage coverage = new Coverage();
				coverage.med = medicine;
				if(coverages.containsKey(coverage)){
					coverage = coverages.get(coverage);
				}
				coverage.count++;
				coverages.put(coverage, coverage);
			}
		}
		List<Coverage> coverageList = new ArrayList<>();
		coverageList.addAll(coverages.keySet());
		Collections.sort(coverageList);
		List<Medicine> medList = new ArrayList<>();
		for (Coverage coverage : coverageList) {
			medList.add(coverage.med);
		}
		return Collections.unmodifiableList(medList);
	}
}
