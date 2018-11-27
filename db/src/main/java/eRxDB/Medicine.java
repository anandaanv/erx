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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * The persistent class for the medicine database table.
 * 
 */
@Entity
@Table(name="medicine")
@NamedQuery(name="Medicine.findAllByString", query="SELECT m FROM Medicine m where m.proprietaryName like :name order by m.proprietaryName desc")
@Getter
@Setter
@NoArgsConstructor
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
}