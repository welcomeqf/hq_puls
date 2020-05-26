package com.gpdi.hqplus.apartment.validate;

import com.gpdi.hqplus.apartment.entity.query.RoomQuery;
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
public class ApartmentRoomValidate {
    private ApartmentRoomValidate() {
    }

    public static void checkAdd(RoomQuery query) {
        if (query.getSeriesId() == null) {
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR, "系列不能为空");
        }
        if (StringUtil.isBlank(query.getName())) {
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR, "名称不能为空");
        }
        if (query.getMinPrice() == null || query.getMaxPrice() == null) {
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR, "价格不能为空");
        }
        if (query.getMinPrice().doubleValue() > query.getMaxPrice().doubleValue()) {
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR, "最小价格不能比最大价格高");
        }
        if (query.getArea() == null) {
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR, "面积不能为空");
        }
    }

    public static void checkUpdate(RoomQuery query) {
        if (query.getId() == null) {
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR, "id不能为空");
        }
        checkAdd(query);
    }
}
