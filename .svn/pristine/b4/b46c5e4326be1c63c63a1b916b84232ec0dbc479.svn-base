package com.gpdi.hqplus.meeting.entity.vo;

import com.gpdi.hqplus.resources.entity.PropertyEquipment;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * app 端：会议室商品列表
 * @author: lianghb
 * @create: 2019-07-12 21:41
 **/
@Data
public class MeetingProductVO {
    /**
     * 会议室商品 id
     */
    private Long id;
    /**
     * 会议室资源 id
     */
    private Long meetingResourceId;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 价格单位
     */
    private String priceUnitCode;
    /**
     * 封面图片
     */
    private String coverImg;
    /**
     * 图片
     */
    private String[] otherImg;
    /**
     * 名称
     */
    private String name;
    /**
     * 人数
     */
    private Integer size;
    /**
     * 面积
     */
    private BigDecimal area;
    /**
     * 服务流程
     */
    private String serviceProcess;
    /**
     * 描述
     */
    private String description;
    /**
     * 详情
     */
    private String context;
    /**
     * 支付方式
     */
    private List<String> payTypes;
    /**
     * 服务开始时间
     */
    private String serviceStartTime;
    /**
     * 服务结束时间
     */
    private String serviceEndTime;
    /**
     * 服务结束时间
     */
    private String status;
    /**
     * 拥有设备
     */
    private List<PropertyEquipment> equipments;
    /**
     * 增值服务
     */
    private List<MeetingExtendProductVO> extendProduct;

    private TimeResourceVO timeBit;
}
