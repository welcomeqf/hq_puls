package com.gpdi.hqplus.meeting.entity.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author: lianghb
 * @create: 2019-07-18 19:55
 **/
@Data
public class MeetingOrderListVO {
    private Long id;
    private String orderCode;
    private String name;
    private String userName;
    private String userConnect;
    private LocalDateTime createTime;
    private String status;
    private LocalDateTime payTime;
    private BigDecimal amount;
}
