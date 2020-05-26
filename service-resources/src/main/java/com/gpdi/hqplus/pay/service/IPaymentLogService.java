package com.gpdi.hqplus.pay.service;

import com.gpdi.hqplus.pay.entity.PaymentLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author zake
 * @since 2019-07-17
 */
public interface IPaymentLogService extends IService<PaymentLog> {
    /**
     * 根据订单的id 查询出当前订单的记录
     * @param orderId
     * @return
     */
    PaymentLog searctStatus(String orderId);
}
