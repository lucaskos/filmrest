package com.filmdatabase.filmdb.application.model.user.role;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.filmdatabase.filmdb.application.model.user.dao.User;

import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {

	private int id;
	private String role;
	private Set<User> userRoles;

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

	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="users_roles",
			joinColumns = {@JoinColumn(name="users_id", referencedColumnName="id")},
			inverseJoinColumns = {@JoinColumn(name="roles_id", referencedColumnName="id")}
	)
	public java.util.Set<User> getUsersSet() {
		return userRoles;
	}

	public void setUsersSet(java.util.Set<User> usersSet) {
		this.userRoles = usersSet;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", role=" + role +  "]";
	}


}
