package com.gpdi.hqplus.pay.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 支付日志表
 * </p>
 *
 * @author zake
 * @since 2019-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_payment_log")
public class PaymentLog extends Model<PaymentLog> {

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
     * 状态
     */
    private String status;

    /**
     * 主键
     */
    private Long id;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 多租户代号
     */
    private String projectCode;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 备注
     */
    private String remark;

    /**
     * 当前操作用户id
     */
    private Long userId;

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 商品描述
     */
    private String productDescribe;

    /**
     * 生成的预付单id

     */
    private String prepareId;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 支付时间
     */
    private LocalDateTime applyTime;

    /**
     * 错误信息

     */
    private String errorInfo;

    /**
     * 扩展字段
     */
    private String extendVal;

    /**
     * 关联生成微信预付单外键id
     */
    private Long wechatPrepayId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
