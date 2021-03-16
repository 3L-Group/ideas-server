package com.lll.ideas.dao;

import com.lll.ideas.pojo.Article;

import java.util.List;

/**
 * @Author lbh
 * @Date 2021/3/15
 */
public interface ArticleMapper {

    /**
     * 新增文章
     * @param article
     * @return
     */
    int insertArticle(Article article);

    /**
     * 删除文章
     * @param articleId
     * @return
     */
    int deleteArticle(Integer articleId);

    /**
     * 更新文章
     * @param article
     * @return
     */
    int updateArticle(Article article);

    /**
     * 查询所有文章
     * @return
     */
    List<Article> selectAll();

    /**
     * 查询某作者的所有文章
     * @param authorId
     * @return
     */
    List<Article> selectByAuthorId(Integer authorId);

    /**
     * 根据标题查询文章
     * @param title
     * @return
     */
    List<Article> selectByTitle(String title);

    /**
     * 根据标题模糊查询文章
     * @param title
     * @return
     */
    List<Article> selectLikeTitle(String title);

}
