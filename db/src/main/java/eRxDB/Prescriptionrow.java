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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * The persistent class for the prescriptionrow database table.
 * 
 */
@Entity
@Table(name="prescriptionrow")
@NamedQuery(name="Prescriptionrow.findAll", query="SELECT p FROM Prescriptionrow p")
public class Prescriptionrow implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=150)
	private String doses;

	@Column(nullable=false)
	private int medicineid;

	@Column(nullable=false, length=120)
	private String medicinename;

	@Column(nullable=false)
	private int numunits;

	@Column(nullable = false)
	private Date created;

	@Column(nullable = false)
	private Date updated;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="prescription", referencedColumnName="id")
	private Prescription prescription;
	
	public Prescription getPrescription() {
		return prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

	public Date getCreated() {
		return created;
	}

	public Date getUpdated() {
		return updated;
	}

	public Prescriptionrow() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDoses() {
		return this.doses;
	}

	public void setDoses(String doses) {
		this.doses = doses;
	}

	public int getMedicineid() {
		return this.medicineid;
	}

	public void setMedicineid(int medicineid) {
		this.medicineid = medicineid;
	}

	public String getMedicinename() {
		return this.medicinename;
	}

	public void setMedicinename(String medicinename) {
		this.medicinename = medicinename;
	}

	public int getNumunits() {
		return this.numunits;
	}

	public void setNumunits(int numunits) {
		this.numunits = numunits;
	}
	
	@PrePersist
	protected void onCreate() {
		created = updated = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updated = new Date();
	}


}