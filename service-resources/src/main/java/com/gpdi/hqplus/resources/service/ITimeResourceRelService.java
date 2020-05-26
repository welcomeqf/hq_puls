package com.gpdi.hqplus.resources.service;

import com.gpdi.hqplus.meeting.entity.vo.TimeBitVO;
import com.gpdi.hqplus.resources.entity.TimeResourceRel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 
商品资源 服务类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-02
 */
public interface ITimeResourceRelService extends IService<TimeResourceRel> {
    /**
     * 根据商品 id，时间，获取时间资源池
     * @param id
     * @param date
     * @return
     */
    Integer[] getByResIdDate(Long id, LocalDate date);

    Map<Long, Integer[]> listByResIdDate(Set<Long> id, LocalDate date);

    /**
     * 更新时间资源
     * @param id
     * @param date
     * @param target
     */
    void updateByResIdDate(Long id, LocalDate date,int[] target);
}
