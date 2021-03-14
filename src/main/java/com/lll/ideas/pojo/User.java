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
    private Integer userId;
    private String username;
    private String password;
    private Byte gender;
    private String phone;
    private String info;
    private String avatar;
    private Date createTime;

}
