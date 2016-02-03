package com.erx.service.input;

import java.util.ArrayList;
import java.util.List;

import com.erx.beans.Diagnosys;
import com.erx.obj.Doctor;
import com.erx.obj.Patient;
import com.erx.obj.PrescriptionRow;
import com.erx.service.ifc.ERxProcessorInput;

public class ERxCreateOrderInput extends ERxProcessorInput {
	
	private int orderId;
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderid) {
		this.orderId = orderid;
	}
	private Doctor doctor;
	private Patient patient;
	private String sessionId;
	private List<Diagnosys> diagnosys = new ArrayList<>();
	private List<PrescriptionRow> prescriptions = new ArrayList<>();
	
	
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public List<Diagnosys> getDiagnosys() {
		return diagnosys;
	}
	public void setDiagnosys(List<Diagnosys> diagnosys) {
		this.diagnosys = diagnosys;
	}
	public List<PrescriptionRow> getPrescriptions() {
		return prescriptions;
	}
	public void setPrescriptions(List<PrescriptionRow> prescriptions) {
		this.prescriptions = prescriptions;
	}
	
	
	
}
