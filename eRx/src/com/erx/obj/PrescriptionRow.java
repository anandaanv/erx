package com.erx.obj;

import java.util.Set;

import com.erx.beans.Medicine;

public class PrescriptionRow {
	
	private int id;
	private Medicine medicine;
	private Set<Dose> doses;
	private int numUnits;
	
	public PrescriptionRow(int id, Medicine medicine, Set<Dose> doses, int numUnits) {
		super();
		this.id = id;
		this.medicine = medicine;
		this.doses = doses;
		this.numUnits = numUnits;
	}

	public int getNumUnits() {
		return numUnits;
	}

	public int getId() {
		return id;
	}


	public Medicine getMedicine() {
		return medicine;
	}


	public Set<Dose> getDoses() {
		return doses;
	}
	
	
}
