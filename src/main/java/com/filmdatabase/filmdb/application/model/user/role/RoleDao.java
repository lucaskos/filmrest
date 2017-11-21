package com.filmdatabase.filmdb.application.model.user.role;

import java.util.List;

public interface RoleDao {
	
	Role getRole(String role);
	
	List<Role> getAllRoles();

}
