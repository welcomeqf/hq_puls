package com.gpdi.hqplus.process.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 投诉建议表
 * </p>
 *
 * @author lianghb
 * @since 2019-06-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_complaint")
public class Complaint extends Model<Complaint> {

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
    private String type;

    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 信息
     */
    private String message;

    /**
     * 用户评论
     */
    private String userComment;

    /**
     * 评分
     */
    private Integer userStar;

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
