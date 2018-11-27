package com.erx.service.handler;

import com.erx.service.BaseERxOrderProcessor;
import com.erx.service.input.ERxCreateOrderInput;
import com.erx.service.input.ERxCreateOrderOutput;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.erx.obj.PrescriptionRow;
import com.erx.service.ifc.OrderStatus;

import eRxDB.Doctor;
import eRxDB.Patient;
import eRxDB.Prescription;
import eRxDB.Prescriptionrow;
import eRxDB.persistence.PersistenceWrapper;

public class ERxCreateOrderProcessor extends BaseERxOrderProcessor<ERxCreateOrderInput, ERxCreateOrderOutput> {
	
	private static final String FIND_BY_PATIENT_ID = "Select p from Patient p where p.uniqueId=:id";

	public ERxCreateOrderProcessor(ERxCreateOrderInput input) {
		super(input);
	}

	@Override
	public ERxCreateOrderOutput processOrder() {
		EntityManager em = PersistenceWrapper.getEntitymanager();
		em.getTransaction().begin();
		List<PrescriptionRow> prescriptions = input.getPrescriptions();
		Prescription ps = new Prescription();
		ps.setDiagnosys(input.getDiagnosys().toString());
		Doctor res = em.find(Doctor.class, input.getDoctor().getId());
		ps.setDoctor(res);
		ps.setDoctorname(res.getName());
		ps.setOrderstatus(OrderStatus.INITIATED.ordinal());
		ps.setExternaltxnid(input.getSessionId());
		ps.setPatientid(getPatient());
		ps.setPatientname(input.getPatient().getName());
		ps.setPhonenumber(input.getPatient().getPhoneNumber());
		PersistenceWrapper.save(ps);
		em.refresh(ps);
		List<Prescriptionrow> medicines = getMedicines(prescriptions, ps);
		for (Prescriptionrow prescriptionrow : medicines) {
			em.persist(prescriptionrow);
		}
		em.getTransaction().commit();
		ERxCreateOrderOutput op = new ERxCreateOrderOutput();
		op.setPrescriptionId(ps.getId());
		op.setExpernalId(ps.getExternaltxnid());
		return op;
	}

	private List<Prescriptionrow> getMedicines(List<PrescriptionRow> prescriptions, Prescription ps) {
		List<Prescriptionrow> medicines = new ArrayList<>();
		for (PrescriptionRow prescriptionRow : prescriptions) {
			Prescriptionrow row = new Prescriptionrow();
			Query uq = PersistenceWrapper.getEntitymanager().createQuery("select m.id from Medicine m where m.proprietaryName = :name", Integer.class);
			uq.setParameter("name", prescriptionRow.getMedicine().getProprietaryName());
			row.setDoses(prescriptionRow.getDoses().toString());
			row.setMedicineid((Integer)uq.getSingleResult());
			row.setMedicinename(prescriptionRow.getMedicine().getProprietaryName());
			row.setNumunits(prescriptionRow.getNumUnits());
			row.setPrescription(ps);
			medicines.add(row);
		}
		return medicines;
	}

	private Patient getPatient() {

		Patient p;
		
		//		System.out.println(hql);
		Query query = PersistenceWrapper.getEntitymanager().createQuery(FIND_BY_PATIENT_ID);
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
