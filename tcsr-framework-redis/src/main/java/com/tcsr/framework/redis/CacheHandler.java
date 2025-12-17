package com.tcsr.framework.redis;

/**
 *
 * @author tangzhong
 * @date   2025-11-03 17:46
 * @since  V1.0.0.0
 */
public interface CacheHandler {

    void cache();

    default <T> T load(){
        throw new UnsupportedOperationException("Unsupported Operation For Load Cache");
    }
}