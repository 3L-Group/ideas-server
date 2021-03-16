package com.lll.ideas.controller;

import com.lll.ideas.pojo.Comment;
import com.lll.ideas.service.CommentService;
import com.lll.ideas.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author lbh
 * @Date 2021/3/16
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/insertComment")
    public ResponseResult<Void> insertComment(Comment comment){
        return commentService.insertComment(comment);
    }

    @PostMapping("/deleteComment")
    public ResponseResult<Void> deleteComment(Integer commentId){
        return commentService.deleteComment(commentId);
    }

    @GetMapping("/selectAll")
    public ResponseResult<List<Comment>> selectAll(){
        return commentService.selectAll();
    }




}
