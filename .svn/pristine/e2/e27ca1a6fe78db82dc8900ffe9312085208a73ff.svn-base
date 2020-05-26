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
 * 报名预定
 * </p>
 *
 * @author wzr
 * @since 2019-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_book")
public class Book extends Model<Book> {

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
     * 业务代码
     */
    private String businessCode;

    /**
     * 类型
     */
    private String type;

    /**
     * 资源 id
     */
    private Long resourceId;

    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 用户留言
     */
    private String message;

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

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 多租户代号
     */
    private String projectCode;

    /**
     * 预约时间
     */
    private LocalDateTime applyTime;

    /**
     * 扩展信息
     */
    private String extendVal;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
