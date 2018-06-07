package com.filmdatabase.filmdb.application.DTO;

import com.filmdatabase.filmdb.application.model.film.Film;

import java.util.*;

public class FilmDTO {

    private Integer id;
    private Integer year;
    private String title;
    private String description;
    private List<PersonDTO> peopleList = new ArrayList<>();
    private Map<RoleDto, PersonDTO> peopleRoleMap = new HashMap<>();
    private Date creationDate;
    private Date modificationDate;
    private Film film;

    public FilmDTO() {
    }

    public FilmDTO(Integer id, Integer year, String title, String description) {
        this.year = year;
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<PersonDTO> getPeopleList() {
        return peopleList;
    }

    public void setPeopleList(List<PersonDTO> peopleList) {
        this.peopleList = peopleList;
    }

    public void addPerson(PersonDTO person) {
        if(peopleList == null) {
            peopleList = new ArrayList<>();
        }
        peopleList.add(person);
    }

    public Map<RoleDto, PersonDTO> getPeopleRoleMap() {
        return peopleRoleMap;
    }

    public void setPeopleRoleMap(Map<RoleDto, PersonDTO> peopleRoleMap) {
        this.peopleRoleMap = peopleRoleMap;
    }

    public Film getFilm() {
        Film film = new Film(this.title, this.year, this.description );
        return film;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    @Override
    public String toString() {
        return "FilmDTO{" +
                "id=" + id +
                ", year=" + year +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", peopleList=" + peopleList +
                ", peopleRoleMap=" + peopleRoleMap +
                ", film=" + film +
                '}';
    }
}
