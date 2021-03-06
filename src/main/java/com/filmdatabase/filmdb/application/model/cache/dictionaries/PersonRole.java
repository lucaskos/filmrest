package com.filmdatabase.filmdb.application.model.cache.dictionaries;

import com.filmdatabase.filmdb.application.commons.QualifierConstants;

import javax.persistence.*;

/**
 * Created by Luke on 25.10.2017.
 */
@Entity
@Table(name = "SL_PERSON_ROLE")
public class PersonRole {

    private int personRoleId;
    private String personRoleType;
    private String personRoleKey;

    public PersonRole() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SL_PERSON_ROLE_ID")
    public int getPersonRoleId() {
        return personRoleId;
    }

    public void setPersonRoleId(int personRoleId) {
        this.personRoleId = personRoleId;
    }

    @Column(name = "SL_PERSON_ROLE_TYPE")
    public String getPersonRoleType() {
        return personRoleType;
    }

    public void setPersonRoleType(String personRoleType) {
        this.personRoleType = personRoleType;
    }

    @Column(name = "SL_PERSON_ROLE_KEY")
    public String getPersonRoleKey() {
        return personRoleKey;
    }

    public void setPersonRoleKey(String personRoleKey) {
        this.personRoleKey = personRoleKey;
    }
}
