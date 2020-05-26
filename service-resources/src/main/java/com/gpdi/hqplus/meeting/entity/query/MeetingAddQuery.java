package com.gpdi.hqplus.meeting.entity.query;

import com.gpdi.hqplus.resources.entity.vo.SimpleEquipmentVO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: lianghb
 * @create: 2019-07-17 15:29
 **/
@Data
public class MeetingAddQuery {
    private Long id;
    /**
     * 会议室资源 id
     */
    private Long meetingResourceId;
    /**
     * 类型
     */
    private String type;

    /**
     * 标题
     */
    private String name;

    /**
     * 描述
     */
    private String context;

    /**
     * 地址
     */
    private String address;

    /**
     * 面积
     */
    private BigDecimal area;

    /**
     * 楼层
     */
    private String floor;

    /**
     * 可容纳人数
     */
    private Integer size;

    /**
     * 设备列表
     */
    private List<SimpleEquipmentVO> equipmentList;

    /**
     * 服务开始时间
     */
    private Integer serviceStartTime;

    /**
     * 服务结束时间
     */
    private Integer serviceEndTime;


    // ----------------------------------------------------------------------------- product
    /**
     * 封面图片
     */
    private String coverFile;
    /**
     * 封面图片
     */
    private String[] otherImg;
    /**
     * 增值服务
     */
    private Long[] extendProduct;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 简介
     */
    private String description;

    private Long[] ids;
    private String status;
}
