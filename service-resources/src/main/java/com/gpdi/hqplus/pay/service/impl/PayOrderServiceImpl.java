package com.gpdi.hqplus.pay.service.impl;

import com.gpdi.hqplus.pay.entity.PayOrder;
import com.gpdi.hqplus.pay.mapper.PayOrderMapper;
import com.gpdi.hqplus.pay.service.IPayOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 支付单 服务实现类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-09
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class PayOrderServiceImpl extends ServiceImpl<PayOrderMapper, PayOrder> implements IPayOrderService {

}
