package in.nareshit.raghu.model;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="user_tab")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="uid")
	private Integer id;

	@Column(name="uname")
	private String userName;
	
	@Column(name="umail")
	private String userMail;
	
	@Column(name="upwd")
	private String userPwd;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(
			name="roles_tab",
			joinColumns = @JoinColumn(name="uid")
			)
	@Column(name="urole")
	private Set<String> userRoles;
	
}
