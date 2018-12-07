package erxdb;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * The persistent class for the patient database table.
 * 
 */
@Entity
@Table(name="patient")
@NamedQuery(name="Patient.findAll", query="SELECT p FROM Patient p")
@Getter
@Setter
@NoArgsConstructor
public class Patient implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(unique=true, length=100, nullable=false)
	private String uniqueId;
	
	@Column(nullable=false, length=45)
	private String name;

	@Column(nullable=false, length=45)
	private String phonenumber;

	//bi-directional many-to-many association to Address
	@ManyToMany(mappedBy="patients")
	private List<Address> addresses;
	
	@Column(updatable=true)
	private int numPrescriptions;

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