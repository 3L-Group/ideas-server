package com.lll.ideas.service.impl;

import com.lll.ideas.dao.UserMapper;
import com.lll.ideas.enums.ResponseEnum;
import com.lll.ideas.pojo.PO.TokenPO;
import com.lll.ideas.pojo.User;
import com.lll.ideas.service.UserService;
import com.lll.ideas.utils.MyPasswordEncodeUtil;
import com.lll.ideas.utils.ResponseResult;
import com.lll.ideas.utils.TokenUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Author lbh
 * @Date 2021/3/14
 */

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private TokenUtil tokenUtil;

    private static  final String DEFAULT_USER_IMAGE_URL = "https://pic1.zhimg.com/80/v2-94b9d3b4390326944eed56b03182b1d7_720w.jpg?source=1940ef5c";


    /**
     * 用户注册
     * @param user
     */
    @Override
    public ResponseResult<TokenPO> insertUser(User user) {

        User userByName = userMapper.selectByUsername(user.getUsername());
        if(userByName == null){
            //设置创建时间
            user.setCreateTime(new Date());
            //设置默认头像
            user.setAvatar(DEFAULT_USER_IMAGE_URL);
            try {
                //加密密码
                user.setPassword(MyPasswordEncodeUtil.encode(user.getPassword()));
            }catch (Exception e){
                return ResponseResult.fail();
            }
            int affectedRow = userMapper.insertUser(user);
            if(affectedRow > 0){
                //生成token
                TokenPO tokenPO = new TokenPO(tokenUtil.tokenByUserId(user.getUserId()), user);
                return ResponseResult.ok(tokenPO);
            }
            return ResponseResult.fail();
        }else{
            return ResponseResult.fail();
        }
    }

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
            return ResponseResult.fail(ResponseEnum.USER_NOT_FOUND.getCode(), ResponseEnum.USER_NOT_FOUND.getMsg());
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
     * @param user
     * @param avatar
     * @return
     */
    @Override
    public ResponseResult<Void> updateUserAvatar(User user,MultipartFile avatar){

        //若上传头像文件为空
        if(avatar == null){
            return ResponseResult.fail(ResponseEnum.AVATAR_IS_NULL.getCode(), ResponseEnum.AVATAR_IS_NULL.getMsg());
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
            newUser.setUserId(user.getUserId());
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
