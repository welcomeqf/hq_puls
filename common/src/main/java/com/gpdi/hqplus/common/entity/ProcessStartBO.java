package com.gpdi.hqplus.common.entity;

import lombok.Data;
import org.springframework.security.core.context.SecurityContext;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 流程启动
 *
 * @author: lianghb
 * @create: 2019-06-30 12:22
 **/
@Data
public class ProcessStartBO implements Serializable {
    private String businessCode;
    private String definitionKey;
    private Long businessKey;
    private String taskType;
    private String name;
    private String userRedisKey;
    private String processPointCode;
    private String callbackQueueName;
    private SecurityContext securityContext;
    private Map<String, Object> variables = new HashMap<>();
    private String projectCode;
}
