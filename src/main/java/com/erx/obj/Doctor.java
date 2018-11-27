package com.erx.obj;

import com.erx.beans.BaseBean;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
@Builder
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
	
}
