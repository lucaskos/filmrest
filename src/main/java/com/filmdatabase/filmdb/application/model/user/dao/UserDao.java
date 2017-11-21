package com.filmdatabase.filmdb.application.model.user.dao;

import com.filmdatabase.filmdb.application.model.user.User;

import java.util.List;

public interface UserDao {

	void createUser(User user);

	List<User> getAllUsers();

	User getUser(String username);

	void removeUser(User user);
	
	void update(User user);

}
