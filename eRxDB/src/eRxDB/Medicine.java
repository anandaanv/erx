package eRxDB;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * The persistent class for the medicine database table.
 * 
 */
@Entity
@Table(name="medicine")
@NamedQuery(name="Medicine.findAll", query="SELECT m FROM Medicine m")
public class Medicine implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=true)
	private int id;

	@Column(nullable=true, precision=12)
	private int applicantLicenseNo;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="brand", referencedColumnName="id")
	private Brand brand;
	
	@Column(nullable=true, length=50)
	private String medRegNo;
	
	@Column(nullable=true, length=20)
	private String nappiCode;
	
	@Column(nullable=true, length=10)
	private String atc4;
	
	@Column(nullable=true, length=255)
	private String medicineSchedule;

	@Column(nullable=true, length=120)
	private String proprietaryName;
	
	@Column(nullable=true, length=150)
	private String activeIngredients;
	
	@Column(nullable=true)
	private double strength;
	
	@Column(nullable=true, length=25)
	private String unit;
	
	@Column(nullable=true, length=15)
	private String dosage_form;
	
	@Column(nullable=true, length=10, precision=4)
	private double pack_size;
	
	@Column(nullable=true)
	private int quantity;
	
	@Column(nullable=true)
	private double manuf_price;
	
	@Column(nullable=true)
	private double logistics_fee;
	
	@Column(nullable=true)
	private double vat;
	
	@Column(nullable=true)
	private double sep;
	
	@Column(nullable=true)
	private double unit_price;
	
	@Column(nullable=true)
	private Date effective_date; 
	
	@Column(nullable=true, length=255)
	private String status;
	
	@Column(nullable=true, length=255)
	private String generic_or_originator;
	
	@Column
	private int sales_volume;
	
		//bi-directional many-to-one association to Brand
	/*@ManyToOne
	@JoinColumn(name="brandid", nullable=true)
	private Brand brand;
	*/

	public Medicine() {
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
	public Brand getBrand() {
		return brand;
	}

	/**
	 * @param applicantName the applicantName to set
	 */
	public void setBrand(Brand brandName) {
		this.brand = brandName;
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
	public double getPack_size() {
		return pack_size;
	}

	/**
	 * @param pack_size the pack_size to set
	 */
	public void setPack_size(double pack_size) {
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

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}