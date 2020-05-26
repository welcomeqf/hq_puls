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
 * 用户扩展信息表
 * </p>
 *
 * @author lianghb
 * @since 2019-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_user_extend")
public class UserExtend extends Model<UserExtend> {

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
     * 用户 id
     */
    private Long userId;

    /**
     * 地址
     */
    private String address;

    /**
     * 名称
     */
    private String userName;

    /**
     * email
     */
    private String sex;

    /**
     * 密码
     */
    private String imgSrc;

    /**
     * 微信用户唯一标识
     */
    private String userType;

    /**
     * 微信会话秘钥
     */
    private String defaultProjectCode;

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
