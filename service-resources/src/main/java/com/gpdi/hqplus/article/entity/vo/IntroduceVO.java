package com.gpdi.hqplus.article.entity.vo;

import lombok.Data;
/**
 * 园区介绍
 *
 * @author liujiahui
 * @since 2019-07-05
 */
@Data
public class IntroduceVO {
    private Long id;
    /**
     * 类型
     */
    private String type;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String context;

    /**
     * 地址
     */
    private String address;

    /**
     * 链接
     */
    private String linkUrl;
}
