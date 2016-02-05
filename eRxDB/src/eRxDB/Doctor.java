package eRxDB;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the doctor database table.
 * 
 */
@Entity
@Table(name="doctor")
@NamedQuery(name="Doctor.findAll", query="SELECT d FROM Doctor d")
public class Doctor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=45)
	private String name;

	@Column(length=100)
	private String qualification;

	@Column(length=45)
	private String regno;

	@Column(nullable=false, length=45)
	private String username;
	
	@Column(nullable=false, length=300)
	private String encryptedPassword;

	//bi-directional many-to-one association to Prescription
	@OneToMany(mappedBy="doctor")
	private List<Prescription> prescriptions;

	@Column(nullable = false)
	private Date created;

	@Column(nullable = false)
	private Date updated;
	
	public Doctor() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQualification() {
		return this.qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getRegno() {
		return this.regno;
	}

	public void setRegno(String regno) {
		this.regno = regno;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Prescription> getPrescriptions() {
		return Collections.unmodifiableList(this.prescriptions);
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public Date getCreated() {
		return created;
	}

	public Date getUpdated() {
		return updated;
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