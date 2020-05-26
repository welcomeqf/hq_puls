package com.gpdi.hqplus.resources.validate;

import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @Description 检查数据体必填字段是否为空
 * @Author wzr
 * @CreateDate 2019-07-01
 * @Time 17:38
 */
@Slf4j
public class CheckEmptyUtil {

    public static void check(Map map, String[]params){
        log.warn("check "+ map.toString());
        if (!map.isEmpty() && params.length>0){
            for (String param:params){
                if (!map.containsKey(param)){
                    log.error(param+"不能为空");
                    throw new ApplicationException(ErrorCode.PARAMETER_ERROR,param+"不能为空");
                }
                if (StringUtil.isEmpty(map.get(param).toString())){
                    log.error(param+"不能为空");
                    throw new ApplicationException(ErrorCode.PARAMETER_ERROR,param+"不能为空");
                }
            }
        }
    }

    private CheckEmptyUtil(){
        throw new IllegalStateException("Utility Class");
    }
}
