package com.erx.ctx;

import com.erx.obj.Doctor;

public class ApplicationContext {
	
	private static ThreadLocal<ApplicationContext> context = new ThreadLocal<>();

	public static ApplicationContext getContext(){
		if(context.get() == null){
			context.set(new ApplicationContext());
		}
		return context.get();
	}
	
	public static void clear(){
		context.remove();
	}
	
	private Doctor doc;
	
	public Doctor getDoc() {
		return doc;
	}

	public void setDoc(Doctor doc) {
		this.doc = doc;
	}

	private ApplicationContext() {
	}
	
	public Doctor getDoctor(){
		return doc;
	}
	
	
}
