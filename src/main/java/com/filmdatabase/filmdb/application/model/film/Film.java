package com.filmdatabase.filmdb.application.model.film;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.filmdatabase.filmdb.application.model.FilmRelation;
import com.filmdatabase.filmdb.application.model.PersonRelation;
import com.filmdatabase.filmdb.application.model.cast.Cast;
import org.hibernate.validator.constraints.NotBlank;

import com.filmdatabase.filmdb.application.model.rating.Rating;

@Entity
@Table(name = "FILM")
public class Film {

	private int filmId;
	private String title;
	private Integer year;
	private String description;

//	@JsonIgnore
//	@OneToMany(mappedBy = "film", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Set<Cast> allCast = new HashSet<Cast>();

	private Set<FilmRelation> filmRelations;

	private Set<Rating> rating = new HashSet<Rating>();

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "film", cascade = CascadeType.ALL)
	public Set<Rating> getRating() {
		return rating;
	}

	public void setRating(Set<Rating> rating) {
		this.rating = rating;
	}

	public Film() {

	}

	@JsonIgnore
	@OneToMany(targetEntity = FilmRelation.class, mappedBy = "film", fetch = FetchType.LAZY, cascade = CascadeType
			.ALL)
	public Set<FilmRelation> getFilmRelations() {
		return filmRelations;
	}

	public void setFilmRelations(Set<FilmRelation> filmRelations) {
		this.filmRelations = filmRelations;
	}

	public Film(String title, int year) {
		this.title = title;
		this.year = year;
	}

	public Film(String title, int year, String description) {
		this.title = title;
		this.year = year;
		this.description = description;
	}

	public void addActor(Cast actorsFilms) {
		this.allCast.add(actorsFilms);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "FILM_ID")
	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	@Size(max = 60)
	@NotBlank
	@Column(name = "TITLE")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "YEAR")
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@NotBlank
	@Size(min = 10)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + filmId;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (filmId != other.filmId)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Film [filmId=" + filmId + ", title=" + title + ", year=" + year + ", description=" + description
				+ "]";
	}

}
