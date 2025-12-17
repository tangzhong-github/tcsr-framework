package com.tcsr.framework.common.metadata;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author tangzhong
 * @date   2025-10-24 11:34
 * @since  V1.0.0.0
 */
public class MetadataProviderFactory {

    private static final Map<String, MetadataProvider<?>> METADATA_PROVIDER_MAP = new ConcurrentHashMap<>();

    public static void register(MetadataProvider<?> metadataProvider){
        METADATA_PROVIDER_MAP.put(metadataProvider.name(), metadataProvider);
    }

    public static MetadataProvider<?> get(String metadataName){
        return METADATA_PROVIDER_MAP.get(metadataName);
    }

}