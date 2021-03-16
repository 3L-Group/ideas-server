package com.lll.ideas.service.impl;

import com.lll.ideas.dao.CommentMapper;
import com.lll.ideas.enums.ResponseEnum;
import com.lll.ideas.pojo.Comment;
import com.lll.ideas.service.CommentService;
import com.lll.ideas.utils.ResponseResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author lbh
 * @Date 2021/3/16
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;


    @Override
    public ResponseResult<Void> insertComment(Comment comment) {

        //评论内容不能为空
        if(comment.getContent()==null){
            return ResponseResult.fail(ResponseEnum.COMMENT_CONTENT_IS_NULL.getCode(), ResponseEnum.COMMENT_CONTENT_IS_NULL.getMsg());
        }

        //设置评论时间
        comment.setCreateTime(new Date());
        commentMapper.insertComment(comment);
        return ResponseResult.ok();
    }


    @Override
    public ResponseResult<Void> deleteComment(Integer commentId) {
        commentMapper.deleteComment(commentId);
        return ResponseResult.ok();
    }


    @Override
    public ResponseResult<List<Comment>> selectAll() {
        List<Comment> commentList = commentMapper.selectAll();
        return ResponseResult.ok(commentList);
    }
}
