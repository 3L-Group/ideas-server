package com.lll.ideas.service;

import com.lll.ideas.pojo.Article;
import com.lll.ideas.utils.ResponseResult;

import java.util.List;

/**
 * @Author lbh
 * @Date 2021/3/15
 */
public interface ArticleService {

    /**
     * 新增文章
     * @param article
     * @return
     */
    ResponseResult<Void> insertArticle(Article article);

    /**
     * 删除文章
     * @param articleId
     * @return
     */
    ResponseResult<Void> deleteArticle(Integer articleId);

    /**
     * 更新文章
     * @param article
     * @return
     */
    ResponseResult<Void> updateArticle(Article article);

    /**
     * 查询所有文章
     * @return
     */
    ResponseResult<List<Article>> selectAll();

    /**
     * 查询某作者的所有文章
     * @param authorId
     * @return
     */
    ResponseResult<List<Article>> selectByAuthorId(Integer authorId);

    /**
     * 根据标题查询文章
     * @param title
     * @return
     */
    ResponseResult<List<Article>> selectByTitle(String title);

    /**
     * 根据标题模糊查询文章
     * @param title
     * @return
     */
    ResponseResult<List<Article>> selectLikeTitle(String title);

}
