package com.gpdi.hqplus.resources.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 物业资源
 * </p>
 *
 * @author wzr
 * @since 2019-07-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_property_resource")
public class PropertyResource extends Model<PropertyResource> {

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
     * 业务代号
     */
    private String businessCode;

    /**
     * 类型
     */
    private String type;

    /**
     * 标题
     */
    private String name;

    /**
     * 描述
     */
    private String context;

    /**
     * 作者
     */
    private Long creator;

    /**
     * 地址
     */
    private String address;

    /**
     * 链接
     */
    private String linkUrl;

    /**
     * 面积
     */
    private BigDecimal area;

    /**
     * 楼层
     */
    private String floor;

    /**
     * 单元
     */
    private String unitCode;

    /**
     * 状态
     */
    private String status;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 多租户代号
     */
    private String projectCode;

    private Integer size;

    /**
     * 设备信息
     */
    private String equipment;

    /**
     * 户型 id数组
     */
    private String roomTypeId;

    /**
     * 额外资源
     */
    private String extendVal;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
