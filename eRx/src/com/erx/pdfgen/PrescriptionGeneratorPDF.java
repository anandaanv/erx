package com.erx.pdfgen;
import java.io.IOException;

import com.erx.service.ifc.ERxOrder;
import com.erx.service.input.ERxCreateOrderInput;

public abstract class PrescriptionGeneratorPDF {
	public byte[] generatePDF(ERxOrder order, ERxCreateOrderInput input) throws IOException{
		if(isValidOrder(order)){
			return generatePDF(input);
		}
		return null;
	}

	protected abstract byte[] generatePDF(ERxCreateOrderInput input) throws IOException;

	protected boolean isValidOrder(ERxOrder order) {
		return true;
	}
}
