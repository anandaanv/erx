package com.erx.user.login;

import javax.persistence.NoResultException;

import com.erx.ctx.ApplicationContext;
import com.erx.ex.UserNotFoundException;
import com.erx.service.handler.ERxHelper;

import eRxDB.Doctor;
import eRxDB.User;
import eRxDB.dao.DoctorDAO;
import eRxDB.dao.UserDAO;

public class LoginAction {
	
	public void login(String userId, String password) throws UserNotFoundException{
		try{
			User user = UserDAO.S_INSTANCE.findByUserIdAndPassword(userId, ERxHelper.encrypt(password));
			initializeSession(user);
		}catch(NoResultException e){
			throw new UserNotFoundException(userId, e);
		}
	}

	private void initializeSession(User user) {
		//for now search for doctor only.
		Doctor doc = DoctorDAO.S_INSTANCE.findByUserId(user.getUserId());
		ApplicationContext.getContext().setDoc(adaptDoctor(doc, user));;
	}

	private com.erx.obj.Doctor adaptDoctor(Doctor doc, User user) {
		return new com.erx.obj.Doctor(doc.getId(), doc.getName(), doc.getQualification(), doc.getRegno(), adaptUser(user));
	}

	private com.erx.obj.User adaptUser(User user) {
		com.erx.obj.User u = new com.erx.obj.User();
		u.setUserId(user.getUserId());
		u.setName(user.getName());
		u.setPhoneNumber(user.getPhoneNumber());
		u.setEmail(user.getEmail());
		return u;
	}
	
	
	

}
