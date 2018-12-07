package erxdb;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * The persistent class for the brand database table.
 * 
 */
@Entity
@Table(name="user")
@NamedQueries(value={
		@NamedQuery(name="User.FindById", query="select u from User u where u.userId=:userId"),
		@NamedQuery(name="User.FindByIdAndPassword", query="select u from User u where u.userId=:userId and u.encryptedPassword=:password")
})
@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=100)
	private String name;

	@Column(length=300)
	private String encryptedPassword;
	
	@Column(length = 10)
	private String status;
	
	@Column(length = 50)
	private String email;
	
	@Column
	private boolean enabled;
	
	@Column(length = 12)
	private String phoneNumber;
	
	@Column(length = 50)
	private String userId;
}