package com.gpdi.hqplus.authentication.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色权限关联表
 * </p>
 *
 * @author lianghb
 * @since 2019-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_role_perm_rel")
public class RolePermRel extends Model<RolePermRel> {

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
     * 权限代号
     */
    private String permissionCode;

    /**
     * 角色代号
     */
    private String roleCode;

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
    @TableField(fill = FieldFill.INSERT)
    private String projectCode;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
