package com.tcsr.framework.common.metadata;

/**
 *
 * @author tangzhong
 * @since  2025-10-24 11:28
 */
public interface MetadataProvider<T> {

    String name();

    T value();

}
