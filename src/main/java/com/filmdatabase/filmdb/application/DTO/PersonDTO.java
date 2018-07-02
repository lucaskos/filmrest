package com.filmdatabase.filmdb.application.DTO;

import com.filmdatabase.filmdb.application.model.comments.PersonComments;
import com.filmdatabase.filmdb.application.model.person.Person;

import java.util.*;

public class PersonDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private Date bornDate;
    private Date diedDate;
    private List<FilmDTO> filmList = new ArrayList<>(0);
    private Person person;
    private String roleType;
    private String biography;
    private RoleDto roleDto;
    private String role;
    private Date creationDate;
    private Date modificationDate;
    //todo sprawdzic czy mapa nie jest lepsza do jako drzewo commentarzy
    private Set<PersonComments> personComments = new LinkedHashSet<>();

    public RoleDto getRoleDto() {
        return roleDto;
    }

    public void setRoleDto(RoleDto roleDto) {
        this.roleDto = roleDto;
    }

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

    public PersonDTO(Integer id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public PersonDTO(Integer id, String firstName, String lastName, Date bornDate, Date diedDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bornDate = bornDate;
        this.diedDate = diedDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public List<FilmDTO> getFilmList() {
        return filmList;
    }

    public void setFilmList(List<FilmDTO> filmList) {
        this.filmList = filmList;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Person getPerson() { return this.person != null ? person : createPersonEntity(); }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public Set<PersonComments> getPersonComments() {
        return personComments;
    }

    public void setPersonComments(Set<PersonComments> personComments) {
        this.personComments = personComments;
    }

    public void addPersonComments(PersonComments comment) {
        if (this.personComments == null) {
            personComments = new LinkedHashSet<>();
        }
        this.personComments.add(comment);

    }

    private Person createPersonEntity() {
        person = new Person();
        person.setFirstName(getFirstName());
        person.setLastName(getLastName());
        person.setId(getId());
        person.setBio(getBiography());
        return person;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", bornDate=" + bornDate +
                ", diedDate=" + diedDate +
                ", filmList=" + filmList +
                ", person=" + person +
                ", roleType='" + roleType + '\'' +
                ", biography='" + biography + '\'' +
                '}';
    }
}
