package com.gpdi.hqplus.enterprise.entity;

import lombok.Data;

import java.util.List;

/**
 * @Description 企业信息参数
 * @Author wzr
 * @CreateDate 2019-07-18
 * @Time 10:25
 */
@Data
public class EnterpriseParams extends Enterprise{
    /**
     * 企业ids，接受id集合进行批量操作
     */
    private List<Long> ids;
}
