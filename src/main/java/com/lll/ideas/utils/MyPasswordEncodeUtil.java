package com.lll.ideas.utils;

import org.springframework.util.DigestUtils;


/**
 * 密码加密
 * @author hly
 * @create 2021-03-14 22:08
 */
public class MyPasswordEncodeUtil {

    /**
     * 密码加密
     * @param rawPassword
     * @return
     */
    public static String encode(CharSequence rawPassword){
        return DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes());
    }


    /**
     * 登录匹配
     * @param rawPassword
     * @param encodePassword
     * @return
     */
    public static boolean matches(CharSequence rawPassword, String encodePassword){
        return encodePassword.equals(DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes()));
    }
}
