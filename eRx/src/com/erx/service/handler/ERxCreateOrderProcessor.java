package com.erx.service.handler;

import java.util.List;

import javax.persistence.Query;

import com.erx.obj.PrescriptionRow;
import com.erx.service.BaseERxOrderProcessor;
import com.erx.service.ifc.OrderStatus;
import com.erx.service.input.ERxCreateOrderInput;
import com.erx.service.input.ERxCreateOrderOutput;

import eRxDB.Doctor;
import eRxDB.Patient;
import eRxDB.Prescription;
import eRxDB.Prescriptionrow;
import eRxDB.persistence.PersistenceWrapper;

public class ERxCreateOrderProcessor extends BaseERxOrderProcessor<ERxCreateOrderInput, ERxCreateOrderOutput> {

	
	public ERxCreateOrderProcessor(ERxCreateOrderInput input) {
		super(input);
	}

	@Override
	public ERxCreateOrderOutput processOrder() {
		PersistenceWrapper.getEntitymanager().getTransaction().begin();
		List<PrescriptionRow> prescriptions = input.getPrescriptions();
		for (PrescriptionRow prescriptionRow : prescriptions) {
			Prescriptionrow row = new Prescriptionrow();
			row.setDoses(prescriptionRow.getDoses().toString());
			row.setMedicineid(prescriptionRow.getMedicine().getId());
			row.setMedicinename(prescriptionRow.getMedicine().getProprietaryName());
			row.setNumunits(prescriptionRow.getNumUnits());
			PersistenceWrapper.save(row);
		}
		Prescription ps = new Prescription();
		ps.setDiagnosys(input.getDiagnosys().toString());
		Doctor res = PersistenceWrapper.getEntitymanager().find(Doctor.class, input.getDoctor().getId());
		ps.setDoctor(res);
		ps.setDoctorname(res.getName());
		ps.setOrderstatus(OrderStatus.INITIATED.ordinal());
		ps.setExternaltxnid(input.getSessionId());
		ps.setPatientid(getPatient());
		ps.setPatientname(input.getPatient().getName());
		ps.setPhonenumber(input.getPatient().getPhoneNumber());
		PersistenceWrapper.save(ps);
		PersistenceWrapper.getEntitymanager().getTransaction().commit();
		ERxCreateOrderOutput op = new ERxCreateOrderOutput();
		op.setPrescriptionId(ps.getId());
		op.setExpernalId(ps.getExternaltxnid());
		return op;
	}

	private Patient getPatient() {

		Patient p = null;
		
		String hql = "Select p from Patient p where p.uniqueId=:id";      
		System.out.println(hql);
		Query query = PersistenceWrapper.getEntitymanager().createQuery(hql);
		query.setParameter("id", input.getPatient().getId());
		List<Patient> result = query.getResultList();

		if(result.isEmpty()){
			p = new Patient();
			p.setUniqueId(input.getPatient().getId());
			p.setName(input.getPatient().getName());
			p.setPhonenumber(input.getPatient().getPhoneNumber());
			PersistenceWrapper.getEntitymanager().persist(p);
			return p;
		}

		return result.get(0);
	}

}
