package com.gpdi.hqplus.meeting.entity.query;

import com.gpdi.hqplus.resources.entity.PropertyResource;
import lombok.Data;

/**
 * @author: lianghb
 * @create: 2019-07-12 22:31
 **/
@Data
public class MeetingQuery extends PropertyResource {
    private Long[] ids;
}
