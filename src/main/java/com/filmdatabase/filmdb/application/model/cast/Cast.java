package com.filmdatabase.filmdb.application.model.cast;

import java.io.Serializable;

import javax.persistence.*;

import com.filmdatabase.filmdb.application.model.person.Person;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.filmdatabase.filmdb.application.model.film.Film;

@Entity
@Table(name = "actor_film")
public class Cast implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6424609370178798738L;

	private int actorFilmId;
	private Film film;
	private Person person;
	private String role;

	public Cast() {

	}

	@Column(name = "actor_film_id")
	@Id
	@GeneratedValue
	public int getActorFilmId() {
		return actorFilmId;
	}

	public void setActorFilmId(int actorFilmId) {
		this.actorFilmId = actorFilmId;
	}

	@Column(name = "role")
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@ManyToOne
	@JoinColumn(name = "film_id")
	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	@ManyToOne
	@JoinColumn(name = "person_id")
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
