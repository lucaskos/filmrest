package com.filmdatabase.filmdb.application.model;

import java.io.Serializable;
import java.util.List;

public interface GenericDao <T> {

    /** Retrieve all object of T type */
    List<T> findAll();

    /** Persist the newInstance object into database */
    T create(T newInstance);

    /** Retrieve an object that was previously persisted to the database using
     *   the indicated id as primary key
     */
    T findOne(Serializable id);

    /** Save changes made to a persistent object.  */
    T update(T transientObject);

    /** Remove an object from persistent storage in the database */
    void delete(T persistentObject);

    /** Clear session */
    void clear();

    /** Flush session */
    void flush();
}
