package com.filmdatabase.filmdb.application.model.cast;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import com.filmdatabase.filmdb.application.model.person.Person;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.filmdatabase.filmdb.application.model.film.Film;

@Component
@Transactional
public class CastDaoImpl implements CastDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session session() {
		try {
			return sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			return sessionFactory.openSession();
		}
	}

	/*
	 * Checking if in provided film the person has certain role. If person and
	 * role not existing in the film new Cast is created. Otherwise update for
	 * the role is made.
	 */
	public void addActorToFilm(Film film, Person person, String role) {
//		Set<Cast> givenActorCast = person.getActorFilms();
//		Cast newCast = new Cast(film, person);
		// no film for this person so we create one
//		if (givenActorCast.isEmpty()) {
//			newCast.setRole(role);
//			session().save(newCast);
//		} else {
//			Cast c = isInFilm(givenActorCast, film);
//			if (c != null) {
//				// if he is in this film update role
//				//so we must iterate and find cast responsible for the
//				//given film
//				c.setRole(role);
//				session().update(c);
//			} else {
//				newCast.setRole(role);
//				System.out.println(newCast);
//				session().save(newCast);
//				// adding new cast and connection to this person
//			}
//		}

	}
	private Cast isInFilm(Set<Cast> actorFilms, Film film) {
		for (Cast cast : actorFilms)
			if (cast.getFilm().getTitle().equalsIgnoreCase(film.getTitle()))
				return cast;
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Cast> getCastOfFilm(Film film) {
		String query = "from Cast where film =?1";
		List<Cast> castListOfFilm = session().createQuery(query).setParameter("1", film).list();
		// return actorRole;
		return castListOfFilm;
	}

	@SuppressWarnings("unchecked")
	public List<Cast> getFilmographyOfActor(Person person) {
		String query = "from Cast";
		List<Cast> castListOfActor = session().createQuery(query).list();
		return castListOfActor;
	}

}
