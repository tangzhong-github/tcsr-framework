package com.tcsr.framework.redis;

/**
 *
 * @author tangzhong
 * @since   2025-11-03 17:46
 */
public interface CacheHandler {

    void cache();

    default <T> T load(){
        throw new UnsupportedOperationException("Unsupported Operation For Load Cache");
    }
}