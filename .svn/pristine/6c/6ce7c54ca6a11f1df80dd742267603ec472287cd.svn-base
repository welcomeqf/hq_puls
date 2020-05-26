package com.gpdi.hqplus.complaint.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 投诉建议
 * @Author qf
 * @Date 2019/7/11
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_complaint")
public class Complaint extends Model<Complaint> {

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
     * 类型
     */
    private String type;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户联系方式
     */
    private String userConnect;

    /**
     * 留言消息
     */
    private String message;

    /**
     *处理人id
     */
    private Long dealUserId;

    /**
     *处理人反馈
     */
    private String feedBack;

    /**
     * 父亲id
     */
    private Long parentId;

    /**
     * 状态
     */
    private String status;

    /**
     * 版本
     */
    private Integer version;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 多租户代号
     */
    private String projectCode;

    /**
     * 任务id
     */
    private String taskId;

    /**
     * 存储图片id
     */
    private String images;

    /**
     * 业务部门
     */
    private String businessDept;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 是否超时
     */
    private String overtime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

