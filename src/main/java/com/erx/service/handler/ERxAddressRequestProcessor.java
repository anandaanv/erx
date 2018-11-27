package com.erx.service.handler;

import com.erx.service.BaseERxOrderProcessor;
import javax.persistence.EntityManager;

import com.erx.obj.Patient;
import com.erx.service.handler.sms.ERxSMSGateway;
import com.erx.service.ifc.OrderStatus;
import com.erx.service.input.ERxCreateOrderInput;
import com.erx.service.input.ERxCreateOrderOutput;

import eRxDB.Prescription;
import eRxDB.persistence.PersistenceWrapper;

public class ERxAddressRequestProcessor extends BaseERxOrderProcessor<ERxCreateOrderInput, ERxCreateOrderOutput> {

	public ERxAddressRequestProcessor(ERxCreateOrderInput input) {
		super(input);
	}

	@Override
	public ERxCreateOrderOutput processOrder() {
		Prescription ps = ERxHelper.findOrder(input.getOrderId());
		//send SMS to request the address
		Patient p = new Patient();
		p.setName(ps.getPatientname());
		p.setPhoneNumber(ps.getPhonenumber());
		
		ERxSMSGateway.getInstance().requestAddress(p);
		//update order state to PENDING_ADDRESS
		EntityManager em = PersistenceWrapper.getEntitymanager();
		//return null for now
		
		ps.setOrderstatus(OrderStatus.PENDING_ADDRESS.ordinal());
		
		em.getTransaction().begin();
		PersistenceWrapper.save(ps);
		em.getTransaction().commit();
		return null;
	}

}
