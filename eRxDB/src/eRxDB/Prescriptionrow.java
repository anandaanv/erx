package eRxDB;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


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