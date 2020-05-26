package com.gpdi.hqplus.pay.entity;

import java.math.BigDecimal;
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
 * @author lianghb
 * @since 2019-07-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_wepay_bill")
public class WepayBill extends Model<WepayBill> {

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
     * 交易时间
     */
    private String transDate;

    /**
     * 公众号id
     */
    private String commonId;

    /**
     * 商户号
     */
    private String businessNo;

    /**
     * 子商户号
     */
    private String childBusinessNo;

    /**
     * 设备号
     */
    private String equipmengNo;

    /**
     * 微信订单号
     */
    private String wxOrderNo;

    /**
     * 商户订单号
     */
    private String businessOrderNo;

    /**
     * 用户标识
     */
    private String userIdentity;

    /**
     * 交易类型
     */
    private String transType;

    /**
     * 交易状态
     */
    private String transStatus;

    /**
     * 付款银行
     */
    private String paymentBank;

    /**
     * 货币种类
     */
    private String currency;

    /**
     * 总金额
     */
    private BigDecimal totalAmount;

    /**
     * 代金券或立减优惠金额
     */
    private BigDecimal redEnvelopesAmount;

    /**
     * 微信退款单号
     */
    private String wxRefundNo;

    /**
     * 商户退款单号
     */
    private String businessRefundNo;

    /**
     * 退款金额
     */
    private BigDecimal refundAmount;

    /**
     * 代金券或立减优惠退款金额
     */
    private BigDecimal redEnvelopeRefundAmount;

    /**
     * 退款类型
     */
    private String refundType;

    /**
     * 退款状态
     */
    private String refundStatus;

    /**
     * 商品名称
     */
    private String businessName;

    /**
     * 商户数据包
     */
    private String businessData;

    /**
     * 手续费
     */
    private BigDecimal fee;

    /**
     * 费率
     */
    private BigDecimal rate;

    /**
     * 时间
     */
    private LocalDateTime createTime;

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


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
