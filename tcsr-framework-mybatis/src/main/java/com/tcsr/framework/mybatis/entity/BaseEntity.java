package com.tcsr.framework.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.tcsr.framework.common.metadata.MetadataTypeConstants;
import com.tcsr.framework.mybatis.annotation.Metadata;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.apache.ibatis.mapping.SqlCommandType;

import java.time.LocalDateTime;

/**
 *
 * @author tangzhong
 * @date   2025-08-27 14:52
 * @since  V1.0.0
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class BaseEntity{

    /** 主键ID */
    @TableId
    @Metadata(dataType = MetadataTypeConstants.SNOW_FLACK_ID)
    private Long id;

    /** 创建人ID */
    @Metadata(dataType = MetadataTypeConstants.USER_ID)
    private Long creatorId;

    /** 创建人 */
    @Metadata(dataType = MetadataTypeConstants.USER_NAME)
    private String creatorName;

    /** 创建时间 */
    @Metadata(dataType = MetadataTypeConstants.TIME)
    private LocalDateTime createTime;

    /** 更新人ID */
    @Metadata(dataType = MetadataTypeConstants.USER_ID, commandTypes = {SqlCommandType.INSERT, SqlCommandType.UPDATE})
    private Long updaterId;

    /** 更新人 */
    @Metadata(dataType = MetadataTypeConstants.USER_NAME, commandTypes = {SqlCommandType.INSERT, SqlCommandType.UPDATE})
    private String updaterName;

    /** 更新时间 */
    @Metadata(dataType = MetadataTypeConstants.TIME, commandTypes = {SqlCommandType.INSERT, SqlCommandType.UPDATE})
    private LocalDateTime updateTime;

}