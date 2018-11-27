package com.erx.service.impl;

import com.erx.service.handler.ERXRequestProcessorFactory;
import com.erx.service.ifc.ERxProcessorInput;
import com.erx.service.ifc.ERxProcessorOutput;

public class ERxserviceImpl {
	public ERxProcessorOutput handle(ERxProcessorInput ip){
		return ERXRequestProcessorFactory.getRequestprocessor(ip).processOrder();
	}
}
