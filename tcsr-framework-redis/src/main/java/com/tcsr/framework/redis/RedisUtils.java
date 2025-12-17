package com.tcsr.framework.redis;

import com.tcsr.framework.common.utils.SpringUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author tangzhong
 * @date   2025-09-28 15:45
 * @since  V1.0.0.0
 */
public class RedisUtils {

    private RedisUtils(){}

    private static final RedisTemplate<String, Object> REDIS_TEMPLATE;
    static {
        //noinspection unchecked
        REDIS_TEMPLATE = SpringUtils.getBean(RedisTemplate.class, "redisTemplate");
    }

    public static RedisTemplate<String, Object> getRedisTemplate(){
        return REDIS_TEMPLATE;
    }

    public static void setValue(String key, Object value) {
        REDIS_TEMPLATE.opsForValue().set(key, value);
    }

    public static void setValue(String key, Object value, Long timeout) {
        REDIS_TEMPLATE.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    public static Object getValue(String key) {
        return REDIS_TEMPLATE.opsForValue().get(key);
    }

    public static void deleteKey(String key) {
        REDIS_TEMPLATE.delete(key);
    }

    public static boolean hasKey(String key) {
        return REDIS_TEMPLATE.hasKey(key);
    }

    public static <T> void setCacheMap(final String key, final Map<String, T> dataMap) {
        if (dataMap != null) {
            REDIS_TEMPLATE.opsForHash().putAll(key, dataMap);
        }
    }

    public static <T> void setCacheMapValue(final String key, final String hKey, final T value) {
        REDIS_TEMPLATE.opsForHash().put(key, hKey, value);
    }

    @SuppressWarnings("unchecked")
    public static <T> Map<String, T> getCacheMapValue(final String key) {
        Map<Object, Object> rawMap = REDIS_TEMPLATE.opsForHash().entries(key);
        return (Map<String, T>) (Map<?, ?>) rawMap;
    }

    public static <T> T getCacheMapValue(final String key, final String hKey) {
        HashOperations<String, String, T> opsForHash = REDIS_TEMPLATE.opsForHash();
        return opsForHash.get(key, hKey);
    }

}