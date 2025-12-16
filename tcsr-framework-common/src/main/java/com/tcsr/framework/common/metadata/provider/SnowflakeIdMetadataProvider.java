package com.tcsr.framework.common.metadata.provider;

import com.tcsr.framework.common.metadata.MetadataTypeConstants;
import com.tcsr.framework.common.utils.SnowflakeIdUtils;
import org.springframework.stereotype.Component;

/**
 * @author tangzhong
 * @since  2025-10-24 11:30
 */
@Component
public class SnowflakeIdMetadataProvider extends AbstractMetadataProvider<Long> {

    public SnowflakeIdMetadataProvider(){
        super(MetadataTypeConstants.SNOW_FLACK_ID, ()-> SnowflakeIdUtils.getInstance().nextId());
    }

}