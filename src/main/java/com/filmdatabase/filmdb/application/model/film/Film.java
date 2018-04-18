package com.filmdatabase.filmdb.application.model.film;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.filmdatabase.filmdb.application.model.FilmRelation;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@Entity
@Table(name = "FILM")
public class Film implements Serializable {

    private static final long serialVersionUID = -8915838577868975194L;
    private Integer filmId;
    private String title;
    private Integer year;
    private String description;
    private List<FilmRelation> filmRelations = new ArrayList<>();

    public Film() {

    }

    @JsonIgnore
    @OneToMany(targetEntity = FilmRelation.class, mappedBy = "film", fetch = FetchType.LAZY, cascade = CascadeType
            .ALL)
    public List<FilmRelation> getFilmRelations() {
        return filmRelations;
    }

    public void setFilmRelations(List<FilmRelation> filmRelations) {
        this.filmRelations = filmRelations;
    }

    public void addRelation(FilmRelation relation) {
        if(filmRelations == null) {
            filmRelations = new ArrayList<>();
        }
        filmRelations.add(relation);
    }

    public Film(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public Film(Integer id, String title, int year, String description) {
        this.filmId = id;
        this.title = title;
        this.year = year;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FILM_ID")
    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
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
