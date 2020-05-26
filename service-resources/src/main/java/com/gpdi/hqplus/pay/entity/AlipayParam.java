package com.gpdi.hqplus.pay.entity;


import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author: zake
 * @Description:
 * @Date: Created in 19:11 2019-07-01
 **/
@Data
public class AlipayParam implements Serializable {
    @NotNull(message = "金额不能为空")
    private String amount;
    @NotNull(message = "订单名称不能为空")
    private String orderName;
    @NotNull(message = "订单号不能为空")
    private String orderId;
}
