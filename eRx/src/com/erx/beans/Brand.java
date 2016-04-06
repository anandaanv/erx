package com.erx.beans;

public class Brand extends BaseBean{
	private String brandName;

	public String getBrandName() {
		return brandName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((brandName == null) ? 0 : brandName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Brand other = (Brand) obj;
		if (brandName == null) {
			if (other.brandName != null)
				return false;
		} else if (!brandName.equals(other.brandName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Brand [brandName=" + brandName + ", id=" + id + "]";
	}

	public Brand(int id, String brandName) {
		super(id);
		this.brandName = brandName;
	}

	public Brand(eRxDB.Brand brand) {
		this(brand.getId(), brand.getName());
	}

	
	
}
