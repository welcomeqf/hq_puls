package com.gpdi.hqplus.apartment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.apartment.entity.ApartmentApply;
import com.gpdi.hqplus.apartment.entity.ApartmentSeries;
import com.gpdi.hqplus.apartment.entity.query.ApplyPageQuery;
import com.gpdi.hqplus.apartment.entity.query.ApplyQuery;
import com.gpdi.hqplus.apartment.entity.vo.ApplyManagerListVO;
import com.gpdi.hqplus.apartment.entity.vo.SeriesManagerListVO;
import com.gpdi.hqplus.apartment.mapper.ApartmentApplyMapper;
import com.gpdi.hqplus.apartment.service.IApartmentApplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.entity.UserContext;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 公寓看房预约 服务实现类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-20
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ApartmentApplyServiceImpl extends ServiceImpl<ApartmentApplyMapper, ApartmentApply> implements IApartmentApplyService {

    @Autowired
    private IdGenerator idGenerator;

    @Override
    public void apply(ApartmentApply apply) {
        apply.setId(idGenerator.getNumberId());
        apply.setUserId(UserContext.get().getId());
        apply.setStatus(ApartmentApply.STATUS_NORMAL);

        boolean insert = apply.insert();
        if (!insert) {
            log.error("insert ApartmentApply fail");
            throw new ApplicationException(ErrorCode.DATABASE_ERROR, "预约失败,请稍后再试");
        }
    }

    @Override
    public Page<ApplyManagerListVO> pageManager(ApplyPageQuery query) {
        Page<ApplyManagerListVO> page = PageHelper.newInstance(query, ApplyManagerListVO.class);

        Map<String, Object> map = BeanPowerHelper.beanToMap(query);
        Integer startIndex = (query.getCurrent() - 1) * query.getSize();
        Integer endIndex = query.getCurrent() * query.getSize();
        map.put("startIndex", startIndex);
        map.put("endIndex", endIndex);

        Integer count = baseMapper.pageManagerCount(map);
        if (count == 0) {
            return page;
        }

        List<ApplyManagerListVO> list = baseMapper.pageManager(map);

        page.setRecords(list);
        page.setTotal(count);
        page.setPages(count / query.getSize() + 1);
        return page;
    }

    @Override
    public void setPrincipal(ApplyQuery query) {
        ApartmentApply apply = new ApartmentApply();
        apply.setPrincipalId(query.getPrincipalId());

        int update = baseMapper.update(apply, new QueryWrapper<ApartmentApply>()
                .lambda()
                .in(ApartmentApply::getId, query.getIds())
        );
        if (update != query.getIds().size()) {
            log.error("update ApartmentApply fail");
            throw new ApplicationException(ErrorCode.DATABASE_ERROR, "更新失败");
        }
    }

    /**
     * ApartmentApply 2 ApplyManagerListVO
     *
     * @param apply
     * @return
     */
    private ApplyManagerListVO apply2ApplyManagerListVO(ApartmentApply apply) {
        ApplyManagerListVO vo = new ApplyManagerListVO();
        BeanUtils.copyProperties(apply, vo);
        return vo;
    }
}
