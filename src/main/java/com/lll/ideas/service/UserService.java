package com.lll.ideas.service;

import com.lll.ideas.enums.ResponseEnum;
import com.lll.ideas.pojo.TokenPO;
import com.lll.ideas.pojo.User;
import com.lll.ideas.utils.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author lbh
 * @Date 2021/3/14
 */
public interface UserService {

    /**
     * 用户注册
     * @param user
     */
    ResponseResult<TokenPO> insertUser(User user);

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
    ResponseResult<List<User>> selectAll();


    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    ResponseResult<User> selectByUsername(String username);


    /**
     * 根据用户名模糊查询用户
     * @param username
     * @return
     */
    ResponseResult<List<User>> selectLikeUsername(String username);
}
