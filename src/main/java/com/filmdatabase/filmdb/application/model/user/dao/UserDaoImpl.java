package com.filmdatabase.filmdb.application.model.user.dao;

import java.util.List;

import javax.sql.DataSource;

import com.filmdatabase.filmdb.application.model.user.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.filmdatabase.filmdb.application.model.user.role.Role;
import com.filmdatabase.filmdb.application.model.user.role.RoleDao;

@Component
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private RoleDao roleDao;

//	private NamedParameterJdbcTemplate jdbc;

	/**
	 * We can't directly autwired field of dataSource because it must be passed
	 * to jdbc template in order to work
	 */
//	@Autowired
//	public void setDataSource(DataSource jdbc) {
//		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
//	}

	private Session session() {
		try {
			return sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			return sessionFactory.openSession();
		}
	}
/*
 * by default each user.role will equal ROLE_USER
 */
	public void createUser(User user) {
		Role role = roleDao.getRole("ROLE_USER");
		
		user.setRole(role);
//		user.setPassword(passwordEncoder.encode(user.getPassword()));
		session().save(user);
	}


	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		List<User> list = session().createQuery("from User").list();
		if (list.isEmpty())
			return null;
		return list;
	}

	public User getUser(String username) {
		User activeUser;
		String query = "from User where username = ?1";
		List<?> list = session().createQuery(query).setParameter("1", username).list();
		if (!list.isEmpty()) {
			activeUser = (User) list.get(0);
		} else {
			return null;
		}
		return activeUser;
	}

	@Override
	public void removeUser(User user) {
		session().delete(user);
	}

	@Override
	public void update(User user) {
		session().update(user);
	}
}
