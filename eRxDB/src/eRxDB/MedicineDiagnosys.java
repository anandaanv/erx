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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: MedicineDiagnosys
 *
 */
@Entity
@Table(name = "medicinediadnosys")
@NamedQuery(name = "MedicineDiagnosys.findByKey", query = "SELECT m FROM MedicineDiagnosys m where m.doctor=:doc and m.medicine.id = :med and m.diagnosys=:dyn")
public class MedicineDiagnosys implements Serializable {
	
	public String getDiagnosys() {
		return diagnosys;
	}

	public void setDiagnosys(String diagnosys) {
		this.diagnosys = diagnosys;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public static String FINDBYDOCDIAGN = "SELECT m FROM MedicineDiagnosys m where m.doctor=:doc and m.diagnosys=:dyn";

	private static final long serialVersionUID = 1L;

	public MedicineDiagnosys() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;

	@Column(unique = false, nullable = false)
	private String diagnosys;

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="doctor", referencedColumnName="id")
	private Doctor doctor;

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="medicine", referencedColumnName="id")
	private Medicine medicine;

	@Column(nullable = false)
	private Date created;

	@Column(nullable = false)
	private Date updated;

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
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
