package com.erx.service;

import com.erx.obj.Doctor;
import com.erx.ops.ERxGateway;
import com.erx.ops.Session;
import com.erx.service.handler.ERXRequestProcessorFactory;
import com.erx.service.ifc.ERxProcessorOutput;
import com.erx.service.input.ERxCreateOrderInput;
import com.erx.service.input.ERxPossibleMedicinesOutput;

public class ERxGatewayImpl implements ERxGateway{

	@Override
	public ERxProcessorOutput completeSession(Doctor d, Session s) {
		ERxCreateOrderInput input = new ERxCreateOrderInput();
		input.setDoctor(d);
		input.setOrderId(s.getPrescriptionId());
		input.setDiagnosys(s.getDiagnosys());
		input.setPatient(s.getPatient());
		input.setPrescriptions(s.getPrescriptions());
		input.setSessionId(s.getId());
		input.setCertPassword(s.getCertPassword());
		return ERXRequestProcessorFactory.getRequestprocessor(input).processOrder();
	}

	private ERxProcessorOutput preparePrescription(Doctor d, Session s) {
		
		ERxCreateOrderInput input = new ERxCreateOrderInput();
		input.setDoctor(d);
		input.setOrderId(0);
		input.setDiagnosys(s.getDiagnosys());
		input.setPatient(s.getPatient());
		input.setPrescriptions(s.getPrescriptions());
		input.setSessionId(s.getId());
		return ERXRequestProcessorFactory.getRequestprocessor(input).processOrder();
	}

	@Override
	public ERxProcessorOutput prepareSession(Doctor d, Session s) {
		return preparePrescription(d, s);
	}
	
	@Override
	public ERxPossibleMedicinesOutput getPossibleMedicines(Doctor d, Session s){
		ERxFetchMedicinesAction action = new ERxFetchMedicinesAction(s, d);
		ERxPossibleMedicinesOutput res = new ERxPossibleMedicinesOutput();
		res.setMedicines(action.process());
		return res;
	}

}
