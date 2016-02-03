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
		Doctor doc = new Doctor();
		doc.setId(input.getDoctor().getId());
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

	private int getPatient() {

		Patient p = null;
		
		String hql = "Select p.id from Patient p where p.phonenumber=:num";      
		System.out.println(hql);
		Query query = PersistenceWrapper.getEntitymanager().createQuery(hql);
		query.setParameter("num", input.getPatient().getPhoneNumber());
		List<Integer> result = query.getResultList();

		//		Patient p = PersistenceWrapper.getEntitymanager().find(Patient.class, input.getPatient().getId());
		if(result.isEmpty()){
			p = new Patient();
//			p.setId(input.getPatient().getId());
			p.setName(input.getPatient().getName());
			p.setPhonenumber(input.getPatient().getPhoneNumber());
			PersistenceWrapper.getEntitymanager().persist(p);
			return p.getId();
		}

		return result.get(0);
	}

}
