package eRxDB;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * The persistent class for the doctor database table.
 * 
 */
@Entity
@Table(name="doctor")
@NamedQuery(name="Doctor.findAll", query="SELECT d FROM Doctor d")
@Getter
@Setter
@NoArgsConstructor
public class Doctor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=100)
	private String name;

	@Column(length=100)
	private String qualification;

	@Column(length=45)
	private String regno;

	@Column(nullable=false, length=45)
	private String username;

	//bi-directional many-to-one association to Prescription
	@OneToMany(mappedBy="doctor")
	private List<Prescription> prescriptions;

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