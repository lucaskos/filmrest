package com.filmdatabase.filmdb.application.model;

import com.filmdatabase.filmdb.application.model.person.Person;
import com.filmdatabase.filmdb.application.model.cache.dictionaries.PersonRole;

import javax.persistence.*;

/**
 * Created by Luke on 25.10.2017.
 */
@Entity
@Table(name = "PERSON_RELATION")
public class PersonRelation {


    private int personRelationId;
    private PersonRole personRole;
    private Person person;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_RELATION")
    public int getPersonRelationId() {
        return personRelationId;
    }

    public void setPersonRelationId(int personRelationId) {
        this.personRelationId = personRelationId;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PERSON_ROLE_ID")
    public PersonRole getPersonRole() {
        return personRole;
    }

    public void setPersonRole(PersonRole personRole) {
        this.personRole = personRole;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PERSON_ID")
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
