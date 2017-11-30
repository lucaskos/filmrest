package com.filmdatabase.filmdb.application.model.comment;


import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Luke on 25.09.2017.
 */
@Entity
@Table(name = "COMMENT")
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "COMMENT_ID")
    private int commentId;

    @Column(name = "OWNER_ID")
    private Integer user;

    @Column(name = "TITLE")
    private String title;

    @NotBlank
    @Column(name = "TEXT")
    private String text;

    @Column(name = "PARENT_COMMENT_ID")
    private Integer parentId;

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Column(name = "DEPTH")
    private Integer depth;

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    @Column(name = "FILM_ID")
    private Integer filmId;

    @Column(name = "PERSON_ID")
    private Integer actorId;

    @Column(name = "CREATED_DATE")
    private Timestamp createdDate;

    public Comment() {}

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", text='" + text + '\'' +
                ", filmId=" + filmId +
                ", actorId=" + actorId +
                '}';
    }
}
