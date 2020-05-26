package com.gpdi.hqplus.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.order.entity.ProductOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gpdi.hqplus.order.entity.query.OrderListQuery;
import com.gpdi.hqplus.order.entity.query.OrderQuery;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-02
 */
public interface IProductOrderService extends IService<ProductOrder> {
    /**
     * 分页查询
     *
     * @param query
     * @return
     */
    Page<ProductOrder> page(OrderListQuery query);

    /**
     * 添加订单
     *
     * @param query
     */
    void add(OrderQuery query);

    /**
     * 取消订单
     *
     * @param orderId
     */
    void cancel(Long orderId);

    /**
     * 删除订单
     *
     * @param orderId
     */
    void delete(Long orderId);

    /**
     * 更新订单
     *
     * @param query
     */
    void update(OrderQuery query);

    /**
     * 通过订单号获取订单数据
     * @param orderCode
     * @return
     */
    ProductOrder getByOrderCode(String orderCode);
}
