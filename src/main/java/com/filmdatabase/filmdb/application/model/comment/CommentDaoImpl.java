package com.filmdatabase.filmdb.application.model.comment;

import com.filmdatabase.filmdb.application.model.GenericDaoHibernateImpl;
import org.springframework.stereotype.Component;

@Component("commentDao")
public class CommentDaoImpl extends GenericDaoHibernateImpl<Comment> implements CommentDao {
    public CommentDaoImpl() {
        super(Comment.class);
    }
}
