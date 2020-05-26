package com.gpdi.hqplus.resources.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gpdi.hqplus.resources.entity.PropertyApply;
import com.gpdi.hqplus.resources.mapper.PropertyApplyMapper;
import com.gpdi.hqplus.resources.service.IPropertyApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 物业申请表 服务实现类
 * </p>
 *
 * @author liuJiaHui
 * @since 2019-07-17
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class PropertyApplyServiceImpl extends ServiceImpl<PropertyApplyMapper, PropertyApply> implements IPropertyApplyService {

}
