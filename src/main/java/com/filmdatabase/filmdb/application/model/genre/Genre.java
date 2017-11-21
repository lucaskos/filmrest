package com.filmdatabase.filmdb.application.model.genre;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Created by Luke on 21.10.2017.
 */
@Table(name = "SL_GENRES")
public class Genre {

    @Column(name = "SL_GENRES_ID")
    private int genreId;

    @Column(name = "SL_GENRES_ID")
    private String genreName;

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
}
