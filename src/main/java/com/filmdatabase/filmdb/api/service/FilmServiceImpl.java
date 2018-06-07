package com.filmdatabase.filmdb.api.service;

import com.filmdatabase.filmdb.api.service.interfaces.FilmService;
import com.filmdatabase.filmdb.api.service.interfaces.PersonService;
import com.filmdatabase.filmdb.application.DTO.FilmDTO;
import com.filmdatabase.filmdb.application.DTO.utils.FilmWrapperUtils;
import com.filmdatabase.filmdb.application.model.film.Film;
import com.filmdatabase.filmdb.application.model.film.FilmDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;

@Service
public class FilmServiceImpl implements FilmService {

    private final FilmDao filmDao;

    private final FilmWrapperUtils filmWrapper;

    private ModelMapper modelMapper;

    private PersonService personService;

    @Autowired
    public FilmServiceImpl(FilmDao filmDao, ModelMapper modelMapper, PersonService personService) {
        this.filmDao = filmDao;
        this.filmWrapper = new FilmWrapperUtils();
        this.modelMapper = modelMapper;
        this.personService = personService;
    }

    @Override
    public Collection<Film> getAllFilms() {
        return filmDao.findAll();
    }

    @Override
    public FilmDTO getFilmById(int id) {
        return filmWrapper.getFullFilmDetails(modelMapper, filmDao.findOne(id));
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public FilmDTO addFilm(FilmDTO film) {
        Date currentDate = new Date();
        if (film.getId() != null) {
            film.setId(null);
        }
        film.setModificationDate(currentDate);
        film.setCreationDate(currentDate);
        Film newFilm = filmWrapper.createFilmEntity(modelMapper, film);

        newFilm.setFilmRelations(null);

        Film film1 = filmDao.create(newFilm);

        if(isSimpleFilm(film1)) {
            Film simple = filmWrapper.createFilmEntity(modelMapper, film);
            film1.setFilmId(film1.getFilmId());
            film1.setFilmRelations(simple.getFilmRelations());
            newFilm = filmDao.update(film1);
        }


        return modelMapper.map(newFilm, FilmDTO.class);

    }

    private boolean isSimpleFilm(Film film) {
        if(film.getFilmRelations() == null || film.getFilmRelations().isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteFilm(Film film) {
        filmDao.delete(film);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public FilmDTO updateFilm(Film film) {
        Date now = new Date();
        film.setModificationDate(now);
        Film updatedFilm = filmDao.update(film);
        return FilmWrapperUtils.getFullFilmDetails(modelMapper, updatedFilm);
    }

    @Transactional
    @Override
    public FilmDTO getFilmDetails(int id) {
        Film one = filmDao.findOne(id);
        return FilmWrapperUtils.getFullFilmDetails(modelMapper, one);
    }

}
