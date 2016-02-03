package eRxDB;

import java.io.Serializable;
import javax.persistence.*;


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

	private int patientid;

	@Column(length=45)
	private String patientname;

	@Column(length=15)
	private String phonenumber;

	//bi-directional many-to-one association to Doctor
	@ManyToOne
	@JoinColumn(name="doctorid")
	private Doctor doctor;

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

	public int getPatientid() {
		return this.patientid;
	}

	public void setPatientid(int patientid) {
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

}