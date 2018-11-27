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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity implementation class for Entity: MedicineDiagnosys
 *
 */
@Entity
@Table(name = "medicinediadnosys")
@NamedQuery(name = "MedicineDiagnosys.findByKey", query = "SELECT m FROM MedicineDiagnosys m where m.doctor=:doc and m.medicine.id = :med and m.diagnosys=:dyn")
@Getter
@Setter
@NoArgsConstructor
public class MedicineDiagnosys implements Serializable {

	public static final String FINDBYDOCDIAGN = "";
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
