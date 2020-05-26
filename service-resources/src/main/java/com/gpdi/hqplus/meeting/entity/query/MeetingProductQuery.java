package com.gpdi.hqplus.meeting.entity.query;

import com.gpdi.hqplus.resources.entity.ProductResource;
import lombok.Data;

/**
 * @author: lianghb
 * @create: 2019-07-13 10:56
 **/
@Data
public class MeetingProductQuery extends ProductResource {
    /**
     * 封面图片
     */
    private String coverImg;
    /**
     * 增值服务
     */
    private Long[] extendProduct;
}
