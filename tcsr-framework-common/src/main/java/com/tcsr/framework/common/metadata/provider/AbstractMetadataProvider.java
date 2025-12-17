package com.tcsr.framework.common.metadata.provider;

import com.tcsr.framework.common.metadata.MetadataProvider;
import com.tcsr.framework.common.metadata.MetadataProviderFactory;
import jakarta.annotation.PostConstruct;

import java.util.function.Supplier;

/**
 *
 * @author tangzhong
 * @date   2025-10-24 11:32
 * @since  V1.0.0
 */
public abstract class AbstractMetadataProvider<T> implements MetadataProvider<T> {

    private final String name;

    protected final Supplier<T> provider;

    protected AbstractMetadataProvider(String name, Supplier<T> provider) {
        this.name = name;
        this.provider = provider;
    }

    @PostConstruct
    protected void register(){
        MetadataProviderFactory.register(this);
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public T value() {
        return provider.get();
    }
}