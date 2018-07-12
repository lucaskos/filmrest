package com.filmdatabase.filmdb.application.DTO;

public class PersonRelationDto extends RelationDto {
    private FilmDTO filmDTO;

    public FilmDTO getFilmDTO() {
        return filmDTO;
    }

    public void setFilmDTO(FilmDTO filmDTO) {
        this.filmDTO = filmDTO;
    }
}
