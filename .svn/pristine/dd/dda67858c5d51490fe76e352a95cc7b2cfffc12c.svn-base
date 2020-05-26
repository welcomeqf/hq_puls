package com.gpdi.hqplus.pay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gpdi.hqplus.pay.constans.PayStatusEnum;
import com.gpdi.hqplus.pay.entity.PaymentLog;
import com.gpdi.hqplus.pay.mapper.PaymentLogMapper;
import com.gpdi.hqplus.pay.service.IPaymentLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author zake
 * @since 2019-07-17
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class PaymentLogServiceImpl extends ServiceImpl<PaymentLogMapper, PaymentLog> implements IPaymentLogService {

    @Override
    public PaymentLog searctStatus(String orderId) {
        PaymentLog paymentLog = baseMapper.selectOne(new QueryWrapper<PaymentLog>()
                .lambda()
                .eq(PaymentLog::getOrderId, orderId)
                .eq(PaymentLog::getStatus, PayStatusEnum.PAY_STATUS_SUCCESS.getStatus()));
        return paymentLog;
    }
}
