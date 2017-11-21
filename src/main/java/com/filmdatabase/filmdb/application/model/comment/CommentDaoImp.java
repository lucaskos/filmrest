package com.filmdatabase.filmdb.application.model.comment;


import com.filmdatabase.filmdb.application.model.actor.Person;
import com.filmdatabase.filmdb.application.model.film.Film;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luke on 25.09.2017.
 */
@Component
public class CommentDaoImp implements CommentDao{

    private String query;
    private static int MAIN_COMMENT_DEPTH = 0;
    private static int ONE_ENTITY = 0;

    private static final Logger log = LoggerFactory.getLogger(CommentDaoImp.class);

    private List<Comment> mainComments;

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        try {
            return sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            return sessionFactory.openSession();
        }
    }

    public List<Comment> getFilmComments(int filmId) {
        mainComments = new ArrayList<>();
        query = "from Comment where filmId = :id";
        List<Comment> comments = getSession().createQuery(query).setParameter("id", filmId).list();
        if(comments != null) {
            for(Comment c : comments) {
                if(c.getParentId() == null) {
                    mainComments.add(c);
                }
            }
        }
        return mainComments;
    }

    public List<Comment> getActorComments(int actorId) {
        mainComments = new ArrayList<>();
        query = "from Comment";
        List<Comment> comments = sessionFactory.getCurrentSession().createQuery(query).list();
        if(comments != null) {
            for(Comment c : comments) {
                if(c.getParentId() == null) {
                    mainComments.add(c);
                }
            }
        }
        return mainComments;
    }

    public List<Comment> getCommentById() {
        query = "from Comment";
        return getSession().createQuery(query).list();
    }

    public Comment getCommentById(int id) {
        query = "from Comment where id=?l";
        return (Comment) sessionFactory.getCurrentSession().createQuery(query).setParameter("l", id).list().get(ONE_ENTITY);
    }

    @Override
    public void insertComment(Comment comment, Film film, Person person) {

        if(film != null) {
            comment.setFilmId(film.getFilmId());
        } else if(person != null) {
            comment.setActorId(person.getId());
        } else {
            log.info("Neither film nor person available");
        }

        if(comment != null) {
            comment.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            sessionFactory.getCurrentSession().save(comment);
        }
    }

    @Override
    public List<Comment> getAllHierarchyComment(int id) {
        query = "from Comment where id = ?commentId";
        sessionFactory.getCurrentSession().createQuery(query).setParameter("commentId", id).list();
        return null;
    }

    @Override
    public List<Comment> getUserComments(String username) {
        return null;
    }
}
