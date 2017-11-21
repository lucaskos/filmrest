package com.filmdatabase.filmdb.application.model.user;

import com.filmdatabase.filmdb.application.model.comment.Comment;

import java.util.Collection;

/**
 * User bean used to store information about user and hide some sensitive information like password .
 * The information can be accessible for other users and admins.
 * Created by Luke on 30.10.2017.
 */
public class UserBean {

    private int id;
    private String username;
    private String email;
    private Collection<Comment> commmentList;

    public UserBean() {

    }

    public UserBean(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<Comment> getCommmentList() {
        return commmentList;
    }

    public void setCommmentList(Collection<Comment> commmentList) {
        this.commmentList = commmentList;
    }
}
