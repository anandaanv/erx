package com.erx.beans;

public class BaseBean {
	protected int id;
	
	protected BaseBean() {
		super();
	}

	protected BaseBean(int id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		return "BaseBean [id=" + id + "]";
	}

	public int getId() {
		return id;
	}
	
	
}
