package com.filmdatabase.filmdb.application.model.comments;

import com.filmdatabase.filmdb.application.model.user.dao.User;

import java.util.Date;

public interface Comment {

    int getCommentId();

    void setCommentId(int commentId);

    Date getCreatedDate();

    void setCreatedDate(Date createdDate);

    Integer getDepth();

    void setDepth(Integer depth);

    Integer getParentCommentId();

    void setParentCommentId(Integer parentCommentId);

    String getText();

    void setText(String text);

    String getTitle();

    void setTitle(String title);

    User getUser();

    void setUser(User user);
}
