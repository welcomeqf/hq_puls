package com.gpdi.hqplus.resources.validate;

import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.resources.entity.query.TimeResourceQuery;

import java.awt.image.VolatileImage;

/**
 * @author: lianghb
 * @create: 2019-07-15 17:17
 **/
public class TimeResourceValidate {
    private TimeResourceValidate(){}

    public static void checkTimeResourceQuery(TimeResourceQuery query){
        if(query==null||query.getId()==null||query.getDate()==null){
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR,"id日期不能为空");
        }
    }
}
