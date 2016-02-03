package com.erx.service.handler;

import javax.persistence.EntityManager;

import com.erx.service.BaseERxOrderProcessor;
import com.erx.service.ifc.OrderStatus;
import com.erx.service.input.ERxCreateOrderInput;
import com.erx.service.input.ERxCreateOrderOutput;

import eRxDB.Prescription;
import eRxDB.persistence.PersistenceWrapper;

public class ERxOrderConfirmationProcessor extends BaseERxOrderProcessor<ERxCreateOrderInput, ERxCreateOrderOutput> {

	public ERxOrderConfirmationProcessor(ERxCreateOrderInput input) {
		super(input);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ERxCreateOrderOutput processOrder() {
		//TODO update order status to orderstatus.confirmed.
		EntityManager em = PersistenceWrapper.getEntitymanager();
		Prescription ps = ERxHelper.findOrder(input.getOrderId());
		ps.setOrderstatus(OrderStatus.CONFIRMED.ordinal());
		
		em.getTransaction().begin();
		PersistenceWrapper.save(ps);
		em.getTransaction().commit();
		//trigger further processing.
		ERxSMSGateway.getInstance().sendOrderConfirmation(ps, input.getPatient());
		ERXRequestProcessorFactory.getRequestprocessor(input).processOrder();
		return null; //for now
	}

}
