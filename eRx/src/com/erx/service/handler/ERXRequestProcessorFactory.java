package com.erx.service.handler;

import com.erx.service.ERxOrderProcessor;
import com.erx.service.ifc.ERxProcessorInput;
import com.erx.service.ifc.OrderStatus;
import com.erx.service.input.ERxCreateOrderInput;

import eRxDB.Prescription;

public class ERXRequestProcessorFactory {
	public static ERxOrderProcessor getRequestprocessor(ERxProcessorInput ip){
		if(ip.getOrderId() == 0){
			return new ERxCreateOrderProcessor((ERxCreateOrderInput) ip);
		}else {
			Prescription ps = ERxHelper.findOrder(ip.getOrderId());
			if(ps.getOrderstatus() == OrderStatus.INITIATED.ordinal()){
				return new ERxOrderConfirmationProcessor((ERxCreateOrderInput) ip);
			}else if(ps.getOrderstatus() == OrderStatus.CONFIRMED.ordinal()){
				return new ERxAddressRequestProcessor((ERxCreateOrderInput) ip);
			}
		}
		return null;
	}
}
