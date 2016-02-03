package com.erx.service.handler;

import javax.persistence.EntityManager;

import com.erx.obj.Patient;
import com.erx.service.BaseERxOrderProcessor;
import com.erx.service.ifc.ERxOrder;
import com.erx.service.ifc.OrderStatus;
import com.erx.service.input.ERxCreateOrderInput;
import com.erx.service.input.ERxCreateOrderOutput;

import eRxDB.Prescription;
import eRxDB.persistence.PersistenceWrapper;

public class ERxAddressRequestProcessor extends BaseERxOrderProcessor<ERxCreateOrderInput, ERxCreateOrderOutput> {

	private ERxOrder order;

	public ERxAddressRequestProcessor(ERxCreateOrderInput input) {
		super(input);
	}

	@Override
	public ERxCreateOrderOutput processOrder() {
		Prescription ps = ERxHelper.findOrder(input.getOrderId());
		//send SMS to request the address
		Patient p = new Patient();
		p.setId(ps.getPatientid());
		p.setName(ps.getPatientname());
		p.setPhoneNumber(ps.getPhonenumber());
		
		ERxSMSGateway.getInstance().requestAddress(p);
		//update order state to PENDING_ADDRESS
		EntityManager em = PersistenceWrapper.getEntitymanager();
		//return null for noe
		
		ps.setOrderstatus(OrderStatus.PENDING_ADDRESS.ordinal());
		
		em.getTransaction().begin();
		PersistenceWrapper.save(ps);
		em.getTransaction().commit();
		return null;
	}

}
