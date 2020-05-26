package com.gpdi.hqplus.pay.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 微信生成预付单参数记录表
 * </p>
 *
 * @author zake
 * @since 2019-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_wechat_create_prepay")
public class WechatCreatePrepay extends Model<WechatCreatePrepay> {

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
     * 主键
     */
    private Long id;

    /**
     * 状态
     */
    private String status;

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
     * 应用id
     */
    private String appId;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 设备号
     */
    private String deviceInfo;

    /**
     * 商品描述
     */
    private String body;

    /**
     * 回调地址
     */
    private String notifyUrl;

    /**
     * 终端ip
     */
    private String spbillCreateIp;

    /**
     * 交易类型
     */
    private String tradeType;

    /**
     * 扩展字段
     */
    private String extendVal;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
