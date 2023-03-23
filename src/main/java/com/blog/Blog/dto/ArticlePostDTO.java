package com.blog.Blog.dto;

import com.blog.Blog.model.Article;
import com.blog.Blog.model.ArticleBody;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

@Data
public class ArticlePostDTO {
    private Long id;
    private Date date;
    private String title, lead, image;
    private Long category;

    private ArticleBody body;

    public ArticlePostDTO(){

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

    public ArticleBody getBody() {
        return body;
    }

    public void setBody(ArticleBody body) {
        this.body = body;
    }

    public String getImg() {
        return image;
    }

    public void setImg(String img) {
        this.image = image;
    }

    public Long getCategory_id() {
        return category;
    }

    public void setCategory_id(Long category_id) {
        this.category = category_id;
    }

    @Override
    public String toString() {
        return "ArticlePostDTO{" +
                "id=" + id +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", thumbnail='" + lead + '\'' +
                ", body='" + body + '\'' +
                ", img='" + image + '\'' +
                ", category_id=" + category +
                '}';
    }
}
