package eRxDB;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: MedicineDiagnosys
 *
 */
@Entity
@Table(name = "medicinediadnosys")
@NamedQuery(name = "MedicineDiadnosys.findAll", query = "SELECT m FROM MedicineDiagnosys m")
public class MedicineDiagnosys implements Serializable {

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

	@Column(nullable = false)
	private Doctor doctor;

	@Column(nullable = false)
	private Medicine medicine;

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
