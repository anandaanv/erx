package com.erx.service;

import com.erx.service.ifc.ERxProcessorInput;
import com.erx.service.ifc.ERxProcessorOutput;

public abstract class BaseERxOrderProcessor<I extends ERxProcessorInput, O extends ERxProcessorOutput> implements ERxOrderProcessor{
	protected I input;

	public BaseERxOrderProcessor(I input) {
		super();
		this.input = input;
	}

	@Override
	public abstract O processOrder();
	
	
	
}
