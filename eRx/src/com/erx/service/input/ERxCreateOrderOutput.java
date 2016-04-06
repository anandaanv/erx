package com.erx.service.input;

import com.erx.service.ifc.ERxProcessorOutput;

public class ERxCreateOrderOutput extends ERxProcessorOutput{

	private int id;
	private String expernalId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getExpernalId() {
		return expernalId;
	}

	public void setExpernalId(String expernalId) {
		this.expernalId = expernalId;
	}

	public void setPrescriptionId(int id) {
		this.id = id;
	}

}
