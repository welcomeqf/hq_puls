package com.gpdi.hqplus.meeting.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.meeting.entity.query.MeetingAddQuery;
import com.gpdi.hqplus.meeting.entity.query.MeetingBookQuery;
import com.gpdi.hqplus.meeting.entity.query.MeetingProductQuery;
import com.gpdi.hqplus.meeting.entity.vo.MeetingProductVO;
import com.gpdi.hqplus.meeting.entity.vo.TimeBitVO;
import com.gpdi.hqplus.resources.entity.ProductResource;
import com.gpdi.hqplus.resources.entity.query.ProductListQuery;
import com.gpdi.hqplus.resources.entity.query.TimeResourceQuery;

import java.util.List;
import java.util.Map;

/**
 * 会议室商品
 * @author: lianghb
 * @create: 2019-07-12 21:38
 **/
public interface IMeetingProductService {
    /**
     * 分页获取会议室商品列表
     * @param query
     * @return
     */
    Page<MeetingProductVO> pageProduct(ProductListQuery query);

    /**
     * 添加新的产品
     * @param query
     */
    void addNewProduct(MeetingProductQuery query);

    /**
     * 获取详情
     * @param id
     * @return
     */
    MeetingProductVO getByProductId(Long id);

    /**
     * 创建订单
     * @param query
     */
    String createBookOrder(MeetingBookQuery query);

    /**
     * getById
     * @param id
     * @return
     */
    ProductResource getById(Long id);

    /**
     * 更新
     * @param query
     */
    void updateMeetingRoomProduct(MeetingAddQuery query);

    void updateStatus(MeetingAddQuery query);
}
