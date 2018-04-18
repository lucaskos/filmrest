package com.filmdatabase.filmdb.application.model.cache.dictionaries;

import com.filmdatabase.filmdb.application.model.FilmRelation;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * Created by Luke on 25.10.2017.
 */
@Entity
@Table(name = "SL_PERSON_ROLE")
public class PersonRole extends CacheAbstract{

    private Integer id;
    private String type;
    private String key;


    public PersonRole() {

    }

    public PersonRole(Integer id, String type, String key) {
        this.id = id;
        this.type = type;
        this.key = key;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SL_PERSON_ROLE_ID")
    @NotNull
    public Integer getId() {
        return id;
    }

    public void setId(Integer personRoleId) {
        this.id = personRoleId;
    }

    @Column(name = "SL_PERSON_ROLE_TYPE")
    public String getType() {
        return type;
    }

    public void setType(String personRoleType) {
        this.type = personRoleType;
    }

    @Column(name = "SL_PERSON_ROLE_KEY")
    @NotNull
    public String getKey() {
        return key;
    }

    public void setKey(String personRoleKey) {
        this.key = personRoleKey;
    }

    private Collection<FilmRelation> filmRelation;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "personRoleDictionary")
    public Collection<FilmRelation> getFilmRelation() {
        return filmRelation;
    }

    public void setFilmRelation(Collection<FilmRelation> filmRelation) {
        this.filmRelation = filmRelation;
    }
}
