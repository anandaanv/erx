package com.erx.obj;

public class Certificate {
	private User user;

	private byte[] certificate;
	
	private byte[] signImage;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public byte[] getCertificate() {
		return certificate;
	}

	public void setCertificate(byte[] certificate) {
		this.certificate = certificate;
	}

	public byte[] getSignImage() {
		return signImage;
	}

	public void setSignImage(byte[] signImage) {
		this.signImage = signImage;
	}
	
	
}
