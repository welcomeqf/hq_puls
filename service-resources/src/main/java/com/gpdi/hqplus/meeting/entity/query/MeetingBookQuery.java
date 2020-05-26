package com.gpdi.hqplus.meeting.entity.query;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

/**
 * @author: lianghb
 * @create: 2019-07-16 14:13
 **/
@Data
public class MeetingBookQuery {
    private Long productId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private int[] time;
    private String userName;
    private String userConnect;
    private List<ExtendService> extendServices;
    private String payType;

    @Data
    public class ExtendService {
        private Long productId;
        private int count;
    }
}
