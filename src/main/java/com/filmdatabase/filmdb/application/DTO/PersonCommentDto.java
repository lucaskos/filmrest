package com.filmdatabase.filmdb.application.DTO;

import com.filmdatabase.filmdb.application.model.person.Person;
import com.filmdatabase.filmdb.application.model.user.dao.User;

import java.util.Date;

public class PersonCommentDto {
    private int commentId;
    private Date createdDate;
    private Integer depth;
    private Integer parentCommentId;
    private String text;
    private String title;
    private UserDto user;
    private Person person;

    public PersonCommentDto() {
    }

    public PersonCommentDto(int commentId, Date createdDate, Integer depth, Integer parentCommentId, String text, String title, UserDto user, Person person) {
        this.commentId = commentId;
        this.createdDate = createdDate;
        this.depth = depth;
        this.parentCommentId = parentCommentId;
        this.text = text;
        this.title = title;
        this.user = user;
        this.person = person;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public Integer getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
