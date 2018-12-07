package erxdb;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * The persistent class for the address database table.
 * 
 */
@Entity
@Table(name="address")
@NamedQuery(name="Address.findAll", query="SELECT a FROM Address a")
@Getter
@Setter
@NoArgsConstructor
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=45)
	private String address1;

	@Column(length=45)
	private String address2;

	@Column(length=45)
	private String address3;

	@Column(length=45)
	private String address4;

	@Column(length=45)
	private String altname;

	@Column(length=45)
	private String city;

	@Column(length=45)
	private String country;

	@Column(length=45)
	private String landmark;

	@Column(length=60)
	private String name;

	@Column(length=45)
	private String state;

	@Column(length=45)
	private String zipcode;

	@Column(nullable = false)
	private Date created;

	@Column(nullable = false)
	private Date updated;
	
	//bi-directional many-to-many association to Patient
	@ManyToMany
	@JoinTable(
		name="patientaddress"
		, joinColumns={
			@JoinColumn(name="addressid")
			}
		, inverseJoinColumns={
			@JoinColumn(name="patientid")
			}
		)
	private List<Patient> patients;
}