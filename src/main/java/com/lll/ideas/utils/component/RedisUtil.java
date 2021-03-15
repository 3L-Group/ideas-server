package com.lll.ideas.utils.component;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.concurrent.TimeUnit;

/**
 * @Author: qyl
 * @Date: 2021/2/21 11:20
 * @Description: 获取序列化后的 RedisTemplate
 */
public class RedisUtil {

    /**
     * 通过工厂工具类获得 RedisTemplate
     * @return
     */
    public static RedisTemplate getRedisTemplate() {
        // 通过工厂工具类获取 redisTemplate
        RedisTemplate  redisTemplate = (RedisTemplate) ApplicationContextUtil.getBean("redisTemplate");

        // redis key 的 string 序列化方式
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // redis value 具有泛型的 json 序列化方式
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();

        // 序列化string类型
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);

        // 序列化hash类型
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer);

        return redisTemplate;
    }

    /**
     * 设置 string 类型值
     * @param key
     * @param value
     */
    public static void setValue(String key, Object value) {
        getRedisTemplate().opsForValue().set(key, value);
    }

    /**
     * 设置 string 类型值 + 过期时间
     * @param key
     * @param value
     */
    public static void setValue(String key, Object value, long timeout, TimeUnit unit) {
        getRedisTemplate().opsForValue().set(key, value, timeout, unit);
    }

    /**
     * 获取 string 类型值
     * @param key
     * @return
     */
    public static Object getValue(String key) {
        return getRedisTemplate().opsForValue().get(key);
    }

    /**
     * 设置 hash 类型值
     * @param key
     * @param hashKey
     * @param value
     */
    public static void putValue(String key, Object hashKey, Object value) {
        getRedisTemplate().opsForHash().put(key, hashKey, value);
    }

    /**
     * 获取 hash 类型值
     * @param key
     * @param hashKey
     * @return
     */
    public static Object getValue(String key, Object hashKey) {
        return getRedisTemplate().opsForHash().get(key, hashKey);
    }

    /**
     * 删除指定缓存
     * @param key
     */
    public static void delete(String key) {
        getRedisTemplate().delete(key);
    }
}
