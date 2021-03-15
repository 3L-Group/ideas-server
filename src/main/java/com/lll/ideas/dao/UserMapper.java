package com.lll.ideas.dao;

import com.lll.ideas.pojo.User;
import com.lll.ideas.utils.ResponseResult;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

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
     *
     * @param userId
     * @return
     */
    int deleteUser(Integer userId);

    /**
     *
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     *
     * @return
     */
    List<User> selectAll();

    /**
     *
     * @param username
     * @return
     */
    User selectByUsername(String username);

    /**
     *
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

}
