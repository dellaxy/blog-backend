package com.blog.Blog.model;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "article")
@DynamicUpdate
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;
    private Date date;
    private String title, lead, img;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "body")
    private ArticleBody body;
    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

    public Article(Long id, Date date, String title, String lead, String img, ArticleBody body, Category category) {
        this.date = date;
        this.title = title;
        this.lead = lead;
        this.img = img;
        this.body = body;
        this.category = category;
    }

    public Article() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLead() {
        return lead;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public ArticleBody getBody() {
        return body;
    }

    public void setBody(ArticleBody body) {
        this.body = body;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", lead='" + lead + '\'' +
                ", img='" + img + '\'' +
                ", body=" + body +
                ", category=" + category +
                '}';
    }
}
