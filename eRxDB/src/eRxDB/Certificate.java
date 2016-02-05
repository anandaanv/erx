package eRxDB;

import java.io.Serializable;
import java.lang.Byte;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Certificate
 *
 */
@Entity

public class Certificate implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;
	@Column(nullable=false, unique=true, length=150)
	private String certKey;
	@Column(length=10000, nullable=false)
	private byte[] value;
	@Column(length=10000, nullable=false)
	private byte[] signImage;
	private static final long serialVersionUID = 1L;

	public Certificate() {
		super();
	}   
	public String getKey() {
		return this.certKey;
	}

	public void setKey(String key) {
		this.certKey = key;
	}   
	public byte[] getValue() {
		return this.value;
	}

	public void setValue(byte[] value) {
		this.value = value;
	}
	public byte[] getSignImage() {
		return signImage;
	}
	public void setSignImage(byte[] signImage) {
		this.signImage = signImage;
	}
   
}
