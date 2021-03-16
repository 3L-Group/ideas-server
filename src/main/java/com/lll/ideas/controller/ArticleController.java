package com.lll.ideas.controller;

import com.lll.ideas.pojo.Article;
import com.lll.ideas.service.ArticleService;
import com.lll.ideas.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @Author lbh
 * @Date 2021/3/15
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/insertArticle")
    public ResponseResult<Void> insertArticle(Article article){
        return articleService.insertArticle(article);
    }

    @PostMapping("/deleteArticle")
    public ResponseResult<Void> deleteArticle(Integer articleId){
        return articleService.deleteArticle(articleId);
    }

    @PostMapping("/updateArticle")
    public ResponseResult<Void> updateArticle(Article article){
        return articleService.updateArticle(article);
    }

    @GetMapping("/selectAll")
    public ResponseResult<List<Article>> selectAll(){
        return articleService.selectAll();
    }

    @GetMapping("/selectByAuthorId")
    public ResponseResult<List<Article>> selectByAuthorId(Integer authorId){
        return articleService.selectByAuthorId(authorId);
    }

    @GetMapping("/selectByTitle")
    public ResponseResult<List<Article>> selectByTitle(String title){
        return articleService.selectByTitle(title);
    }

    @GetMapping("/selectLikeTitle")
    public ResponseResult<List<Article>> selectLikeTitle(String title){
        return articleService.selectLikeTitle(title);
    }














}
