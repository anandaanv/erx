package com.erx.service.input;

import com.erx.service.ifc.ERxProcessorOutput;
import java.util.List;

import com.erx.beans.Medicine;

public class ERxPossibleMedicinesOutput extends ERxProcessorOutput {
	List<Medicine> medicines;

	public List<Medicine> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<Medicine> medicines) {
		this.medicines = medicines;
	}
	
}
