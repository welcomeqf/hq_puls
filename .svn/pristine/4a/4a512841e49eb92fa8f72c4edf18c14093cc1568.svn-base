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
 * 文件资源
 * </p>
 *
 * @author lianghb
 * @since 2019-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_file_resource")
public class FileResource extends Model<FileResource> {

    private static final long serialVersionUID = 1L;

    /**
     * 状态：正常可用
     */
    public static String STATUS_NORMAL = "normal";

    /**
     * 状态：禁用
     */
    public static String STATUS_DISABLED = "disabled";


    private Long id;

    /**
     * 类型
     */
    private String fileType;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 角色代号
     */
    private String filePath;

    /**
     * 大小
     */
    private Long fileSize;

    /**
     * 父 id
     */
    private Long parentId;

    /**
     * 序号
     */
    private Integer serialNumber;

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

    /**
     * 扩展类型
     */
    private String extendType;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
