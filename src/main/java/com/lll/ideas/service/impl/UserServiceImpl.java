package com.lll.ideas.service.impl;

import com.lll.ideas.dao.UserMapper;
import com.lll.ideas.enums.ResponseEnum;
import com.lll.ideas.pojo.Article;
import com.lll.ideas.pojo.PO.TokenPO;
import com.lll.ideas.pojo.User;
import com.lll.ideas.pojo.VO.UserVO;
import com.lll.ideas.service.UserService;
import com.lll.ideas.utils.component.MyPasswordEncodeUtil;
import com.lll.ideas.utils.ResponseResult;
import com.lll.ideas.utils.component.RedisUtil;
import com.lll.ideas.utils.component.TokenUtil;
import com.lll.ideas.utils.component.VerifyCodeUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author lbh
 * @Date 2021/3/14
 */

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    private static final String DEFAULT_USER_IMAGE_URL = "src/main/resources/avatar/default.jpg";

    private static final String USER_PHONE_CODE = "user:phone:code:";

    @Override
    public ResponseResult<String> sendVerificationCode(String phone) {
        // 通过手机号判断用户是否存在
        if (userMapper.selectByPhone(phone) != null) {
            return ResponseResult.fail(ResponseEnum.USER_EXIST.getCode(), ResponseEnum.USER_EXIST.getMsg());
        }
        String code = VerifyCodeUtil.generateCode(6);
        RedisUtil.setValue(USER_PHONE_CODE + phone, code, 5, TimeUnit.MINUTES);
        return ResponseResult.ok(code);
    }

    @Override
    public ResponseResult<TokenPO> insertUser(User user, String verifyCode) {

        //校验验证码
        if (!verifyCode.equals(RedisUtil.getValue(USER_PHONE_CODE + user.getPhone()))) {
            return ResponseResult.fail(ResponseEnum.VERIFY_CODE_INCORRECT.getCode(), ResponseEnum.VERIFY_CODE_INCORRECT.getMsg());
        }

        User userByName = userMapper.selectByUsername(user.getUsername());
        if (userByName == null){
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
                TokenPO tokenPO = new TokenPO(TokenUtil.genToken(user.getUserId()), user);
                return ResponseResult.ok(tokenPO);
            }
        }
        return ResponseResult.fail(ResponseEnum.USER_EXIST.getCode(), ResponseEnum.USER_EXIST.getMsg());
    }

    @Override
    public ResponseResult<TokenPO> userLogin(String username, String password) {
        User user = userMapper.selectByUsername(username);
        try{
            //输入pwd加密
            String encode = MyPasswordEncodeUtil.encode(password);
            if(user != null && MyPasswordEncodeUtil.matches(user.getPassword(),encode)){
                TokenPO tokenPO = new TokenPO(TokenUtil.genToken(user.getUserId()), user);
                return ResponseResult.ok(tokenPO);
            }else{
                return ResponseResult.fail(ResponseEnum.USER_LOGIN_ERROE.getCode(),ResponseEnum.USER_LOGIN_ERROE.getMsg());
            }
        }catch (Exception e){
            return ResponseResult.fail();
        }
    }

    @Override
    public ResponseResult<Void> deleteUser(Integer userId) {
        userMapper.deleteUser(userId);
        return ResponseResult.ok();
    }

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

    @Override
    public ResponseResult<List<UserVO>> selectAll() {
        List<User> userList = userMapper.selectAll();
        List<UserVO> userVOList = null;

        for (User user : userList) {
            UserVO userVO = convert(user);
            userVOList.add(userVO);
        }
        if(!userVOList.isEmpty()){
            return ResponseResult.ok(userVOList);
        }

        return ResponseResult.fail();

    }

    @Override
    public ResponseResult<UserVO> selectByUsername(String username) {
        User user = userMapper.selectByUsername(username);
        if(user != null){
            UserVO userVO = convert(user);
            return ResponseResult.ok(userVO);
        }

        return ResponseResult.fail(ResponseEnum.USER_NOT_FOUND.getCode(), ResponseEnum.USER_NOT_FOUND.getMsg());

    }

    @Override
    public ResponseResult<List<UserVO>> selectLikeUsername(String username) {
        List<User> userList = userMapper.selectLikeUsername(username);
        List<UserVO> userVOList = null;
        for (User user : userList) {
            UserVO userVO = convert(user);
            userVOList.add(userVO);
        }

        if(!userVOList.isEmpty()){
            return ResponseResult.ok(userVOList);
        }

        return ResponseResult.fail(ResponseEnum.USER_NOT_FOUND.getCode(), ResponseEnum.USER_NOT_FOUND.getMsg());
    }

    @Override
    public ResponseResult<List<Integer>> selectFollowList(Integer userId) {
        List<Integer> followList = userMapper.selectFollowList(userId);
        return ResponseResult.ok(followList);

    }

    @Override
    public ResponseResult<List<Integer>> selectFollowerList(Integer userId) {
        List<Integer> followerList = userMapper.selectFollowerList(userId);
        return ResponseResult.ok(followerList);
    }

    @Override
    public ResponseResult<Void> follow(Integer followingId, Integer followedId) {
        userMapper.follow(followingId, followedId);
        return ResponseResult.ok();
    }

    @Override
    public ResponseResult<Void> cancelFollow(Integer followingId, Integer followedId) {
        userMapper.cancelFollow(followingId,followedId);
        return ResponseResult.ok();
    }


    @Override
    public ResponseResult<List<Article>> selectArticleList(Integer userId) {
        List<Article> articleList = userMapper.selectArticleList(userId);
        return ResponseResult.ok(articleList);
    }


    /**
     * user 转换成 userVO
     * @param user
     * @return
     */
    private UserVO convert(User user) {
        UserVO userVO = new UserVO();
        userVO.setUserId(user.getUserId());
        userVO.setGender(user.getGender());
        userVO.setPhone(user.getPhone());
        userVO.setInfo(user.getInfo());
        userVO.setAvatar(user.getAvatar());
        userVO.setFollow(userMapper.selectFollowList(user.getUserId()).size());
        userVO.setFollower(userMapper.selectFollowerList(user.getUserId()).size());
        return userVO;
    }
}
