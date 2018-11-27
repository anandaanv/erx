package com.erx.obj;

import com.erx.beans.BaseBean;

public class Doctor extends BaseBean {
	private String name;
	private String qualification;
	private String registrationNumber;
	private User  user;
	private int id;
	
	public Doctor(int id, String name, String qualification, String registrationNumber, User user) {
		super();
		this.id = id;
		this.name = name;
		this.qualification = qualification;
		this.registrationNumber = registrationNumber;
		this.user = user;
	}

	public String getName() {
		return name;
	}
	public String getQualification() {
		return qualification;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public User getUser() {
		return user;
	}

	public int getId() {
		return id;
	}
	
	
}
