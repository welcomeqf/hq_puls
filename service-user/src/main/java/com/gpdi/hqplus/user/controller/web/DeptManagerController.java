package com.gpdi.hqplus.user.controller.web;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lianghb
 * @create: 2019-07-01 23:41
 **/
@Api
@Slf4j
@RestController
@RequestMapping("/v1/manager/user/dept")
@PreAuthorize("ROLE_admin")
public class DeptManagerController {
}
