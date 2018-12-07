package erxdb;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="userrole")
@NamedQueries(value={
		@NamedQuery(name="UserRole.FindByUserRole", query="select u from UserRole u where u.user=:user and u.role=:role")
})
@Getter
@Setter
@NoArgsConstructor
public class UserRole {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user", referencedColumnName="id")
	private User user;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="role", referencedColumnName="id")
	private Role role;
	
	@Column
	private int status;

	@Column
	private int refId;
}
