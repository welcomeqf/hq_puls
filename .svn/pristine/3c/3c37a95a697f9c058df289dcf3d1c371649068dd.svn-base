package com.gpdi.hqplus.bill.service.impl;

import com.gpdi.hqplus.bill.constants.PayTypeEnum;
import com.gpdi.hqplus.bill.entity.BillOrder;
import com.gpdi.hqplus.bill.entity.query.BillOrderQuery;
import com.gpdi.hqplus.bill.mapper.BillOrderMapper;
import com.gpdi.hqplus.bill.service.IBillOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gpdi.hqplus.bill.util.PayTypeUtil;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.entity.UserContext;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.util.IdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * <p>
 * 账单 服务实现类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-08
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class BillOrderServiceImpl extends ServiceImpl<BillOrderMapper, BillOrder> implements IBillOrderService {

    @Autowired
    private IdGenerator idGenerator;

    /**
     * 账单
     * 用户购买商品下单，生成订单数据后，根据订单数据，生成用户账单
     *
     * @param query
     */
    @Override
    public void addBill(BillOrderQuery query) {
        query.setId(idGenerator.getNumberId());
        query.setUserId(UserContext.get().getId());
        query.setStatus(BillOrder.STATUS_NORMAL);

        PayTypeEnum payTypeEnum = PayTypeUtil.getByCode(query.getPayType());
        if (payTypeEnum == null) {
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR, "支付类型不正确");
        }

        boolean insert = query.insert();
        if (!insert) {
            log.error("insert bill order fail");
            throw new ApplicationException(ErrorCode.DATABASE_ERROR, "添加账单信息失败");
        }

        // todo 生成支付单
    }

    @Override
    public void removeBill(Long id) {
        int delete = baseMapper.deleteById(id);
        if (delete < 1) {
            throw new ApplicationException(ErrorCode.DATABASE_ERROR, "删除账单信息失败");
        }
    }

    @Override
    public void updateBill() {

    }
}
