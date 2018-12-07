package com.erx.obj;

import com.erx.beans.BaseBean;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class Doctor extends BaseBean {
	private String name;
	private String qualification;
	private String registrationNumber;
	private User  user;
	private long id;
}
