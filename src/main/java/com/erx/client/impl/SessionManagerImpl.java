package com.erx.client.impl;

import java.util.List;

import com.erx.beans.Medicine;
import com.erx.client.ifc.SessionManager;
import com.erx.ctx.ApplicationContext;
import com.erx.obj.Doctor;
import com.erx.obj.Patient;
import com.erx.ops.Session;
import com.erx.service.ERXGatewayFactory;
import com.erx.service.ifc.ERxProcessorOutput;

public class SessionManagerImpl implements SessionManager{

	@Override
	public Session createSession(Patient patient, List<String> diagnosys) {
		return ObjectsProvider.newSession(patient, diagnosys);
	}

	@Override
	public void completeSession(Session s) {
		ERXGatewayFactory.getERxGateway().completeSession(
					getDoctor(), s);
	}

	@Override
	public ERxProcessorOutput prepareSession(Session s) {
		return ERXGatewayFactory.getERxGateway().prepareSession(
				getDoctor(), s);
		
	}
	
	@Override
	public List<Medicine> getPossibleMedicines(Session s){
		return ERXGatewayFactory.getERxGateway().getPossibleMedicines(
				getDoctor(), s).getMedicines();
	}

	

	private Doctor getDoctor() {
		return ApplicationContext.getContext().getDoctor();
	}

}
