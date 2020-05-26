package com.gpdi.hqplus.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.bill.constants.PayTypeEnum;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.entity.UserContext;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.util.IdGenerator;
import com.gpdi.hqplus.common.util.StringUtil;
import com.gpdi.hqplus.order.constants.OrderStatusEnum;
import com.gpdi.hqplus.order.entity.ProductOrder;
import com.gpdi.hqplus.order.entity.ProductOrderDetail;
import com.gpdi.hqplus.resources.entity.ProductResource;
import com.gpdi.hqplus.order.entity.query.OrderListQuery;
import com.gpdi.hqplus.order.entity.query.OrderQuery;
import com.gpdi.hqplus.order.mapper.ProductOrderMapper;
import com.gpdi.hqplus.order.service.IProductOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gpdi.hqplus.resources.service.IProductResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-02
 */
@Slf4j
@Service("productOrderServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class ProductOrderServiceImpl extends ServiceImpl<ProductOrderMapper, ProductOrder> implements IProductOrderService {

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    @Qualifier("productResourceServiceImpl")
    private IProductResourceService productResourceService;

    @Override
    public Page<ProductOrder> page(OrderListQuery query) {
        Page<ProductOrder> page = new Page<>();
        page.setCurrent(query.getCurrent());
        page.setSize(query.getSize());

        LambdaQueryWrapper<ProductOrder> queryWrapper = new QueryWrapper<ProductOrder>().lambda()
                .orderByDesc(ProductOrder::getId);
        setQueryParam(queryWrapper, query);

        baseMapper.selectPage(page,queryWrapper);

        return page;
    }

    @Override
    public void add(OrderQuery query) {
        query.setId(idGenerator.getNumberId())
                .setOrderCode(idGenerator.getOrderCode())
                .setUserId(UserContext.get().getId())
                .setStatus(OrderStatusEnum.CREATE.getCode());

        BigDecimal amount = new BigDecimal("0");
        int count = 0;

        // 订单详情处理
        List<ProductOrderDetail> details = query.getDetails();
        for (ProductOrderDetail detail : details) {
            ProductResource productResource = productResourceService.getById(detail.getProductId());
            if (productResource == null) {
                throw new ApplicationException(ErrorCode.RESOURCES_NOT_FIND, "查询商品信息失败");
            }

            BigDecimal detailAmount = productResource.getPrice().multiply(new BigDecimal(detail.getCount().toString()));

            // 订单详情记录
            detail.setId(idGenerator.getNumberId())
                    .setOrderCode(query.getOrderCode())
                    .setAmount(detailAmount)
                    .setStatus(ProductOrderDetail.STATUS_NORMAL);

            amount = amount.add(detailAmount);
            count += detail.getCount();
        }

        query.setAmount(amount);
        query.setCount(count);

        boolean insert = query.insert();
        if (!insert) {
            log.error("insert order fail");
            throw new ApplicationException(ErrorCode.DATABASE_ERROR, "生成订单信息失败");
        }

        for (ProductOrderDetail detail : details) {
            boolean detailInsert = detail.insert();
            if (!detailInsert) {
                log.error("insert order detail fail");
                throw new ApplicationException(ErrorCode.DATABASE_ERROR, "生成订单信息失败");
            }
        }

        if (PayTypeEnum.ONCE.getCode().equals(query.getPayType())) {
            // todo 添加账单支付单
        }
    }

    @Override
    public void cancel(Long orderId) {
        ProductOrder productOrder = baseMapper.selectById(orderId);
        if (productOrder == null) {
            throw new ApplicationException(ErrorCode.RESOURCES_NOT_FIND, "查询订单信息失败");
        }

        productOrder.setStatus(OrderStatusEnum.CANCEL.getCode());

        boolean update = productOrder.updateById();
        if (!update) {
            log.error("update order fail");
            throw new ApplicationException(ErrorCode.DATABASE_ERROR, "取消订单失败");
        }

        // todo 取消账单
        // todo 取消支付单
    }

    @Override
    public void delete(Long orderId) {
        ProductOrder productOrder = baseMapper.selectById(orderId);
        if (productOrder == null) {
            throw new ApplicationException(ErrorCode.RESOURCES_NOT_FIND, "查询订单信息失败");
        }

        // todo 添加删除逻辑 什么状态的订单可删除？？

        boolean delete = productOrder.deleteById();
        if (!delete) {
            log.error("delete order fail");
            throw new ApplicationException(ErrorCode.DATABASE_ERROR, "删除订单失败");
        }
    }

    @Override
    public void update(OrderQuery query) {
        ProductOrder productOrder = baseMapper.selectById(query.getId());
        if (productOrder == null) {
            throw new ApplicationException(ErrorCode.RESOURCES_NOT_FIND, "查询订单信息失败");
        }

        // 金额
        if (query.getAmount() != null) {
            productOrder.setAmount(query.getAmount());
        }
        // 发货时间
        if (query.getDeliveryTime() != null) {
            productOrder.setDeliveryTime(query.getDeliveryTime());
        }
        // 确认时间
        if (query.getConfirmTime() != null) {
            productOrder.setConfirmTime(query.getConfirmTime());
        }
        // 支付时间
        if (query.getPayTime() != null) {
            productOrder.setPayTime(query.getPayTime());
        }

        boolean update = productOrder.updateById();
        if (!update) {
            log.error("update order fail");
            throw new ApplicationException(ErrorCode.DATABASE_ERROR, "更新订单失败");
        }
    }

    @Override
    public ProductOrder getByOrderCode(String orderCode) {
        return baseMapper.selectOne(new QueryWrapper<ProductOrder>()
                .lambda()
                .eq(ProductOrder::getOrderCode, orderCode)
        );
    }

    private void setQueryParam(LambdaQueryWrapper<ProductOrder> queryWrapper, OrderListQuery query) {
        if (StringUtil.isNotBlank(query.getBusinessCode())) {
            queryWrapper.eq(ProductOrder::getBusinessCode, query.getBusinessCode());
        }
        if (StringUtil.isNotBlank(query.getStatus())) {
            queryWrapper.eq(ProductOrder::getStatus, query.getStatus());
        }
        if (query.getUserId() != null) {
            queryWrapper.eq(ProductOrder::getUserId, query.getUserId());
        }
        if (StringUtil.isNotBlank(query.getName())) {
            queryWrapper.like(ProductOrder::getName, query.getName());
        }
        if (StringUtil.isNotBlank(query.getUserName())) {
            queryWrapper.like(ProductOrder::getUserName, query.getUserName());
        }
        if (StringUtil.isNotBlank(query.getUserConnect())) {
            queryWrapper.like(ProductOrder::getUserConnect, query.getUserConnect());
        }
        if (query.getMinPrice() != null) {
            queryWrapper.ge(ProductOrder::getAmount,query.getMinPrice());
        }
        if (query.getMaxPrice() != null) {
            queryWrapper.le(ProductOrder::getAmount,query.getMaxPrice());
        }
        if (query.getStartCreateTime() != null) {
            queryWrapper.ge(ProductOrder::getCreateTime,query.getStartCreateTime());
        }
        if (query.getEndCreateTime() != null) {
            queryWrapper.le(ProductOrder::getCreateTime,query.getEndCreateTime());
        }
    }
}
