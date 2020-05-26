package com.gpdi.hqplus.apartment.entity;

import java.math.BigDecimal;

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
 * 公寓房型
 * </p>
 *
 * @author lianghb
 * @since 2019-07-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_apartment_room")
public class ApartmentRoom extends Model<ApartmentRoom> {

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
     * 系列id
     */
    private Long seriesId;

    /**
     * 房型名称
     */
    private String name;

    /**
     * 最小价格
     */
    private BigDecimal minPrice;

    /**
     * 最大价格
     */
    private BigDecimal maxPrice;

    /**
     * 面积
     */
    private BigDecimal area;

    /**
     * 设备信息
     */
    private String equipments;

    /**
     * 描述
     */
    private String description;

    /**
     * 朝向
     */
    private String toward;

    /**
     * 户型
     */
    private String roomModel;

    /**
     * 租赁方式
     */
    private String rentType;

    /**
     * 租期
     */
    private String rentTime;

    /**
     * 展示图片
     */
    private String showImages;

    /**
     * 户型图片
     */
    private String modelImages;

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
     * 项目代号
     */
    private String projectCode;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
