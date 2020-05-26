package com.gpdi.hqplus.resources.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
文章资源
 * </p>
 *
 * @author lianghb
 * @since 2019-07-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_article_resource")
public class ArticleResource extends Model<ArticleResource> {

    private static final long serialVersionUID = 1L;

    /**
     * 状态：正常可用
     */
    public static String STATUS_NORMAL = "normal";

    /**
     * 状态：禁用
     */
    public static String STATUS_DISABLED = "disabled";


    private Long id;

    /**
     * 业务代号
     */
    private String businessCode;

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
     * 作者
     */
    private Long creator;

    /**
     * 地址
     */
    private String address;

    /**
     * 链接
     */
    private String linkUrl;

    /**
     * 阅读数
     */
    private Integer readCount;

    /**
     * 状态
     */
    private String status;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    /**
     * 多租户代号
     */
    private String projectCode;

    /**
     * 序号
     */
    private Integer serialNumber;

    /**
     * 描述
     */
    private String description;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
