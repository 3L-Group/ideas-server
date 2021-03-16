package com.lll.ideas.service.impl;

import com.lll.ideas.dao.ArticleMapper;
import com.lll.ideas.enums.ResponseEnum;
import com.lll.ideas.pojo.Article;
import com.lll.ideas.service.ArticleService;
import com.lll.ideas.utils.ResponseResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author lbh
 * @Date 2021/3/15
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Override
    public ResponseResult<Void> insertArticle(Article article) {

        //标题不能为空
        if(article.getTitle() == null){
            return ResponseResult.fail(ResponseEnum.TITLE_IS_NULL.getCode(), ResponseEnum.TITLE_IS_NULL.getMsg());
        }

        //内容不能为空
        if(article.getContent() == null){
            return ResponseResult.fail(ResponseEnum.CONTENT_IS_NULL.getCode(), ResponseEnum.CONTENT_IS_NULL.getMsg());
        }

        //设置创建时间
        Date date = new Date();
        article.setUpdateTime(date);

        System.out.println(date);
        articleMapper.insertArticle(article);
        return ResponseResult.ok();

    }


    @Override
    public ResponseResult<Void> deleteArticle(Integer articleId) {
        articleMapper.deleteArticle(articleId);
        return ResponseResult.ok();

    }


    @Override
    public ResponseResult<Void> updateArticle(Article article) {
        if(article.getArticleId() == null){
            return ResponseResult.fail(ResponseEnum.ARTICLE_NOT_FOUND.getCode(), ResponseEnum.ARTICLE_NOT_FOUND.getMsg());
        }

        article.setArticleId(article.getArticleId());
        article.setAuthorId(article.getAuthorId());
        article.setContent(article.getContent());
        article.setTitle(article.getTitle());
        article.setUpdateTime(new Date());

        articleMapper.updateArticle(article);

        return ResponseResult.ok();

    }


    @Override
    public ResponseResult<List<Article>> selectAll() {
        List<Article> articleList = articleMapper.selectAll();
        return ResponseResult.ok(articleList);
    }


    @Override
    public ResponseResult<List<Article>> selectByAuthorId(Integer authorId) {
        List<Article> articleList = articleMapper.selectByAuthorId(authorId);
        if(articleList != null){
            return ResponseResult.ok(articleList);
        }

        return ResponseResult.fail();
    }


    @Override
    public ResponseResult<List<Article>> selectByTitle(String title) {
        List<Article> articleList = articleMapper.selectByTitle(title);

        return ResponseResult.ok(articleList);
    }


    @Override
    public ResponseResult<List<Article>> selectLikeTitle(String title) {
        List<Article> articleList = articleMapper.selectLikeTitle(title);
        if(articleList != null){
            return ResponseResult.ok(articleList);
        }

        return ResponseResult.fail();
    }
}
