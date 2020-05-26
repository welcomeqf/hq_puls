package com.gpdi.hqplus.message.controller.app;

import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.constants.RedisConstants;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.manager.RedisService;
import com.gpdi.hqplus.common.util.StringUtil;
import com.gpdi.hqplus.message.entity.query.PhoneCodeQuery;
import com.gpdi.hqplus.message.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * App端接口，包括ios和安卓
 * 如需要区分ios和安卓，可在uri中继续加以区分
 *
 * @author: lianghb
 * @create: 2019-06-03 14:36
 **/
@Api
@Slf4j
@RestController
@RequestMapping("/v1/app/message")
public class MessageAppController {
    @Autowired
    private MessageService messageService;

    /**
     * 发送短信验证码
     *
     * @param phone
     * @return
     */
    @ApiOperation(value = "发送短信验证码", notes = "发送短信验证码")
    @ApiImplicitParam(name = "phone", value = "phone", required = true, dataType = "String", paramType = "path")
    @GetMapping("/sendForRegister")
    public String sendMessage(String phone) {
        boolean regPhone = StringUtil.regPhone(phone);
        if (!regPhone) {
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR, "手机号不正确");
        }
        messageService.sendMessage(RedisConstants.SMS_KEY_REGISTER, phone);
        return "ok";
    }

    /**
     * 验证短信验证码
     *
     * @param query
     * @return
     */
    @ApiOperation(value = "验证短信验证码", notes = "验证短信验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "phone", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "code", value = "code", required = true, dataType = "String", paramType = "path")
    })
    @GetMapping("/checkForRegister")
    public Boolean checkCode(@RequestBody PhoneCodeQuery query) {
        boolean regPhone = StringUtil.regPhone(query.getPhone());
        if (!regPhone) {
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR, "手机号不正确");
        }
        return messageService.checkCode(RedisConstants.SMS_KEY_REGISTER, query.getPhone(), query.getCode());
    }
}
