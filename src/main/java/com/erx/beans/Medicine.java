package com.erx.beans;

import java.util.Date;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
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
	
	@Override
	public String toString() {
		return "Medicine [proprietaryName=" + proprietaryName + ", brand=" + brandName + "]";
	}

	public Medicine(int id, String proprietaryName, Brand brand, String dosageForm) {
		super(id);
		this.proprietaryName = proprietaryName;
		this.brandName = brand;
		this.dosage_form = dosageForm;
	}
}
