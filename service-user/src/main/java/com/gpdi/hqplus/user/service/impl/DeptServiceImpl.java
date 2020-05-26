package com.gpdi.hqplus.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gpdi.hqplus.user.entity.Dept;
import com.gpdi.hqplus.user.mapper.DeptMapper;
import com.gpdi.hqplus.user.service.IDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 部门 服务实现类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-01
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

}
