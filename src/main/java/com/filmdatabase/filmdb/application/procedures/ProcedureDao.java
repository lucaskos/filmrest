package com.filmdatabase.filmdb.application.procedures;

public interface ProcedureDao {

    int insertRating(int userId, Integer filmId, Integer personId, int rating);

}
