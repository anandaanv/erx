package com.erx.user.doctor;

import com.erx.obj.User;

import erxdb.dao.UserDAO;
import erxdb.persistence.PersistenceWrapper;

public class RegisterUser {
	private User user;

	public RegisterUser(User user) {
		super();
		this.user = user;
	}
	
	public void processRegistration(){
		PersistenceWrapper.getEntitymanager().getTransaction().begin();
		erxdb.User u = new erxdb.User();
		u.setEmail(user.getEmail());
		u.setEnabled(false);
		u.setEncryptedPassword(user.getEncryptedPassword());
		u.setName(user.getName());
		u.setPhoneNumber(user.getPhoneNumber());
		u.setStatus("CREATED");
		u.setUserId(user.getUserId());
		UserDAO.S_INSTANCE.saveUser(u);
		PersistenceWrapper.getEntitymanager().getTransaction().commit();
	}
	
	public void completeRegistration(){
		PersistenceWrapper.getEntitymanager().getTransaction().begin();
		erxdb.User u = UserDAO.S_INSTANCE.findByUserId(user.getUserId());
		u.setEnabled(true);
		UserDAO.S_INSTANCE.saveUser(u);
		PersistenceWrapper.getEntitymanager().getTransaction().commit();
	}
	
}
