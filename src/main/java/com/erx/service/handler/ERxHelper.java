package com.erx.service.handler;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import erxdb.Certificate;
import erxdb.Doctor;
import erxdb.Prescription;
import erxdb.persistence.PersistenceWrapper;

public class ERxHelper {
	
	private ERxHelper() {
	}

	private static Logger logger = Logger.getLogger(ERxHelper.class.getName());
	private static final String FIND_CERT_BY_KEY = "Select c from Certificate c where c.certKey=:id";
	
	
	public static Prescription findOrder(int orderId){
		EntityManager em = PersistenceWrapper.getEntitymanager();
		return em.find(Prescription.class, orderId);
	}

	public static String encrypt(String passwordToHash){
	    String generatedPassword = null;
	    try {
	        // Create MessageDigest instance for MD5
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        //Add password bytes to digest
	        md.update(passwordToHash.getBytes());
	        //Get the hash's bytes 
	        byte[] bytes = md.digest();
	        //This bytes[] has bytes in decimal format;
	        //Convert it to hexadecimal format
	        StringBuilder sb = new StringBuilder();
	        for(int i=0; i< bytes.length ;i++)
	        {
	            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        //Get complete hashed password in hex format
	        generatedPassword = sb.toString();
	    } 
	    catch (NoSuchAlgorithmException e){
	    	logger.log(Level.SEVERE, e.getMessage(), e);
	    }
	    return generatedPassword;
	}
	
	public static Certificate getCertificate(Doctor doc){
		String id = ERxHelper.encrypt(doc.getUsername());
		logger.log(Level.INFO, FIND_CERT_BY_KEY);
		Query query = PersistenceWrapper.getEntitymanager().createQuery(FIND_CERT_BY_KEY);
		query.setParameter("id", id);
		List<Certificate> result = query.getResultList();
		return result.get(0);
	}

}
