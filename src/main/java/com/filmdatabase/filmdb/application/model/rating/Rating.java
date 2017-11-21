package com.filmdatabase.filmdb.application.model.rating;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.filmdatabase.filmdb.application.model.film.Film;
import com.filmdatabase.filmdb.application.model.user.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(name = "rating_films")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly=false)
public class Rating {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "rating_id")
	private int ratingId;

	@Column(name = "rating")
	private int rating;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "users_id")
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "film_id")
	private Film film;
	
	public Rating() {
		
	}

	public Rating(int rating, User user, Film film) {
		this.rating = rating;
		this.user = user;
		this.film = film;
	}

	public int getRatingId() {
		return ratingId;
	}

	public void setRatingId(int ratingId) {
		this.ratingId = ratingId;
	}

	public Film getFilm() {
		return film;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	@Override
	public String toString() {
		return "Rating [ratingId=" + ratingId + ", rating=" + rating + ", user=" + user + ", film=" + film + "]";
	}

}
