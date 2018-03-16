package com.filmdatabase.filmdb.application.model.user.role;

import javax.persistence.*;

import com.filmdatabase.filmdb.application.model.user.dao.User;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {

	private int id;
	private String role;
	private User user;

	public Role() {

	}

	public Role(String role) {
		this.role = role;
	}

	@Id
	@GeneratedValue
	@Column(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "role")
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinTable(name="users_roles",
			joinColumns = {@JoinColumn(name="users_id", referencedColumnName="id")},
			inverseJoinColumns = {@JoinColumn(name="roles_id", referencedColumnName="id")}
	)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
