package com.lll.ideas.enums;

/**
 * @Author: qyl
 * @Date: 2020/12/7 9:54
 */
public enum ResponseEnum {
    SUCCESS(1, "成功"),
    FAIL(0, "失败"),

    // 用户模块错误
    USER_NOT_FOUND(1001, "用户不存在"),
    USER_EXIST(1002, "用户已存在"),
    AVATAR_IS_NULL(1003, "头像为空"),
    VERIFY_CODE_INCORRECT(1004, "验证码不正确"),
    USER_LOGIN_ERROR(1005,"用户名不存在或者密码不正确"),

    // 文章模块错误
    ARTICLE_NOT_FOUND(2001,"文章不存在"),
    TITLE_IS_NULL(2002,"文章标题为空"),
    ARTICLE_CONTENT_IS_NULL(2003,"文章内容为空"),

    // 评论模块错误
    COMMENT_CONTENT_IS_NULL(3001,"评论内容为空")
    ;

    /**
     * 状态码
     */
    private int code;

    /**
     * 状态信息
     */
    private String msg;

    ResponseEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
