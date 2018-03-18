package com.filmdatabase.filmdb.api.controllers;

import com.filmdatabase.filmdb.api.service.PersonService;
import com.filmdatabase.filmdb.application.model.FilmRelation;
import com.filmdatabase.filmdb.application.model.person.Person;
import jersey.repackaged.com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public @ResponseBody
    List<Person> getAllPeople() {
        List<Person> all = personService.findAll();
        Set<FilmRelation> filmRelations = personService.findAll().get(0).getFilmRelations();
        return all;
    }

    @GetMapping("/{id}")
    public @ResponseBody Person getPersonById(@PathVariable @NotNull int id) {
        //todo exception handler in component if no actor found
        return personService.getPerson(id);
    }

    @PostMapping
    public void create(@RequestBody Person person) {
        Preconditions.checkNotNull(person);
        personService.create(person);
    }

    @PutMapping
    public void update(@PathVariable("id") Long id, @RequestBody Person person) {
        Preconditions.checkNotNull(person);
        personService.update(person);
    }

    @DeleteMapping
    public void delete(@PathVariable("id") Long id) {
        //todo
    }
}
