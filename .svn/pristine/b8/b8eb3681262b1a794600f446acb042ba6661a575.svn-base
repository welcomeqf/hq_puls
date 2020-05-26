package com.gpdi.hqplus.user.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author lianghb
 * @since 2019-06-10
 */
@Data
@TableName("tb_sys_menu")
public class SysMenu extends Model<SysMenu> {

    private static final long serialVersionUID = 1L;


    /**
     * 状态：正常可用
     */
    public static final String STATUS_NORMAL = "normal";
    /**
     * 状态：禁用
     */
    public static final String STATUS_DISABLED = "disabled";

    /**
     * id
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 代号
     */
    private String code;

    /**
     * 状态
     */
    private String status;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 园区代号
     */
    private String parkCode;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
