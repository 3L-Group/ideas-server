package com.lll.ideas.service;

import com.lll.ideas.pojo.Article;
import com.lll.ideas.pojo.PO.TokenPO;
import com.lll.ideas.pojo.User;
import com.lll.ideas.pojo.VO.UserVO;
import com.lll.ideas.utils.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author lbh
 * @Date 2021/3/14
 */
public interface UserService {


    /**
     * 发送验证码
     * @param phone
     * @return
     */
    ResponseResult<String> sendVerificationCode(String phone);

    /**
     * 用户注册
     * @param user
     */
    ResponseResult<TokenPO> insertUser(User user, String verifyCode);

    /**
     * 删除用户
     * @return
     */
    ResponseResult<Void> deleteUser(Integer userId);

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    ResponseResult<Void> updateUser(User user);

    /**
     * 更新用户头像
     * @param user
     * @param avatar
     * @return
     */
    ResponseResult<Void> updateUserAvatar(User user, MultipartFile avatar);

    /**
     * 查询所有用户
     * @return
     */
    ResponseResult<List<UserVO>> selectAll();


    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    ResponseResult<UserVO> selectByUsername(String username);


    /**
     * 根据用户名模糊查询用户
     * @param username
     * @return
     */
    ResponseResult<List<UserVO>> selectLikeUsername(String username);


    /**
     * 查询关注列表
     * @param userId
     * @return
     */
    ResponseResult<List<Integer>> selectFollowList(Integer userId);


    /**
     * 查询粉丝列表
     * @param userId
     * @return
     */
    ResponseResult<List<Integer>> selectFollowerList(Integer userId);

    /**
     * 关注其他用户
     * @param followingId
     * @param followedId
     * @return
     */
    ResponseResult<Void> follow(Integer followingId,Integer followedId);

    /**
     * 取关
     * @param followingId
     * @param followedId
     * @return
     */
    ResponseResult<Void> cancelFollow(Integer followingId, Integer followedId);

    /**
     * 查询用户的所有文章
     * @param userId
     * @return
     */
    ResponseResult<List<Article>> selectArticleList(Integer userId);

}
