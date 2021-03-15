package com.lll.ideas.pojo.VO;

import lombok.Data;

/**
 * @Author: qyl
 * @Date: 2021/3/15 10:45
 * @Description:
 */
@Data
public class UserVO {
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户性别
     */
    private Byte gender;
    /**
     * 用户电话
     */
    private String phone;
    /**
     * 个人简介
     */
    private String info;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 用户关注的博主
     */
    private Integer follow;
    /**
     * 用户的粉丝
     */
    private Integer follower;
}
