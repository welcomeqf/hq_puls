package com.gpdi.hqplus.parking.entity.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.gpdi.hqplus.resources.entity.Book;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Description 停车月卡VO
 * 现停车场月卡不走流程，转为预约形式，提交后后台直接审核批准
 * 在tb_business_product内添加月卡资源记录，将其id作为resourceId存入book表（类似于注册）
 * book表中若resourceId若对应月卡，则意味着该条记录为月卡记录
 * 关于月卡的增删改查均在此类记录上进行
 * @Author wzr
 * @CreateDate 2019-07-01
 * @Time 17:04
 */
@Data
public class ParkingCardVO extends Model<Book> {

        private static final long serialVersionUID = 1L;

        /**
         * id
         */
        private Long id;

        /**
         * 类型
         */
        private String type;

        /**
         * 用户 id
         */
        private Long userId;

        /**
         * 申请人姓名(此字段不与userId对应)
         */
        private String name;

        /**
         * 申请手机号(此字段不与userId对应)
         */
        private String phone;

        /**
         * 公司 id
         */
        private Long companyId;

        /**
         * 公司名称
         */
        private String companyName;

        /**
         * 月卡状态 -1审核未通过 0审核中 1审核通过，待付款 2正常缴费中  3逾期未续费，已过期
         */
        private String flowStatus;

        /**
         * 数据状态 分为normal和disable
         */
        private String status;

        /**
         * 车牌号码
         */
        private String carNumber;

        /**
         * 申请时间
         */
        @JSONField(format="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime applyTime;

        /**
         * 过期时间
         */
        @JSONField(format="yyyy-MM-dd")
        private LocalDate expireTime;

        /**
         * 续费时长 只用来读取入参
         */
        private Integer timePeriod;
}
