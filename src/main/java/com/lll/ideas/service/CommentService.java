package com.lll.ideas.service;

import com.lll.ideas.pojo.Comment;
import com.lll.ideas.utils.ResponseResult;

import java.util.List;

/**
 * @Author lbh
 * @Date 2021/3/16
 */
public interface CommentService {
    /**
     * 添加评论
     * @param comment
     * @return
     */
    ResponseResult<Void> insertComment(Comment comment);

    /**
     * 删除评论
     * @param commentId
     * @return
     */
    ResponseResult<Void> deleteComment(Integer commentId);


    /**
     * 查询全部评论
     * @return
     */
    ResponseResult<List<Comment>> selectAll();
}
