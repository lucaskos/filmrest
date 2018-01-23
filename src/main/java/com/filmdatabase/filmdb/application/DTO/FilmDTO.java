package com.filmdatabase.filmdb.application.DTO;

import com.filmdatabase.filmdb.application.model.person.Person;

import java.util.ArrayList;
import java.util.List;

public class FilmDTO {

    private int id;
    private int year;
    private String title;
    private String description;
    private List<PersonDTO> peopleList;

    public FilmDTO() {
    }

    public FilmDTO(int id, int year, String title, String description) {
        this.year = year;
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
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
}
