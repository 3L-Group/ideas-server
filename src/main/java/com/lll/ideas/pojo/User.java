package com.lll.ideas.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author lbh
 * @Date 2021/3/14
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
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
     * 创建时间
     */
    private Date createTime;


}
