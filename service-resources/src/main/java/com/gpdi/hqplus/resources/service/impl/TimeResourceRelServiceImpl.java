package com.gpdi.hqplus.resources.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.util.CollectionUtil;
import com.gpdi.hqplus.common.util.IdGenerator;
import com.gpdi.hqplus.common.util.JsonUtil;
import com.gpdi.hqplus.meeting.entity.vo.TimeBitVO;
import com.gpdi.hqplus.meeting.validate.MeetingValidate;
import com.gpdi.hqplus.resources.entity.TimeResourceRel;
import com.gpdi.hqplus.resources.mapper.TimeResourceRelMapper;
import com.gpdi.hqplus.resources.service.ITimeResourceRelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * <p>
 * 商品资源 服务实现类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-02
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class TimeResourceRelServiceImpl extends ServiceImpl<TimeResourceRelMapper, TimeResourceRel> implements ITimeResourceRelService {

    @Autowired
    private IdGenerator idGenerator;

    @Override
    public Integer[] getByResIdDate(Long id, LocalDate date) {
        TimeResourceRel timeResourceRel = baseMapper.selectOne(new QueryWrapper<TimeResourceRel>()
                .lambda()
                .eq(TimeResourceRel::getResourceId, id)
                .eq(TimeResourceRel::getReDate, date)
                .eq(TimeResourceRel::getStatus, TimeResourceRel.STATUS_NORMAL)
        );
        if (timeResourceRel == null) {
            // 48位
            return getNewTimeByte();
        }

        return JsonUtil.json2Bean(timeResourceRel.getVal(), Integer[].class);
    }

    @Override
    public Map<Long, Integer[]> listByResIdDate(Set<Long> ids, LocalDate date) {
        Map<Long, Integer[]> map = CollectionUtil.createHashMap();
        List<TimeResourceRel> list = baseMapper.selectList(new QueryWrapper<TimeResourceRel>()
                .lambda()
                .in(TimeResourceRel::getResourceId, ids)
                .eq(TimeResourceRel::getReDate, date)
                .eq(TimeResourceRel::getStatus, TimeResourceRel.STATUS_NORMAL)
        );

        if (CollectionUtil.isNotEmpty(list)) {
            for (TimeResourceRel timeResourceRel : list) {
                map.put(timeResourceRel.getResourceId(), JsonUtil.json2Bean(timeResourceRel.getVal(), Integer[].class));
            }
        }

        for (Long id : ids) {
            Integer[] integers = map.get(id);
            if (integers == null) {
                map.put(id, getNewTimeByte());
            }
        }

        return map;
    }

    @Override
    public void updateByResIdDate(Long id, LocalDate date, int[] target) {
        int[] timeResource;

        TimeResourceRel timeResourceRel = baseMapper.selectOne(new QueryWrapper<TimeResourceRel>()
                .lambda()
                .eq(TimeResourceRel::getResourceId, id)
                .eq(TimeResourceRel::getReDate, date)
                .eq(TimeResourceRel::getStatus, TimeResourceRel.STATUS_NORMAL)
        );
        if (timeResourceRel == null) {
            timeResourceRel = new TimeResourceRel();
            timeResourceRel.setId(idGenerator.getNumberId());
            timeResourceRel.setResourceId(id);
            timeResourceRel.setReDate(date);
            timeResourceRel.setStatus(TimeResourceRel.STATUS_NORMAL);

            timeResource = new int[48];
        } else {
            timeResource = JsonUtil.json2Bean(timeResourceRel.getVal(), int[].class);
        }

        MeetingValidate.checkTimeResource(timeResource, target);

        for (int i = 0; i < target.length; i++) {
            if (target[i] != 0) {
                timeResource[i] = target[i];
            }
        }

        timeResourceRel.setVal(JsonUtil.bean2JsonString(timeResource));
        boolean update = timeResourceRel.insertOrUpdate();
        if (!update) {
            throw new ApplicationException(ErrorCode.DATABASE_ERROR, "更新时间失败");
        }
    }

    private Integer[] getNewTimeByte(){
        return new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    }
}
