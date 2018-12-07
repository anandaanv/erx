package com.erx.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.erx.beans.Medicine;
import com.erx.obj.Doctor;
import com.erx.ops.Session;

import erxdb.MedicineDiagnosys;
import erxdb.persistence.PersistenceWrapper;

public class ERxFetchMedicinesAction {
	
	private Session s;
	private Doctor doctor;

	public ERxFetchMedicinesAction(Session s, Doctor doc) {
		super();
		this.s = s;
		this.doctor = doc;
	}
	
	List<Medicine> process(){

		List<erxdb.Medicine> possibleMeds = new ArrayList<>();
		String name = null;
		if(!s.getPossibleMedicines().isEmpty()){
			name = s.getPossibleMedicines().get(0).getProprietaryName();
		}
		List<Medicine> meds = new ArrayList<>();
		EntityManager em = PersistenceWrapper.getEntitymanager();
		Query q = em.createQuery(MedicineDiagnosys.FINDBYDOCDIAGN, MedicineDiagnosys.class);
		erxdb.Doctor doc = em.find(erxdb.Doctor.class, doctor.getId());
		q.setParameter("doc", doc);
		q.setParameter("dyn", s.getDiagnosys().toString());
		try{
			List<MedicineDiagnosys> res = q.getResultList();
			for (MedicineDiagnosys dn : res) {
				erxdb.Medicine m = dn.getMedicine();
				if(name == null || name.isEmpty() || m.getProprietaryName().contains(name)){
					possibleMeds.add(m);
				}
			}
			//doing it deliberately
			possibleMeds.add(null);
		}catch(NoResultException e){
			e.printStackTrace();
		}
		Query medicinesByName = em.createNamedQuery("Medicine.findAllByString", erxdb.Medicine.class);
		medicinesByName.setParameter("name", "%" + name +"%");
		try{
			List<erxdb.Medicine> res = medicinesByName.getResultList();
			possibleMeds.addAll(res);
		}catch(Exception e){
			e.printStackTrace();
		}
		for (erxdb.Medicine m : possibleMeds) {
			if(m == null){
				meds.add(new Medicine());
			}
			meds.add(adaptMedicine(m));
		}
		return meds;
	
	}
	
	private Medicine adaptMedicine(erxdb.Medicine m) {
		Medicine med = new Medicine();
		med.setProprietaryName(m.getProprietaryName());
		med.setId(m.getId());
		return med;
	}

}
