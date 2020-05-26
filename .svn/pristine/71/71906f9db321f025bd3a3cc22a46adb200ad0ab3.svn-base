package com.gpdi.hqplus.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 流程回调
 *
 * @author: lianghb
 * @param
 * @create: 2019-07-03 17:59
 **/
@Data
public class ProcessCallbackBO implements Serializable {
    /**
     * businessCode 业务代码 BusinessCode.XXXX
     */
    private String businessCode;
    /**
     * 启动流程定义的key
     */
    private String definitionKey;
    /**
     * 相当于主键
     */
    private Long businessKey;
    /**
     * 流程名称(备注)
     */
    private String name;
    /**
     * 流程节点名称 相当于status
     */
    private String processPointCode;
    /**
     * 用户的redisKey
     */
    private String userRedisKey;
    /**
     * 备用参数
     */
    private Map<String, Object> variables;
    /**
     * 多租户代号
     */
    private String projectCode;
    /**
     * 任务Id
     */
    private String taskId;
}
