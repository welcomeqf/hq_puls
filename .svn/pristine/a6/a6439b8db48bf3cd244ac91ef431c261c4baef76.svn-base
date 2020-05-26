package com.gpdi.hqplus.enterprise.entity;

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
 * 企业信息表
 * </p>
 *
 * @author wzr
 * @since 2019-07-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_enterprise")
public class Enterprise extends Model<Enterprise> {

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
     * 类型
     */
    private String type;

    /**
     * 名称
     */
    private String name;

    /**
     * 代码
     */
    private String code;

    /**
     * 企业所属行业
     */
    private String industry;

    /**
     * 管理者名称(法人)
     */
    private String managerName;

    /**
     * 联系方式
     */
    private String tel;

    /**
     * 行政人员
     */
    private String personnel;

    /**
     * 行政人员联系方式
     */
    private String personnelTel;

    /**
     * 企业所属楼层
     */
    private String floor;

    /**
     * 企业所在单元
     */
    private String unitCode;

    /**
     * 企业管家
     */
    private String houseKeeper;

    /**
     * 简介
     */
    private String description;

    /**
     * 备注
     */
    private String remark;

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


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
