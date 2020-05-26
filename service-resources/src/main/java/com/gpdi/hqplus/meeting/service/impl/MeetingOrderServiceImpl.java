package com.gpdi.hqplus.meeting.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.common.constants.BusinessCode;
import com.gpdi.hqplus.common.util.BeanPowerHelper;
import com.gpdi.hqplus.common.util.CollectionUtil;
import com.gpdi.hqplus.meeting.entity.vo.MeetingOrderListVO;
import com.gpdi.hqplus.meeting.service.IMeetingOrderService;
import com.gpdi.hqplus.order.entity.ProductOrder;
import com.gpdi.hqplus.order.entity.query.OrderListQuery;
import com.gpdi.hqplus.order.service.IProductOrderService;
import com.gpdi.hqplus.order.service.impl.ProductOrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: lianghb
 * @create: 2019-07-18 19:56
 **/
@Slf4j
@Service("meetingOrderServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class MeetingOrderServiceImpl implements IMeetingOrderService {

    @Autowired
    private IProductOrderService productOrderService;

    @Override
    public Page<MeetingOrderListVO> pageOrder(OrderListQuery query) {
        query.setBusinessCode(BusinessCode.PROPERTY_MEETING_ROOM);
        Page<ProductOrder> page = productOrderService.page(query);

        List<MeetingOrderListVO> result = CollectionUtil.createArrayList();
        if (CollectionUtil.isNotEmpty(page.getRecords())) {
            for (ProductOrder record : page.getRecords()) {
                result.add(productOrder2ListVO(record));
            }
        }

        return BeanPowerHelper.mapPage(page, result);
    }

    private MeetingOrderListVO productOrder2ListVO(ProductOrder productOrder) {
        MeetingOrderListVO vo = new MeetingOrderListVO();
        BeanUtils.copyProperties(productOrder,vo);
        return vo;
    }
}
