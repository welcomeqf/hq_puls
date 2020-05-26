package com.gpdi.hqplus.pass.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zake
 * @since 2019-07-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_product_pass")
public class ProductPass extends Model<ProductPass> {

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
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 地址
     */
    private String address;

    /**
     * 备注
     */
    private String remark;

    /**
     * 物品清单
     */
    private String items;

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
    private LocalDateTime createTime;

    /**
     * 申请放行时间
     */
    private LocalDateTime passDate;

    /**
     * 电话号码
     */
    private String phoneNumber;

    /**
     * 二维码id
     */
    private String codeSrc;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 审核时间
     */
    private LocalDateTime auditTime;

    /**
     * 放行时间
     */
    private LocalDateTime finishTime;

    /**
     * 多租户代号
     */
    private String projectCode;

    /**
     * 扩展信息
     */
    private String extendVal;

    /**
     * 地址类型
     */
    private String addressType;

    /**
     * 流程节点
     */
    private String taskId;

    /**
     * 放行人
     */
    private String policemenName;

    /**
     * 放行方式
     */
    private String policemenType;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
