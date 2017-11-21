package com.filmdatabase.filmdb;

import com.filmdatabase.filmdb.configuration.JpaConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Import;

@Import(JpaConfiguration.class)
@SpringBootApplication
public class FilmdbApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmdbApplication.class, args);
	}
}
