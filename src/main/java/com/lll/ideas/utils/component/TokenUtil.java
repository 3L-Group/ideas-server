package com.lll.ideas.utils.component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Calendar;

/**
 * token工具类
 * @author hly
 * @date 2020/2/3 15:22
 */
public class TokenUtil {

    private static final String signature = "Q!w2XS%^63p*";

    private static final String PAYLOAD_NAME = "user_phone";

    /**
     * 通过用户 ID 生成 token
     * @param userId
     * @return
     */
    public static String genToken(Integer userId) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 7);  // 默认7天过期

        // 生成token
        String token = JWT.create()
                .withClaim(PAYLOAD_NAME, userId)
                .withExpiresAt(instance.getTime())  // 指定token过期时间
                .sign(Algorithm.HMAC256(signature));

        return token;
    }

    /**
     * 验证 token
     * 若验证出错将会抛出异常
     * @param token
     * @return 用户信息 (userPhone)
     */
    public static String verifyToken(String token) {
        return JWT.require(Algorithm.HMAC256(signature))
                .build()
                .verify(token)
                .getClaim(PAYLOAD_NAME)
                .asString();
    }
}
