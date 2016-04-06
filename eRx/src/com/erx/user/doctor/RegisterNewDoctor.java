package com.erx.user.doctor;

import javax.persistence.NoResultException;

import com.erx.ex.UserNotFoundException;
import com.erx.obj.Certificate;
import com.erx.obj.Doctor;
import com.erx.service.handler.ERxHelper;

import eRxDB.User;
import eRxDB.UserRole;
import eRxDB.dao.CertificateDAO;
import eRxDB.dao.DoctorDAO;
import eRxDB.dao.RoleDAO;
import eRxDB.dao.UserDAO;
import eRxDB.dao.UserRoleDAO;
import eRxDB.persistence.PersistenceWrapper;

public class RegisterNewDoctor {

	private Doctor doctor;

	public RegisterNewDoctor(Doctor doctor) {
		super();
		this.doctor = doctor;
	}
	
	public void processRegistration() throws UserNotFoundException{
		PersistenceWrapper.getEntitymanager().getTransaction().begin();
		User u = null;
		try{
			u = UserDAO.S_INSTANCE.findByUserId(doctor.getUser().getUserId());
		}catch(NoResultException e){
			throw new UserNotFoundException(doctor.getUser().getUserId(), e);
		}
		
		eRxDB.Doctor doc = new eRxDB.Doctor();
		doc.setName(u.getName());
		doc.setUsername(u.getUserId());
		doc.setRegno(doctor.getRegistrationNumber());
		doc.setQualification(doctor.getQualification());
		
		DoctorDAO.S_INSTANCE.saveDoctor(doc);
		
		UserRole role = new UserRole();
		role.setUser(u);
		role.setRole(RoleDAO.S_INSTANCE.findByRoleName("DOCTOR"));
		role.setRefId(doc.getId());
		
		UserRoleDAO.S_INSTANCE.saveUserRole(role);
		PersistenceWrapper.getEntitymanager().getTransaction().commit();
	}
	
	
	public void updateCertificate(Certificate cer) throws UserNotFoundException{
		PersistenceWrapper.getEntitymanager().getTransaction().begin();
		eRxDB.Doctor d = null;
		try{
			d = DoctorDAO.S_INSTANCE.findByUserId(doctor.getUser().getUserId());
		}catch(NoResultException e){
			throw new UserNotFoundException(doctor.getUser().getUserId(), e);
		}
		eRxDB.Certificate cert = new eRxDB.Certificate();
		cert.setKey(ERxHelper.encrypt(cer.getUser().getUserId()));
		cert.setSignImage(cer.getSignImage());
		cert.setValue(cer.getCertificate());
		
		CertificateDAO.S_INSTANCE.saveCertificate(cert);
		PersistenceWrapper.getEntitymanager().getTransaction().commit();
	}
	
	
}
