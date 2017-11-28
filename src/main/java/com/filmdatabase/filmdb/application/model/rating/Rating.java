package com.filmdatabase.filmdb.application.model.rating;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Entity
@Table(name = "rating_films")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
public class Rating {


    private int ratingId;
    private int rating;
    private Integer userId;
    private Integer film;

    public Rating() {

    }

    public Rating(int rating, Integer userId, int filmId) {
        this.rating = rating;
        this.userId = userId;
        this.film = filmId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rating_id")
    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    @JoinColumn(name = "film_id")
    public Integer getFilm() {
        return film;
    }

    @Column(name = "rating")
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer user) {
        this.userId = user;
    }

    public void setFilm(Integer filmId) {
        this.film = filmId;
    }

    @Override
    public String toString() {
        return "Rating [ratingId=" + ratingId + ", rating=" + rating + ", user=" + userId + ", film=" + film + "]";
    }

}
