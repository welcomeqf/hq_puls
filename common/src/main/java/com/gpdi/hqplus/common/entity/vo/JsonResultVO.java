package com.gpdi.hqplus.common.entity.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.util.JsonUtil;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 全局统一返回对象
 *
 * @author: lianghb
 * @create: 2019-05-22 15:11
 **/
@Data
public class JsonResultVO<T> implements Serializable {
    /**
     * 状态码
     */
    private int code;
    /**
     * 信息
     */
    private String msg;
    /**
     * 数据
     */
    private T data;
    /**
     * 响应时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time = LocalDateTime.now();

    public static JsonResultVO success() {
        JsonResultVO vo = new JsonResultVO();
        vo.setCode(0);
        vo.setMsg("ok");
        return vo;
    }

    public static<T> JsonResultVO success(T data) {
        JsonResultVO vo = success();
        vo.setData(data);
        return vo;
    }

    public static JsonResultVO fail() {
        JsonResultVO vo = new JsonResultVO();
        vo.setCode(ErrorCode.UNKNOWN_ERROR.getCode());
        vo.setMsg("fail");
        return vo;
    }

    public String toJSON() {
        return JsonUtil.bean2JsonString(this);
    }
}
