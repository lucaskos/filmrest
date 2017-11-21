package com.filmdatabase.filmdb.application.model;

import com.filmdatabase.filmdb.application.model.actor.Person;
import com.filmdatabase.filmdb.application.model.cache.dictionaries.PersonRole;

import javax.persistence.*;

/**
 * Created by Luke on 25.10.2017.
 */
@Entity
@Table(name = "PERSON_RELATION")
public class PersonRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_RELATION")
    private int personRelationId;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = PersonRole.class)
    @JoinColumn(name = "ID_PERSON_ROLE")
    private PersonRole personRoleId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PERSON")
    private Person personObject;

    public int getPersonRelationId() {
        return personRelationId;
    }

    public void setPersonRelationId(int personRelationId) {
        this.personRelationId = personRelationId;
    }

    public PersonRole getPersonRoleId() {
        return personRoleId;
    }

    public void setPersonRoleId(PersonRole personRoleId) {
        this.personRoleId = personRoleId;
    }

    public Person getPersonId() {
        return personObject;
    }

    public void setPersonId(Person person) {
        this.personObject = person;
    }
}
