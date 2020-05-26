package com.gpdi.hqplus.resources.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 房型
 * </p>
 *
 * @author lianghb
 * @since 2019-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_property_room_type")
public class PropertyRoomType extends Model<PropertyRoomType> {

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

    private String name;

    private String code;

    private String type;

    private String businessCode;

    private String description;

    private String imgSrc;

    private String toward;

    private Integer people;

    /**
     * 扩展类型
     */
    private String extendVal;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    private String projectCode;

    private Integer version;

    /**
     * 状态
     */
    private String status;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
