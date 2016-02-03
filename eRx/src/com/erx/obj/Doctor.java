package com.erx.obj;

import com.erx.beans.BaseBean;

public class Doctor extends BaseBean {
	private String name;
	private String qualification;
	private String registrationNumber;
	private String userName;
	public String getName() {
		return name;
	}
	public Doctor(int id, String name, String qualification, String registrationNumber, String userName) {
		super(id);
		this.name = name;
		this.qualification = qualification;
		this.registrationNumber = registrationNumber;
		this.userName = userName;
	}
	public String getQualification() {
		return qualification;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public String getUserName() {
		return userName;
	}
}
