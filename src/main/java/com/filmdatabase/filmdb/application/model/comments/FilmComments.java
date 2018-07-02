package com.filmdatabase.filmdb.application.model.comments;

import com.filmdatabase.filmdb.application.model.film.Film;

import javax.persistence.*;
import java.util.Date;

@Entity()
@Table(name = "FILM_COMMENTS")
public class FilmComments {
    private int commentId;
    private Film getFilm;
    private Date createdDate;
    private int depth;
    private Integer parentCommentId;
    private String text;
    private String title;
    private Integer userIdl;

    @Id
    @Column(name = "COMMENT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public Film getFilm() {
        return getFilm;
    }

    public void setFilm(Film filmId) {
        this.getFilm = filmId;
    }

    @Column(name = "CREATED_DATE")
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Column(name = "DEPTH")
    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    @Column(name = "PARENT_COMMENT_ID")
    public Integer getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    @Column(name = "TEXT", nullable = false, columnDefinition = "TEXT")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column(name = "TITLE", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "OWNER_ID")
    public Integer getUserIdl() {
        return userIdl;
    }

    public void setUserIdl(Integer userIdl) {
        this.userIdl = userIdl;
    }
}
