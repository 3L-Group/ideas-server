package com.lll.ideas.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author lbh
 * @Date 2021/3/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    /**
     * 评论id
     */
    private Integer commentId;
    /**
     * 文章id
     */
    private Integer articleId;
    /**
     * 评论者id
     */
    private Integer reviewerId;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论时间
     */
    private Date createTime;
    /**
     * 评论点赞量
     */
//    private Integer star;
}
