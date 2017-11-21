package com.filmdatabase.filmdb.application.model.user.role;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class RoleDaoImpl implements RoleDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Role getRole(String role) {
		List<Role> list = session().createQuery("from Role where role = ?1").setParameter("1", role).list();
		if(!list.isEmpty())
			return list.get(0);
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getAllRoles() {
		List<Role> list = session().createQuery("from Role").list();
		return list;
	}

}
