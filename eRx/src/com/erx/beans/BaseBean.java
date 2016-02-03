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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseBean other = (BaseBean) obj;
		if (id != other.id) {
			return false;
		}return true;
	}

	public int getId() {
		return id;
	}
	
	
}
