package com.tcsr.framework.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 *
 * @author tangzhong
 * @since  2025-08-27 14:52
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class BaseEntity{

    /** 主键ID */
    @TableId
    private Long id;

    /** 创建人 */
    private String creator;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 更新人 */
    private String updater;

    /** 更新时间 */
    private LocalDateTime updateTime;

}