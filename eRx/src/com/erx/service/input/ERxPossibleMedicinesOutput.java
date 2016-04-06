package com.erx.service.input;

import java.util.List;

import com.erx.beans.Medicine;
import com.erx.service.ifc.ERxProcessorOutput;

public class ERxPossibleMedicinesOutput extends ERxProcessorOutput{
	List<Medicine> medicines;

	public List<Medicine> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<Medicine> medicines) {
		this.medicines = medicines;
	}
	
}
