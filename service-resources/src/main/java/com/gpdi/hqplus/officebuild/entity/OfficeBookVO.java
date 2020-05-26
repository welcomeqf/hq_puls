package com.gpdi.hqplus.officebuild.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.gpdi.hqplus.resources.entity.Book;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Description 写字楼系列预约vo
 * @Author wzr
 * @CreateDate 2019-07-10
 * @Time 14:56
 */
@Data
public class OfficeBookVO extends Model<Book> {
    /**
     * 写字楼系列预约id
     */
    private Long id;

    /**
     * 写字楼系列id
     */
    private Long officeId;

    /**
     * 预约时间
     */
    @JSONField(format="yyyy-MM-dd")
    private LocalDateTime applyTime;

    /**
     * 备注
     */
    private String message;

    /**
     * 预约人姓名
     */
    private String name;

    /**
     * 预约人手机号码
     */
    private String phone;

    /**
     * 状态
     */
    private String status;

}
