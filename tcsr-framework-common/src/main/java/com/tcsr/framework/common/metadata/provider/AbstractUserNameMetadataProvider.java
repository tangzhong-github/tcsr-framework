package com.tcsr.framework.common.metadata.provider;

import com.tcsr.framework.common.metadata.MetadataTypeConstants;

import java.util.function.Supplier;

/**
 *
 * @author tangzhong
 * @date   2025-10-24 11:30
 * @since  V1.0.0.0
 */
public abstract class AbstractUserNameMetadataProvider extends AbstractMetadataProvider<String> {

    public AbstractUserNameMetadataProvider(Supplier<String> provider){
        super(MetadataTypeConstants.USER_NAME, provider);
    }

}