package com.lll.ideas.controller;

import com.lll.ideas.pojo.Article;
import com.lll.ideas.pojo.PO.TokenPO;
import com.lll.ideas.pojo.User;
import com.lll.ideas.pojo.VO.UserVO;
import com.lll.ideas.service.UserService;
import com.lll.ideas.utils.ResponseResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.Serializable;
import java.util.List;

/**
 * @Author lbh
 * @Date 2021/3/14
 */

@RestController
@RequestMapping("/User")
public class UserController implements Serializable {

    @Autowired
    private UserService userService;

    /**
     * 发送验证码
     * @param phone
     * @return
     */
    @PostMapping("/send")
    public ResponseResult<String> sendVerificationCode(String phone) {
        return userService.sendVerificationCode(phone);
    }

    /**
     * 用户注册
     * @param user
     * @param verifyCode
     * @return
     */
    @PostMapping("/register")
    public ResponseResult<TokenPO> insertUser(User user, String verifyCode){
        return userService.insertUser(user, verifyCode);
    }

    /**
     * 用户登录
     * @param username 用户名
     * @param password 用户密码
     * @return
     */
    @PostMapping("/login")
    public ResponseResult<TokenPO> userLogin(String username, String password){ return userService.userLogin(username,password); }

    /**
     * 删除用户
     * @param userId 用户id
     * @return
     */
    @PostMapping("/delete")
    public ResponseResult<Void> deleteUser(Integer userId){
        return userService.deleteUser(userId);
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @PostMapping("/update")
    public ResponseResult<Void> updateUser(User user){
        return userService.updateUser(user);
    }

    @PostMapping("/updateAvatar")
    public ResponseResult<Void> updateAvatar(User user,MultipartFile avatar){
        return userService.updateUserAvatar(user,avatar);
    }


    /**
     * 查询所有用户
     * @return
     */
    @GetMapping("/selectAll")
    public ResponseResult<List<UserVO>> selectAll(){
        return userService.selectAll();
    }

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    @GetMapping("/selectByUsername")
    public ResponseResult<UserVO> selectByUsername(String username){
        return userService.selectByUsername(username);
    }

    /**
     * 根据用户名模糊查询
     * @param username
     * @return
     */
    @GetMapping("/selectLikeUsername")
    public ResponseResult<List<UserVO>> selectLikeUsername(String username){
        return userService.selectLikeUsername(username);
    }

    /**
     * 查询关注列表
     * @param userId
     * @return
     */
    @GetMapping("/selectFollowList")
    public ResponseResult<List<Integer>> selectFollowList(Integer userId){
        return userService.selectFollowList(userId);
    }

    /**
     * 查询粉丝列表
     * @param userId
     * @return
     */
    @GetMapping("/selectFollowerList")
    public ResponseResult<List<Integer>> selectFollowerList(Integer userId){
        return userService.selectFollowerList(userId);
    }

    /**
     * 关注其他用户
     * @param followingId
     * @param followedId
     * @return
     */
    @PostMapping("/follow")
    public ResponseResult<Void> follow(Integer followingId,Integer followedId){
        return userService.follow(followingId,followedId);
    }

    /**
     * 取关
     * @param followingId
     * @param followedId
     * @return
     */
    @PostMapping("/cancelFollow")
    public ResponseResult<Void> cancelFollow(Integer followingId,Integer followedId){
        return userService.cancelFollow(followingId,followedId);
    }

    /**
     * 查询用户的所有文章
     * @param userId
     * @return
     */
    @GetMapping("/selectArticleList")
    public ResponseResult<List<Article>> selectArticleList(Integer userId){
        return userService.selectArticleList(userId);
    }
}
