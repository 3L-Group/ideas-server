package com.lll.ideas.dao;

import com.lll.ideas.pojo.User;
import com.lll.ideas.utils.ResponseResult;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author lbh
 * @Date 2021/3/14
 */


public interface UserMapper {
    /**
     * 注册
     */
    int insertUser(User user);

    /**
     * 登录(验证账号密码)
     */
    int login(String username,String password);

    /**
     * 删除用户
     */
    int deleteUser(Integer userId);

    /**
     * 更改用户信息
     */
    int updateUser(User user);

    /**
     * 查询所有用户
     */
    List<User> selectAll();


    /**
     * 根据用户名查询用户
     */
    User selectByUsername(String username);

    /**
     * 根据用户名模糊查询用户
     */
    List<User> selectLikeUsername(String username);

}
