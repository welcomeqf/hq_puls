package com.gpdi.hqplus.meeting.entity.query;

import com.gpdi.hqplus.common.entity.BasePageQuery;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 会议室分页查询
 * @author: lianghb
 * @create: 2019-07-17 11:05
 **/
@Data
public class MeetingRoomQuery extends BasePageQuery {
    private String name;
    private String status;
    private BigDecimal minArea;
    private BigDecimal maxArea;
}
