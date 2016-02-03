package com.erx.ctx;

import com.erx.obj.Doctor;

public enum ApplicationContext {
	S_INSTANCE;

	private Doctor doc;
	
	private ApplicationContext() {
		//FIXME - set proper initialization code
		doc = new Doctor(1, "vaidya", "M.D.", "MH12-FE-00991", "avaidya");
	}
	
	public Doctor getDoctor(){
		return doc;
	}
	
	
}
