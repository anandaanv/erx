package com.erx.beans;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import com.erx.beans.BaseBean;
import com.erx.obj.Dose;

public class Medicine extends BaseBean {

	private int id;
	private int applicantLicenseNo;
	private Brand brandName;
	private String medRegNo;
	private String nappiCode;
	private String atc4;
	private String medicineSchedule;
	private String proprietaryName;
	private String activeIngredients;
	private double strength;
	private String unit;
	private String dosage_form;
	private int pack_size;
	private int quantity;
	private double manuf_price;
	private double logistics_fee;
	private double vat;
	private double sep;
	private double unit_price;
	private Date effective_date; 
	private String status;
	private String generic_or_originator;
	private int sales_volume;
	
		//bi-directional many-to-one association to Brand
	/*@ManyToOne
	@JoinColumn(name="brandid", nullable=false)
	private Brand brand;
	*/

	public Medicine() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the applicantLicenseNo
	 */
	public int getApplicantLicenseNo() {
		return applicantLicenseNo;
	}

	/**
	 * @param applicantLicenseNo the applicantLicenseNo to set
	 */
	public void setApplicantLicenseNo(int applicantLicenseNo) {
		this.applicantLicenseNo = applicantLicenseNo;
	}

	/**
	 * @return the applicantName
	 */
	public Brand getBrandName() {
		return brandName;
	}

	/**
	 * @param applicantName the applicantName to set
	 */
	public void setBrandName(Brand brandName) {
		this.brandName = brandName;
	}

	/**
	 * @return the medRegNo
	 */
	public String getMedRegNo() {
		return medRegNo;
	}

	/**
	 * @param medRegNo the medRegNo to set
	 */
	public void setMedRegNo(String medRegNo) {
		this.medRegNo = medRegNo;
	}

	/**
	 * @return the nappiCode
	 */
	public String getNappiCode() {
		return nappiCode;
	}

	/**
	 * @param nappiCode the nappiCode to set
	 */
	public void setNappiCode(String nappiCode) {
		this.nappiCode = nappiCode;
	}

	/**
	 * @return the atc4
	 */
	public String getAtc4() {
		return atc4;
	}

	/**
	 * @param atc4 the atc4 to set
	 */
	public void setAtc4(String atc4) {
		this.atc4 = atc4;
	}

	/**
	 * @return the medicineSchedule
	 */
	public String getMedicineSchedule() {
		return medicineSchedule;
	}

	/**
	 * @param medicineSchedule the medicineSchedule to set
	 */
	public void setMedicineSchedule(String medicineSchedule) {
		this.medicineSchedule = medicineSchedule;
	}

	/**
	 * @return the proprietaryName
	 */
	public String getProprietaryName() {
		return proprietaryName;
	}

	/**
	 * @param proprietaryName the proprietaryName to set
	 */
	public void setProprietaryName(String proprietaryName) {
		this.proprietaryName = proprietaryName;
	}

	/**
	 * @return the activeIngredients
	 */
	public String getActiveIngredients() {
		return activeIngredients;
	}

	/**
	 * @param activeIngredients the activeIngredients to set
	 */
	public void setActiveIngredients(String activeIngredients) {
		this.activeIngredients = activeIngredients;
	}

	/**
	 * @return the strength
	 */
	public double getStrength() {
		return strength;
	}

	/**
	 * @param strength the strength to set
	 */
	public void setStrength(double strength) {
		this.strength = strength;
	}

	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * @return the dosage_form
	 */
	public String getDosage_form() {
		return dosage_form;
	}

	/**
	 * @param dosage_form the dosage_form to set
	 */
	public void setDosage_form(String dosage_form) {
		this.dosage_form = dosage_form;
	}

	/**
	 * @return the pack_size
	 */
	public int getPack_size() {
		return pack_size;
	}

	/**
	 * @param pack_size the pack_size to set
	 */
	public void setPack_size(int pack_size) {
		this.pack_size = pack_size;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the manuf_price
	 */
	public double getManuf_price() {
		return manuf_price;
	}

	/**
	 * @param manuf_price the manuf_price to set
	 */
	public void setManuf_price(double manuf_price) {
		this.manuf_price = manuf_price;
	}

	/**
	 * @return the logistics_fee
	 */
	public double getLogistics_fee() {
		return logistics_fee;
	}

	/**
	 * @param logistics_fee the logistics_fee to set
	 */
	public void setLogistics_fee(double logistics_fee) {
		this.logistics_fee = logistics_fee;
	}

	/**
	 * @return the vat
	 */
	public double getVat() {
		return vat;
	}

	/**
	 * @param vat the vat to set
	 */
	public void setVat(double vat) {
		this.vat = vat;
	}

	/**
	 * @return the sep
	 */
	public double getSep() {
		return sep;
	}

	/**
	 * @param sep the sep to set
	 */
	public void setSep(double sep) {
		this.sep = sep;
	}

	/**
	 * @return the unit_price
	 */
	public double getUnit_price() {
		return unit_price;
	}

	/**
	 * @param unit_price the unit_price to set
	 */
	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}

	/**
	 * @return the effective_date
	 */
	public Date getEffective_date() {
		return effective_date;
	}

	/**
	 * @param effective_date the effective_date to set
	 */
	public void setEffective_date(Date effective_date) {
		this.effective_date = effective_date;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the generic_or_originator
	 */
	public String getGeneric_or_originator() {
		return generic_or_originator;
	}

	/**
	 * @param generic_or_originator the generic_or_originator to set
	 */
	public void setGeneric_or_originator(String generic_or_originator) {
		this.generic_or_originator = generic_or_originator;
	}

	/**
	 * @return the sales_volume
	 */
	public int getSales_volume() {
		return sales_volume;
	}

	/**
	 * @param sales_volume the sales_volume to set
	 */
	public void setSales_volume(int sales_volume) {
		this.sales_volume = sales_volume;
	}
	
	@Override
	public String toString() {
		return "Medicine [proprietaryName=" + proprietaryName + ", brand=" + brandName + "]";
	}

	public Medicine(int id, String proprietaryName, Brand brand, MedicineType type,
			List<Dose> dosage) {
		super(id);
		this.proprietaryName = proprietaryName;
		this.brandName = brand;
		//this.type = type;
		//this.dosage = dosage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((brandName == null) ? 0 : brandName.hashCode());
		result = prime * result + ((proprietaryName == null) ? 0 : proprietaryName.hashCode());
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
		Medicine other = (Medicine) obj;
		if (brandName == null) {
			if (other.brandName != null)
				return false;
		} else if (!brandName.equals(other.brandName))
			return false;
		if (proprietaryName == null) {
			if (other.proprietaryName != null)
				return false;
		} else if (!proprietaryName.equals(other.proprietaryName))
			return false;
		return true;
	}

}
