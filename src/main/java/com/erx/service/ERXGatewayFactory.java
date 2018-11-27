package com.erx.service;

import com.erx.ops.ERxGateway;

public class ERXGatewayFactory {
	public static ERxGateway getERxGateway(){
		return new ERxGatewayImpl();
	}
}
