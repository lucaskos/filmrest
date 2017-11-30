package com.filmdatabase.filmdb.application.procedures;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.SQLException;
import java.util.List;

@Component
public class ProcedureDaoImpl implements ProcedureDao {

    @PersistenceContext
    public EntityManager entityManager;

    @Override
    public int insertRating(int userId, Integer filmId, Integer personId, int rating) {
        int result = 0;
        try {

        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("INSERT_RATING");

            storedProcedure.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN);
            //storedProcedure.registerStoredProcedureParameter(5, Integer.class, ParameterMode.OUT);
            storedProcedure.setParameter(1, String.valueOf(userId));
            storedProcedure.setParameter(2, filmId);
            storedProcedure.setParameter(3, 0);
            storedProcedure.setParameter(4, rating);

            boolean execute = storedProcedure.execute();

            //result = (int) storedProcedure.getOutputParameterValue(5);
        } catch (Exception e) {
            String message = e.getMessage();
            e.printStackTrace();
            return 0;
        }
        return result;
    }
}
