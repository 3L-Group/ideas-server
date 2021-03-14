package com.lll.ideas.service.impl;

import com.lll.ideas.dao.UserMapper;
import com.lll.ideas.pojo.User;
import com.lll.ideas.service.UserService;
import com.lll.ideas.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Author lbh
 * @Date 2021/3/14
 */

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    @Override
    public ResponseResult<Void> deleteUser(Integer userId) {
        userMapper.deleteUser(userId);
        return ResponseResult.ok();
    }

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    @Override
    public ResponseResult<Void> updateUser(User user) {

        if(user.getUserId()==null){
            return ResponseResult.fail();
        }
        user.setUserId(user.getUserId());
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());
        Byte gender = user.getGender();
        user.setGender(gender);
        user.setPhone(user.getPhone());
        user.setInfo(user.getInfo());

        userMapper.updateUser(user);

        return ResponseResult.ok();
    }

    /**
     * 更新用户头像
     *
     * @param userId
     * @param avatar
     * @return
     */
    @Override
    public ResponseResult<Void> updateUserAvatar(Integer userId,MultipartFile avatar){
        if(avatar.isEmpty()){
            return ResponseResult.fail();
        }

        //文件名、文件存放路径名
        String filename = System.currentTimeMillis() +avatar.getOriginalFilename();
        String filepath = System.getProperty("user.dir")
                + System.getProperty("file.separator")
                +"src"
                +System.getProperty("file.separator")
                +"resources"
                +System.getProperty("file.separator")
                +"avatar";

        File file = new File(filepath);

        //若文件目录不存在则创建
        if(!file.exists()){
            file.mkdir();
        }

        //拼接绝对路径
        File dest =new File(filepath + System.getProperty("file.separator") + filename);
        String storeAvatarPath = "/avatar/" + filename;

        //持久化到数据库
        try {
            avatar.transferTo(dest);
            User newUser = new User();
            newUser.setUserId(userId);
            newUser.setAvatar(storeAvatarPath);
            userMapper.updateUser(newUser);

            return ResponseResult.ok();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseResult.fail();
        }

    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @Override
    public ResponseResult<List<User>> selectAll() {
        List<User> userList = userMapper.selectAll();
        if(!userList.isEmpty()){
            return ResponseResult.ok(userList);
        }

        return ResponseResult.fail();

    }

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    @Override
    public ResponseResult<User> selectByUsername(String username) {
        User user = userMapper.selectByUsername(username);
        if(user != null){
            return ResponseResult.ok(user);
        }

        return ResponseResult.fail();

    }

    /**
     * 根据用户名模糊查询用户
     *
     * @param username
     * @return
     */
    @Override
    public ResponseResult<List<User>> selectLikeUsername(String username) {
        List<User> userList = userMapper.selectLikeUsername(username);
        if(!userList.isEmpty()){
            return ResponseResult.ok(userList);
        }

        return ResponseResult.fail();
    }
}
