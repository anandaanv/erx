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


/**
 * The persistent class for the prescription database table.
 * 
 */
@Entity
@Table(name="prescription")
@NamedQuery(name="Prescription.findAll", query="SELECT p FROM Prescription p")
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

	public Prescription() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAddressid() {
		return this.addressid;
	}

	public void setAddressid(int addressid) {
		this.addressid = addressid;
	}

	public String getDiagnosys() {
		return this.diagnosys;
	}

	public void setDiagnosys(String diagnosys) {
		this.diagnosys = diagnosys;
	}

	public String getDoctorname() {
		return this.doctorname;
	}

	public void setDoctorname(String doctorname) {
		this.doctorname = doctorname;
	}

	public String getExternaltxnid() {
		return this.externaltxnid;
	}

	public void setExternaltxnid(String externaltxnid) {
		this.externaltxnid = externaltxnid;
	}

	public int getOrderstatus() {
		return this.orderstatus;
	}

	public void setOrderstatus(int orderstatus) {
		this.orderstatus = orderstatus;
	}

	public Patient getPatientid() {
		return this.patientid;
	}

	public void setPatientid(Patient patientid) {
		this.patientid = patientid;
	}

	public String getPatientname() {
		return this.patientname;
	}

	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}

	public String getPhonenumber() {
		return this.phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public Doctor getDoctor() {
		return this.doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
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

	public byte[] getPrescriptionDoc() {
		return prescriptionDoc;
	}

	public void setPrescriptionDoc(byte[] prescriptionDoc) {
		this.prescriptionDoc = prescriptionDoc;
	}


}