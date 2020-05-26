package com.gpdi.hqplus.apartment.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 公寓看房预约
 * </p>
 *
 * @author lianghb
 * @since 2019-07-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_apartment_apply")
public class ApartmentApply extends Model<ApartmentApply> {

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
     * 房型id
     */
    private Long roomId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 负责人id
     */
    private Long principalId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 联系方式
     */
    private String userConnect;

    /**
     * 用户留言
     */
    private String userMessage;

    /**
     * 预约时间
     */
    private LocalDate applyDate;

    /**
     * 最晚入住时间
     */
    private LocalDate lastEnterTime;

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
