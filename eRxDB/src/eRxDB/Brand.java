package eRxDB;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the brand database table.
 * 
 */
@Entity
@Table(name="brand")
@NamedQuery(name="Brand.findAll", query="SELECT b FROM Brand b")
public class Brand implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=100)
	private String name;

	//bi-directional many-to-one association to Medicine
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="brand")
	private List<Medicine> medicines;

	public Brand() {
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

	public List<Medicine> getMedicines() {
		return this.medicines;
	}

	public void setMedicines(List<Medicine> medicines) {
		this.medicines = medicines;
	}

	public Medicine addMedicine(Medicine medicine) {
		getMedicines().add(medicine);
		medicine.setBrand(this);

		return medicine;
	}

	public Medicine removeMedicine(Medicine medicine) {
		getMedicines().remove(medicine);
		medicine.setBrand(null);

		return medicine;
	}

}