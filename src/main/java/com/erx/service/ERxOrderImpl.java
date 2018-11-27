package com.erx.service;

import com.erx.obj.Address;
import com.erx.obj.Doctor;
import com.erx.obj.Patient;
import com.erx.service.ifc.ERxOrder;

public class ERxOrderImpl implements ERxOrder {

	@Override
	public long getOrderId() {
		return 0;
	}

	@Override
	public void setOrderId(long orderid) {
		// TODO Auto-generated method stub

	}

	@Override
	public Patient getPatient() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Address getAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Doctor getDoctor() {
		// TODO Auto-generated method stub
		return null;
	}

}
