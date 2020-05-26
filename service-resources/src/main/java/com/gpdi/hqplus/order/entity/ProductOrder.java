package com.gpdi.hqplus.order.entity;

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
 * 订单表
 * </p>
 *
 * @author lianghb
 * @since 2019-07-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_product_order")
public class ProductOrder extends Model<ProductOrder> {

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
     * 业务代号
     */
    private String businessCode;

    /**
     * 订单号
     */
    private String orderCode;

    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 数量
     */
    private Integer count;

    /**
     * 支付时间
     */
    private LocalDateTime payTime;

    /**
     * 支付类型
     */
    private String payType;

    /**
     * 用户留言
     */
    private String userMessage;

    /**
     * 发货时间
     */
    private LocalDateTime deliveryTime;

    /**
     * 确认时间
     */
    private LocalDateTime confirmTime;

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

    /**
     * user name
     */
    private String userName;

    /**
     * connect
     */
    private String userConnect;

    /**
     * name
     */
    private String name;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
