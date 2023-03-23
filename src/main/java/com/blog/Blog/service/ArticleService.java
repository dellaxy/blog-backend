package com.blog.Blog.service;

import com.blog.Blog.dto.ArticlePostDTO;
import com.blog.Blog.dto.ArticleRequestDTO;
import com.blog.Blog.dto.ArticleWithBodyDTO;
import com.blog.Blog.model.Article;
import com.blog.Blog.model.ArticleBody;
import com.blog.Blog.model.Category;
import com.blog.Blog.repository.ArticleBodyRepo;
import com.blog.Blog.repository.ArticleRepo;
import com.blog.Blog.repository.CategoryRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepo articleRepository;
    private final CategoryRepo categoryRepository;
    private final ArticleBodyRepo articleBodyRepository;

    @Autowired
    public ArticleService(ArticleRepo articleRepository, CategoryRepo categoryRepository, ArticleBodyRepo articleBodyRepository) {
        this.articleRepository = articleRepository;
        this.articleBodyRepository = articleBodyRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ArticleRequestDTO> findAllArticles() {
        Sort sort = Sort.by(Sort.Direction.DESC, "date");
        List<Article> articles = articleRepository.findAll(sort);
        return articles.stream().map(article -> new ArticleRequestDTO(article)).collect(Collectors.toList());
    }
    public ArticleWithBodyDTO findArticleById(Long id){
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Article not found with id: " + id));
        return new ArticleWithBodyDTO(article);
    }

    public List<ArticleRequestDTO> findArticlesByCategory(Long id) {
        Sort sort = Sort.by(Sort.Direction.DESC, "date");
        List<Article> articles = articleRepository.getArticlesByCategoryId(id, sort);
        return articles.stream().map(article -> new ArticleRequestDTO(article)).collect(Collectors.toList());
    }

    @Transactional
    public boolean addArticle(ArticlePostDTO articlePost) {
        Category category = categoryRepository.findById(articlePost.getCategory_id())
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + articlePost.getCategory_id()));
        articleBodyRepository.save(articlePost.getBody());
        Article article = new Article(null, articlePost.getDate(), articlePost.getTitle() ,articlePost.getLead(), articlePost.getImg(), articlePost.getBody(), category);
        articleRepository.save(article);
        return true;
    }

    @Transactional
    public boolean updateArticle(ArticlePostDTO articlePost) {
        Article article = articleRepository.findById(articlePost.getId())
                .orElseThrow(() -> new EntityNotFoundException("Article not found with id: " + articlePost.getId()));
        Category category = categoryRepository.findById(articlePost.getCategory_id())
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + articlePost.getCategory_id()));
        ArticleBody articleBody = article.getBody();
        article.setDate(articlePost.getDate());
        article.setTitle(articlePost.getTitle());
        article.setLead(articlePost.getLead());
        article.setImg(articlePost.getImg());
        article.setCategory(category);
        articleBody.setAll(articlePost.getBody().getContent(), articlePost.getBody().getLast_update());
        articleBodyRepository.save(articleBody);
        articleRepository.save(article);
        return true;
    }


    @Transactional
    public void deleteArticle(Long id) {
        String filename = articleRepository.getArticleById(id).getImg();
        if (filename == null) return;
        Path imagePath = Paths.get("D:/Files/Projekty/Blog/discord-diary/src/assets/images/" + filename);
        try {
            Files.delete(imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        articleRepository.deleteById(id);
    }



}
