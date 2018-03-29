package com.filmdatabase.filmdb.application.DTO;

import com.filmdatabase.filmdb.application.model.film.Film;
import com.filmdatabase.filmdb.application.model.person.Person;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class
PersonDTO {

    private int id;
    private String firstName;
    private String lastName;
    private Date bornDate;
    private Date diedDate;
    private Map<Integer, Film> filmList = new HashMap();
    private Person person;

    public PersonDTO() {

    }

    public PersonDTO(Person p) {
        this.person = p;
        this.id = p.getId();
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        this.bornDate = p.getBornDate();
        this.diedDate = p.getDiedDate();
    }

    public PersonDTO(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public PersonDTO(int id, String firstName, String lastName, Date bornDate, Date diedDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bornDate = bornDate;
        this.diedDate = diedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public Date getDiedDate() {
        return diedDate;
    }

    public void setDiedDate(Date diedDate) {
        this.diedDate = diedDate;
    }

    public Map<Integer, Film> getFilmList() {
        return filmList;
    }

    public void setFilmList(Map<Integer, Film> filmList) {
        this.filmList = filmList;
    }

//    public Person getPerson() { return this.person; }
}
