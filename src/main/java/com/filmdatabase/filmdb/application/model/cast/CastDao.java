package com.filmdatabase.filmdb.application.model.cast;

import java.util.List;

import com.filmdatabase.filmdb.application.model.person.Person;
import com.filmdatabase.filmdb.application.model.film.Film;

public interface CastDao {

	void addActorToFilm(Film film, Person person, String role);
	
	List<Cast> getCastOfFilm(Film film);
	
	List<Cast> getFilmographyOfActor(Person person);
	
}
