package com.erx.service.handler;

import javax.persistence.EntityManager;

import eRxDB.Prescription;
import eRxDB.persistence.PersistenceWrapper;

public class ERxHelper {
	public static Prescription findOrder(int orderId){
		EntityManager em = PersistenceWrapper.getEntitymanager();
		Prescription ps = em.find(Prescription.class, orderId);
		return ps;
	}
}
