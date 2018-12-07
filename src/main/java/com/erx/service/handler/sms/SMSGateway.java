package com.erx.service.handler.sms;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.erx.ex.ERxException;
import com.erx.obj.Address;
import com.erx.obj.Patient;
import com.erx.obj.SMSPayload;

import erxdb.Prescription;
import erxdb.persistence.PersistenceWrapper;

public abstract class SMSGateway {

	private static final Logger logger = Logger.getLogger(SMSGateway.class.getName());

	public SMSGateway() {
		super();
	}

	protected abstract boolean sendMessage(Patient patient, String message) throws ERxException;

	public boolean requestAddress(Patient patient) {
		// send SMS request
		if (sendAddressRequest(patient)) {
			return true;
		}
		return false;
	}

	private boolean sendAddressRequest(Patient patient) {
		try {
			String message = "We need your Address to deliver your order. Please send your address to 9222211188. Thank you for your cooperation";
			return sendMessage(patient, message);
		} catch (Exception e) {
			logger.log(Level.WARNING, "error while sending SMS " + e.getMessage(), e);
			return false;
		}
	}

	public void receiveAddress(SMSPayload payload) {
		Prescription ps = findOrder(payload);
		Address addr = extractAddress(payload);
		erxdb.Address adr = adaptAddress(addr);
		PersistenceWrapper.save(adr);
		int addressId = adr.getId();
		ps.setAddressid(addressId);
	}

	private erxdb.Address adaptAddress(Address addr) {
		erxdb.Address adr = new erxdb.Address();
		adr.setAddress1(addr.getAddress1());
		adr.setAddress2(addr.getAddress1());
		adr.setAddress3(addr.getAddress1());
		adr.setAddress4(addr.getAddress1());
		adr.setCity(addr.getCity());
		adr.setCountry(addr.getCountry());
		adr.setState(addr.getState());
		adr.setZipcode(addr.getZipcode());
		adr.setLandmark(addr.getLandmark());
		return adr;
	}

	private Address extractAddress(SMSPayload payload) {
		return null;
	}

	private Prescription findOrder(SMSPayload payload) {
		return null;
	}

	public boolean sendOrderConfirmation(Prescription ps, Patient p) {
		String message = createMessaageFromPrescription(ps);
		try {
			sendMessage(p, message);
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			return false;
		}
		return true;
	}

	private String createMessaageFromPrescription(Prescription ps) {
		String message = "Dr " + ps.getDoctor().getName() + " has requested an electronic prescription for Mr " +
					ps.getPatientname() + ". Prescription number:" + ps.getId();
		
		StringBuilder s = new StringBuilder(message);
		
		
		return s.toString();
	}

}