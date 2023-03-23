package com.blog.Blog.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "article_body")
public class ArticleBody implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;
    private Date last_update;
    private String content;


    public ArticleBody(Long id, Date last_update, String content) {
        this.id = id;
        this.last_update = last_update;
        this.content = content;
    }

    public ArticleBody() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAll(String content, Date last_update){
        this.setContent(content);
        this.setLast_update(last_update);
    }
}
