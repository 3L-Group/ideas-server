package com.lll.ideas.dao;

import com.lll.ideas.pojo.Comment;

import java.util.List;

/**
 * @Author lbh
 * @Date 2021/3/16
 */
public interface CommentMapper {

    /**
     * 添加评论
     * @param comment
     * @return
     */
    int insertComment(Comment comment);

    /**
     * 删除评论
     * @param commentId
     * @return
     */
    int deleteComment(Integer commentId);


    /**
     * 查询全部评论
     * @return
     */
    List<Comment> selectAll();

    /**
     * 用户点赞评论
     * @param userId
     * @param commentId
     * @return
     */
//    int agree(Integer userId, Integer commentId);


}
