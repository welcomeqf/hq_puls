package com.gpdi.hqplus.order.service.impl;

import com.gpdi.hqplus.order.entity.ProductOrderDetail;
import com.gpdi.hqplus.order.mapper.ProductOrderDetailMapper;
import com.gpdi.hqplus.order.service.IProductOrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-02
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductOrderDetailServiceImpl extends ServiceImpl<ProductOrderDetailMapper, ProductOrderDetail> implements IProductOrderDetailService {

}
