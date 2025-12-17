package com.tcsr.framework.common.metadata;

/**
 *
 * @author tangzhong
 * @date   2025-10-24 11:28
 * @since  V1.0.0.0
 */
public interface MetadataProvider<T> {

    String name();

    T value();

}
