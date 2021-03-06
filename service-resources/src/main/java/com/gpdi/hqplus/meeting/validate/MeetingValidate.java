package com.gpdi.hqplus.meeting.validate;

import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.util.ArrayUtil;
import com.gpdi.hqplus.common.util.StringUtil;
import com.gpdi.hqplus.common.validate.PageQueryValidate;
import com.gpdi.hqplus.meeting.entity.query.MeetingAddQuery;
import com.gpdi.hqplus.meeting.entity.query.MeetingBookQuery;
import com.gpdi.hqplus.resources.entity.ProductResource;
import com.gpdi.hqplus.resources.entity.query.ProductListQuery;
import com.gpdi.hqplus.resources.entity.query.PropertyQuery;

import javax.print.attribute.standard.MediaSize;

/**
 * @author: lianghb
 * @create: 2019-07-16 15:17
 **/
public class MeetingValidate {
    private MeetingValidate() {
    }

    public static void checkUpdateStatus(MeetingAddQuery query) {
        if (ArrayUtil.isEmpty(query.getIds())) {
            throw new ApplicationException(ErrorCode.RESOURCES_EXISTING, "id不能为空");
        }

        if (query.getStatus() == null ||
                !(ProductResource.STATUS_NORMAL.equals(query.getStatus()) || ProductResource.STATUS_DISABLED.equals(query.getStatus()))) {
            throw new ApplicationException(ErrorCode.RESOURCES_EXISTING, "状态错误");
        }
    }

    public static void checkPageProduct(ProductListQuery query) {
        PageQueryValidate.check(query);
        if (query.getDate() == null) {
            throw new ApplicationException(ErrorCode.RESOURCES_EXISTING, "日期不能为空");
        }
    }

    public static void checkAdd(MeetingAddQuery query) {
        if (StringUtil.isBlank(query.getName())) {
            throw new ApplicationException(ErrorCode.RESOURCES_EXISTING, "名称不能为空");
        }
        if (StringUtil.isBlank(query.getAddress())) {
            throw new ApplicationException(ErrorCode.RESOURCES_EXISTING, "地址不能为空");
        }
        if (query.getArea() == null) {
            throw new ApplicationException(ErrorCode.RESOURCES_EXISTING, "面积不能为空");
        }
        if (query.getFloor() == null) {
            throw new ApplicationException(ErrorCode.RESOURCES_EXISTING, "楼层不能为空");
        }
        if (query.getSize() == null) {
            throw new ApplicationException(ErrorCode.RESOURCES_EXISTING, "可容纳人数不能为空");
        }
        if (query.getCoverFile() == null) {
            throw new ApplicationException(ErrorCode.RESOURCES_EXISTING, "封面图片不能为空");
        }
        if (query.getPrice() == null) {
            throw new ApplicationException(ErrorCode.RESOURCES_EXISTING, "价格不能为空");
        }
    }

    public static void checkUpdate(MeetingAddQuery query) {
        if (query.getId() == null) {
            throw new ApplicationException(ErrorCode.RESOURCES_EXISTING, "id不能为空");
        }
        checkAdd(query);
    }

    public static void checkBookQuery(MeetingBookQuery query) {
        if (query.getProductId() == null) {
            throw new ApplicationException(ErrorCode.RESOURCES_EXISTING, "会议室 id 不能为空");
        }
        if (query.getDate() == null) {
            throw new ApplicationException(ErrorCode.RESOURCES_EXISTING, "日期不能为空");
        }
        if (query.getTime() == null || query.getTime().length == 0) {
            throw new ApplicationException(ErrorCode.RESOURCES_EXISTING, "时间不能为空或格式错误");
        }
        if (StringUtil.isBlank(query.getPayType())) {
            throw new ApplicationException(ErrorCode.RESOURCES_EXISTING, "支付类型不能为空");
        }
    }

    public static void checkTimeResource(int[] source, int[] target) {
        for (int i = 0; i < source.length; i++) {
            if (source[i] != 0 && target[i] != 0) {
                int time = i / 2;
                throw new ApplicationException(ErrorCode.RESOURCES_EXISTING, time + ":00 已预订");
            }
        }
    }
}
