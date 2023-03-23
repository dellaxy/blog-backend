package com.blog.Blog.controller;
import com.blog.Blog.dto.ArticlePostDTO;
import com.blog.Blog.dto.ArticleRequestDTO;
import com.blog.Blog.dto.ArticleWithBodyDTO;
import com.blog.Blog.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService service;

    public ArticleController(ArticleService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ArticleRequestDTO>> getAllArticles(){
        List<ArticleRequestDTO> articles = service.findAllArticles();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleWithBodyDTO> getArticleById(@PathVariable("id") Long id){
        ArticleWithBodyDTO article = service.findArticleById(id);
        if(article == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ArticleRequestDTO>> getArticlesByCategory(@PathVariable("categoryId") Long categoryId){
        List<ArticleRequestDTO> articles = service.findArticlesByCategory(categoryId);
        return new ResponseEntity<>(articles, HttpStatus.OK);

    }
    @PostMapping("/add")
    public ResponseEntity<ArticlePostDTO> addArticle(@RequestBody ArticlePostDTO article){
        if(service.addArticle(article))
            return new ResponseEntity<>(article ,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
    @PutMapping("/update")
    public ResponseEntity<ArticlePostDTO> updateArticle(@RequestBody ArticlePostDTO article){
        if(service.updateArticle(article))
            return new ResponseEntity<>(article,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteId(@PathVariable("id") Long id){
        service.deleteArticle(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile imageFile) {
        try {
            // Save the image to the server's file system
            String filename = imageFile.getOriginalFilename();
            Path imagePath = Paths.get("D:/Files/Projekty/Blog/discord-diary/src/assets/images/" + filename);
            Files.copy(imageFile.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

