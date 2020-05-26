package com.gpdi.hqplus.resources.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 物业申请表
 * </p>
 *
 * @author liuJiaHui
 * @since 2019-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_property_apply")
public class PropertyApply extends Model<PropertyApply> {

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

    private String businessCode;

    private String userName;

    private String userConnect;

    private Long userId;

    private String address;

    private String userMessage;

    private String feedback;

    private String status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer version;

    private String projectCode;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
