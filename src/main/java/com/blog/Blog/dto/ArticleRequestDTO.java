package com.blog.Blog.dto;

import com.blog.Blog.model.Article;
import com.blog.Blog.model.ArticleBody;
import com.blog.Blog.model.Category;
import lombok.Data;

import java.util.Date;

@Data
public class ArticleRequestDTO {
    private Long id;
    private Date date;
    private String title,leadParagraph,image;
    private Category category;

    public ArticleRequestDTO(Article article) {
        this.id = article.getId();
        this.date = article.getDate();
        this.title = article.getTitle();
        this.leadParagraph = article.getLead();
        this.image = article.getImg();
        this.category = article.getCategory();
    }

}
