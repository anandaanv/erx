package com.erx.service.handler.sms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import com.erx.ex.ERxException;
import com.erx.obj.Patient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ERxSMSGateway extends SMSGateway {
	private static ERxSMSGateway instance = new ERxSMSGateway();
	
	Logger logger = Logger.getLogger(ERxSMSGateway.class.getName());

	private ERxSMSGateway() {
	}


	public static ERxSMSGateway getInstance() {
		return instance;
	}

	@Override
	protected boolean sendMessage(Patient patient, String message)
			throws ERxException {
		try{
			String user = "username=" + "anand@dheemantech.com";
			String hash = "&apiKey=" + "eoAX0CWKnug-6M4EVdymRfFSni3MAZ54Gb8Jnu63r6";
			String sender = "&sender=" + "TXTLCL";
			String numbers = "&numbers=" + "91" + patient.getPhoneNumber();
			String smsText = "&message=" + message;
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("http://api.textlocal.in/send/?").openConnection();
			String data = user + hash + numbers + smsText + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			conn.getOutputStream().flush();
			conn.getOutputStream().close();
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder stringBuilder = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuilder.append(line);
			}
			rd.close();
			Gson gson = new GsonBuilder().create();
			SMSResponseObject response = gson.fromJson(stringBuilder.toString(), SMSResponseObject.class);
			if("success".equals(response.getStatus())){
				return true;
			}
			throw new ERxException(stringBuilder.toString());
		}catch(IOException ex){
			throw new ERxException(ex);
		}
		
	}

}
