package com.erx.service.handler;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import eRxDB.Certificate;
import eRxDB.Doctor;
import eRxDB.Patient;
import eRxDB.Prescription;
import eRxDB.persistence.PersistenceWrapper;

public class ERxHelper {
	public static Prescription findOrder(int orderId){
		EntityManager em = PersistenceWrapper.getEntitymanager();
		Prescription ps = em.find(Prescription.class, orderId);
		return ps;
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
	    catch (NoSuchAlgorithmException e) 
	    {
	        e.printStackTrace();
	    }
	    return generatedPassword;
	}
	
	public static Certificate getCertificate(Doctor doc){
		Patient p = null;
		String id = ERxHelper.encrypt(doc.getUsername());
		String hql = "Select c from Certificate c where c.certKey=:id";      
		System.out.println(hql);
		Query query = PersistenceWrapper.getEntitymanager().createQuery(hql);
		query.setParameter("id", id);
		List<Certificate> result = query.getResultList();
		return result.get(0);
	}

}
