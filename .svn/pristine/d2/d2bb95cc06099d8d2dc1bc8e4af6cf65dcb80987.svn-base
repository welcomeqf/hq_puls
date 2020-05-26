package com.gpdi.hqplus.bill.entity;

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
 * 
账单
 * </p>
 *
 * @author lianghb
 * @since 2019-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_bill_order")
public class BillOrder extends Model<BillOrder> {

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
     * 用户 id
     */
    private Long userId;

    /**
     * 订单号
     */
    private String orderCode;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 支付类型
     */
    private String payType;

    /**
     * 计费周期
     */
    private Integer payCycle;

    /**
     * 周期单位
     */
    private String cycleUnit;

    private String userMessage;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime entTime;

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
