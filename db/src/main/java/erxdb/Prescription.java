package erxdb;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * The persistent class for the prescription database table.
 * 
 */
@Entity
@Table(name="prescription")
@NamedQuery(name="Prescription.findAll", query="SELECT p FROM Prescription p")
@Getter
@Setter
@NoArgsConstructor
public class Prescription implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	private int addressid;

	@Column(length=200)
	private String diagnosys;

	@Column(length=45)
	private String doctorname;

	@Column(length=40)
	private String externaltxnid;

	private int orderstatus;

	@OneToOne(fetch = FetchType.LAZY, targetEntity=Patient.class)
	@JoinColumn(name="id", updatable=true)
	private Patient patientid;

	@Column(length=45)
	private String patientname;

	@Column(length=15)
	private String phonenumber;

	//bi-directional many-to-one association to Doctor
	@ManyToOne
	@JoinColumn(name="doctorid")
	private Doctor doctor;
	
	@Column(length=10000)
	private byte[] prescriptionDoc;
	
	@Column(updatable=false)
	private int patientOrderId;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="prescription")
	private List<Prescriptionrow> medicines;

	@Column(nullable = false)
	private Date created;

	@Column(nullable = false)
	private Date updated;

	@PrePersist
	protected void onCreate() {
		created = updated = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updated = new Date();
	}

}