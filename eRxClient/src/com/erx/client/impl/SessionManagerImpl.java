package com.erx.client.impl;

import com.erx.client.ifc.SessionManager;
import com.erx.ctx.ApplicationContext;
import com.erx.obj.Patient;
import com.erx.ops.Session;
import com.erx.service.ERXGatewayFactory;
import com.erx.service.ifc.ERxProcessorOutput;

public class SessionManagerImpl implements SessionManager{

	@Override
	public Session createSession(Patient patient) {
		return ObjectsProvider.newSession(patient);
	}

	@Override
	public void completeSession(Session s) {
		ERXGatewayFactory.getERxGateway().completeSession(
					ApplicationContext.S_INSTANCE.getDoctor(), s);
	}

	@Override
	public ERxProcessorOutput prepareSession(Session s) {
		return ERXGatewayFactory.getERxGateway().prepareSession(
				ApplicationContext.S_INSTANCE.getDoctor(), s);
		
	}

}
