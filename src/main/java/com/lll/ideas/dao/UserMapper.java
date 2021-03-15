package com.lll.ideas.dao;

import com.lll.ideas.pojo.Article;
import com.lll.ideas.pojo.User;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * @Author lbh
 * @Date 2021/3/14
 */


public interface UserMapper{


    /**
     * 用户注册
     * @param user
     */
    int insertUser(User user);

    /**
     * 登录(验证账号密码)
     */
    int login(String username,String password);

    /**
     * 删除用户
     * @param userId
     * @return
     */
    int deleteUser(Integer userId);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 查询全部用户
     * @return
     */
    List<User> selectAll();

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User selectByUsername(String username);

    /**
     * 根据用户名模糊查询
     * @param username
     * @return
     */
    List<User> selectLikeUsername(String username);

    /**
     * 根据手机号查询用户
     * @param phone
     * @return
     */
    User selectByPhone(String phone);


    /**
     * 查询关注列表
     * @param userId
     * @return
     */
    List<Integer> selectFollowList(Integer userId);

    /**
     * 查询粉丝列表
     * @param userId
     * @return
     */
    List<Integer> selectFollowerList(Integer userId);

    /**
     * 关注其他用户
     * @param followingId
     * @param followedId
     * @return
     */
    int follow(@Param("followingId") Integer followingId,@Param("followedId") Integer followedId);

    /**
     * 取关
     * @param followingId
     * @param followedId
     * @return
     */
    int cancelFollow(@Param("followingId") Integer followingId,@Param("followedId") Integer followedId);

    /**
     * 查询用户的所有文章
     * @return
     */
    List<Article> selectArticleList(Integer userId);

}
