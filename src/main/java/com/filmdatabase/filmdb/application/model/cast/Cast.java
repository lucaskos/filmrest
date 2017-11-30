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
	private Integer film;
	private Integer person;
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

	@Column(name = "film_id")
	public Integer getFilm() {
		return film;
	}

	public void setFilm(Integer film) {
		this.film = film;
	}

	@Column(name = "person_id")
	public Integer getPerson() {
		return person;
	}

	public void setPerson(Integer person) {
		this.person = person;
	}
}
