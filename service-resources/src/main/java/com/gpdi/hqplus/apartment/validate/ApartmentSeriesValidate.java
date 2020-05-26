package com.gpdi.hqplus.apartment.validate;

import com.gpdi.hqplus.apartment.entity.query.SeriesQuery;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.util.ArrayUtil;
import com.gpdi.hqplus.common.util.CollectionUtil;
import com.gpdi.hqplus.common.util.StringUtil;

/**
 * @author: lianghb
 * @create: 2019-07-20 15:30
 **/
public class ApartmentSeriesValidate {
    private ApartmentSeriesValidate() {
    }

    public static void checkAdd(SeriesQuery query) {
        if (StringUtil.isBlank(query.getName())) {
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR, "名称不能为空");
        }
    }

    public static void checkUpdate(SeriesQuery query) {
        if(query.getId()==null){
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR, "id不能为空");
        }
        checkAdd(query);
    }
}
