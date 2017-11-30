package com.filmdatabase.filmdb.application.model.cache.dictionaries;

import com.filmdatabase.filmdb.application.commons.QualifierConstants;

import javax.persistence.*;

/**
 * Created by Luke on 25.10.2017.
 */
@Entity
@Table(name = "SL_GENRES")
@Cacheable
public class GenresDictionary {

    @Id
    @Column(name = "SL_GENRES_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer genreId;

    @Column(name = "SL_GENRES_NAME")
    private String genreName;

    public GenresDictionary() {

    }

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
}
