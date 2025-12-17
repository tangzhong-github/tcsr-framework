package com.tcsr.framework.common.metadata.provider;

import com.tcsr.framework.common.metadata.MetadataTypeConstants;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author tangzhong
 * @date   2025-10-24 11:30
 * @since  V1.0.0
 */
@Component
public class LocalTimeMetadataProvider extends AbstractMetadataProvider<LocalDateTime> {

    public LocalTimeMetadataProvider(){
        super(MetadataTypeConstants.TIME, LocalDateTime::now);
    }

}