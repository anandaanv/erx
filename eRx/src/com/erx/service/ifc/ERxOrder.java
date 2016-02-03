package com.erx.service.ifc;

import com.erx.obj.Address;
import com.erx.obj.Doctor;
import com.erx.obj.Patient;

public interface ERxOrder {
	long getOrderId();
	void setOrderId(long orderid);
	Patient getPatient();
	Address getAddress();
	Doctor getDoctor();
}
