package com.lll.ideas.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author lbh
 * @Date 2021/3/15
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    /**
     * 文章id
     */
    private Integer articleId;
    /**
     * 作者id
     */
    private Integer authorId;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章内容
     */
    private String content;
    /**
     * 文章点赞数
     *
     */
    private Integer star;
    /**
     * 浏览量
     */
    private Integer view;
    /**
     * 更新时间
     */
    private Date updateTime;
}
