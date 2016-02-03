package com.erx.service.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import com.erx.obj.Address;
import com.erx.obj.Patient;
import com.erx.obj.SMSPayload;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import eRxDB.Prescription;
import eRxDB.persistence.PersistenceWrapper;

public class ERxSMSGateway {

	private ERxSMSGateway() {
	}

	private static ERxSMSGateway s_instance = new ERxSMSGateway();

	public static ERxSMSGateway getInstance() {
		return s_instance;
	}

	// methods
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
			System.out.println("Error SMS " + e);
			return false;
		}
	}

	private boolean sendMessage(Patient patient, String message)
			throws Exception {
		String user = "username=" + "anand@dheemantech.com";
		String hash = "&apiKey=" + "eoAX0CWKnug-6M4EVdymRfFSni3MAZ54Gb8Jnu63r6";
		String sender = "&sender=" + "TXTLCL";
		String numbers = "&numbers=" + "91" + patient.getPhoneNumber();
		message = "&message=" + message;
		// Send data
		HttpURLConnection conn = (HttpURLConnection) new URL("http://api.textlocal.in/send/?").openConnection();
		String data = user + hash + numbers + message + sender;
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
		conn.getOutputStream().write(data.getBytes("UTF-8"));
		conn.getOutputStream().flush();
		conn.getOutputStream().close();
		final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		final StringBuffer stringBuffer = new StringBuffer();
		String line;
		while ((line = rd.readLine()) != null) {
			stringBuffer.append(line);
		}
		rd.close();
		Gson gson = new GsonBuilder().create();
		SMSResponseObject response = gson.fromJson(stringBuffer.toString(), SMSResponseObject.class);
		if("success".equals(response.getStatus())){
			return true;
		}
		throw new Exception(stringBuffer.toString());
	}

	public void receiveAddress(SMSPayload payload) {
		Prescription ps = findOrder(payload);
		Address addr = extractAddress(payload);
		eRxDB.Address adr = adaptAddress(addr);
		PersistenceWrapper.save(adr);
		int addressId = adr.getId();
		ps.setAddressid(addressId);
	}

	private eRxDB.Address adaptAddress(Address addr) {
		eRxDB.Address adr = new eRxDB.Address();
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
		// TODO Auto-generated method stub
		return null;
	}

	public boolean sendOrderConfirmation(Prescription ps, Patient p) {
		String message = createMessaageFromPrescription(ps);
		try {
			sendMessage(p, message);
		} catch (Exception e) {
			e.printStackTrace();
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
