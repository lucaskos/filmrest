package com.filmdatabase.filmdb.application.model.rating;


import javax.persistence.*;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "inertRating",
                procedureName = "INSERT_RATING",
                resultClasses = {Rating.class},
                parameters = {
                        @StoredProcedureParameter(
                                name = "in_user_id",
                                type = Integer.class,
                                mode = ParameterMode.IN),
                        @StoredProcedureParameter(
                                name = "in_film_id",
                                type = Integer.class,
                                mode = ParameterMode.IN),

                        @StoredProcedureParameter(
                                name = "in_person_id",
                                type = Integer.class,
                                mode = ParameterMode.IN
                        ),

                        @StoredProcedureParameter(
                                name = "in_rating",
                                type = Integer.class,
                                mode = ParameterMode.IN
                        )
                        ,
//                        @StoredProcedureParameter(
//                                name = "out_result",
//                                type = Integer.class,
//                                mode = ParameterMode.OUT
//                        )
                })
})
@Entity
@Table(name = "rating_films")
public class Rating {

    private Long ratingId;
    private int rating;
    private Integer userId;
    private Integer filmId;
    private Integer personId;

    public Rating() {

    }

    public Rating(int rating, Integer userId, int filmId) {
        this.rating = rating;
        this.userId = userId;
        this.filmId = filmId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rating_id")
    public Long getRatingId() {
        return ratingId;
    }

    public void setRatingId(Long ratingId) {
        this.ratingId = ratingId;
    }

    @JoinColumn(name = "film_id")
    public Integer getFilmId() {
        return filmId;
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

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    @Column(name = "person_id")
    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    @Override
    public String toString() {
        return "Rating [ratingId=" + ratingId + ", rating=" + rating + ", user=" + userId + ", film=" + filmId + "]";
    }

}
