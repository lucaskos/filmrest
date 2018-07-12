package com.filmdatabase.filmdb.api.controllers;

import com.filmdatabase.filmdb.api.service.PersonServiceImpl;
import com.filmdatabase.filmdb.api.service.interfaces.PersonService;
import com.filmdatabase.filmdb.application.DTO.PersonDTO;
import com.filmdatabase.filmdb.application.model.person.Person;
import jersey.repackaged.com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
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
        return all;
    }

    @GetMapping("/{id}")
    public @ResponseBody PersonDTO getPersonById(@PathVariable @NotNull int id) {

        //todo exception handler in component if no actor found
        PersonDTO person = personService.getPerson(id);
        return person;
    }

    @PostMapping
    public void create(@RequestBody PersonDTO person) {
        Preconditions.checkNotNull(person);
        personService.create(person);
    }

    @PutMapping
    public void update(@PathVariable("id") Long id, @RequestBody PersonDTO person) {
        Preconditions.checkNotNull(person);
        //personService.update(person);
    }

    @DeleteMapping
    public void delete(@PathVariable("id") Long id) {
        //todo
    }
}
