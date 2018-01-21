package com.filmdatabase.filmdb.application.model.person;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.filmdatabase.filmdb.application.model.FilmRelation;
import com.filmdatabase.filmdb.application.model.PersonRelation;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "PERSON")
public class Person implements Serializable{

    private static final long serialVersionUID = 5650070241555490348L;
    private int id;
    private String firstName;
    private String lastName;
    private Date bornDate;
    private Date diedDate;
    /**
     * biography of a person
     */
    private String bio;
    private Set<PersonRelation> personRelations;
    private Set<FilmRelation> filmRelations;

//    @JsonIgnore
    //@OneToMany(mappedBy = "person", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    public Set<PersonRelation> getPersonRelations() {
//        return personRelations;
//    }

    public void setPersonRelations(Set<PersonRelation> personRelations) {
        this.personRelations = personRelations;
    }
    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY, cascade = CascadeType
            .ALL)
    public Set<FilmRelation> getFilmRelations() {
        return filmRelations;
    }

    public void setFilmRelations(Set<FilmRelation> filmRelations) {
        this.filmRelations = filmRelations;
    }

//	@LazyCollection(LazyCollectionOption.FALSE)
//	@OneToMany(mappedBy = "person",cascade = CascadeType.ALL)
    //private Set<Cast> filmography = new HashSet<Cast>();

    public Person() {

    }

    public Person(String firstName) {
        this.firstName = firstName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PERSON_ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotBlank
    @Column(name = "FIRST_NAME")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

//	public Set<Cast> getActorFilms() {
//		return this.filmography;
//	}
//
//	public void setActorFilms(Set<Cast> actorFilms) {
//		this.filmography = actorFilms;
//	}
//
//	public void addActorsFilms(Cast actorFilms) {
//		this.filmography.add(actorFilms);
//	}


    @Column(name = "LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "BORN_DATE")
    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    @Column(name = "DIED_DATE")
    public Date getDiedDate() {
        return diedDate;
    }

    public void setDiedDate(Date diedDate) {
        this.diedDate = diedDate;
    }

    @Column(name = "BIOGRAPHY")
    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", firstName=" + firstName + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
//		result = prime * result + ((filmography == null) ? 0 : filmography.hashCode());
        result = prime * result + id;
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
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
        Person other = (Person) obj;
//		if (filmography == null) {
//			if (other.filmography != null)
//				return false;
//		} else if (!filmography.equals(other.filmography))
//			return false;
        if (id != other.id)
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        return true;
    }


}
