package eRxDB;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity implementation class for Entity: Certificate
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
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
   
}
