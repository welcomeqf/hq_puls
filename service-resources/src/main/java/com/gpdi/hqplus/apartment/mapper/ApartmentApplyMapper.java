package com.gpdi.hqplus.apartment.mapper;

import com.gpdi.hqplus.apartment.entity.ApartmentApply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gpdi.hqplus.apartment.entity.vo.ApplyManagerListVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 公寓看房预约 Mapper 接口
 * </p>
 *
 * @author lianghb
 * @since 2019-07-20
 */
public interface ApartmentApplyMapper extends BaseMapper<ApartmentApply> {

    Integer pageManagerCount(Map<String,Object> map);

    List<ApplyManagerListVO> pageManager(Map<String,Object> map);
}
