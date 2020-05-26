package com.gpdi.hqplus.resources.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author wzr
 * @since 2019-07-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_sys_business_file")
public class SysBusinessFile extends Model<SysBusinessFile> {

    private static final long serialVersionUID = 1L;

    /**
     * 状态：正常可用
     */
    public static String STATUS_NORMAL = "normal";

    /**
     * 状态：禁用
     */
    public static String STATUS_DISABLED = "disabled";


    /**
     * id
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 类型代码
     */
    private String code;

    /**
     * 文件id
     */
    private Long fileId;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 介绍
     */
    private String description;

    /**
     * 状态
     */
    private String status;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 多租户代号
     */
    private String projectCode;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
