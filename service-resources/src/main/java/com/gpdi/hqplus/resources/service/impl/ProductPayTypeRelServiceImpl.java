package com.gpdi.hqplus.resources.service.impl;

import com.gpdi.hqplus.resources.entity.ProductPayTypeRel;
import com.gpdi.hqplus.resources.mapper.ProductPayTypeRelMapper;
import com.gpdi.hqplus.resources.service.IProductPayTypeRelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 
商品支付方式关联表 服务实现类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-02
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductPayTypeRelServiceImpl extends ServiceImpl<ProductPayTypeRelMapper, ProductPayTypeRel> implements IProductPayTypeRelService {

}
