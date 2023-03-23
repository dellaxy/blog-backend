package com.blog.Blog.repository;

import com.blog.Blog.model.Article;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepo extends JpaRepository<Article, Long> {
    Article getArticleById(Long id);

    List<Article> getArticlesByCategoryId(Long id, Sort sort);
}

