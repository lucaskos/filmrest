package com.filmdatabase.filmdb.application.model.cast;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.filmdatabase.filmdb.application.model.actor.Person;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.filmdatabase.filmdb.application.model.film.Film;

@Entity
@Table(name = "actor_film")
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class Cast implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6424609370178798738L;

	@Column(name = "actor_film_id")
	@Id
	@GeneratedValue
	private int actorFilmId;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "film_id")
	private Film film;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "person_id")
	private Person person;

	@Column(name = "role")
	private String role;

	public Cast() {

	}
	
	public Cast(Film f) {
		this.film = f;

	}

	public int getActorFilmId() {
		return actorFilmId;
	}

	public void setActorFilmId(int actorFilmId) {
		this.actorFilmId = actorFilmId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
