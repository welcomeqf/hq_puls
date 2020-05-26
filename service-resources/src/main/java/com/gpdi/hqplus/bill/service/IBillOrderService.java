package com.gpdi.hqplus.bill.service;

import com.gpdi.hqplus.bill.entity.BillOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gpdi.hqplus.bill.entity.query.BillOrderQuery;

import java.time.LocalDateTime;

/**
 * <p>
 * 
账单 服务类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-08
 */
public interface IBillOrderService extends IService<BillOrder> {
    void addBill(BillOrderQuery query);

    void removeBill(Long id);

    void updateBill();

}
