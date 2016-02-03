package com.erx.client.impl;

import java.util.Set;
import java.util.UUID;

import com.erx.beans.Medicine;
import com.erx.obj.Dose;
import com.erx.obj.Patient;
import com.erx.obj.PrescriptionRow;
import com.erx.ops.Session;

public class ObjectsProvider {
	public static Session newSession(Patient p){
		return new SessionImpl(UUID.randomUUID().toString(), p);
	}

	public static PrescriptionRow getPrescription(Medicine medicine, Set<Dose> doses, int numUnits) {
		return new PrescriptionRow(getRandomInt(), medicine, doses, numUnits);
	}

	private static int getRandomInt() {
		return Long.valueOf(Math.round(Math.random() * 1000000000)).intValue();
	}
}
