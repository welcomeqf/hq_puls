package com.gpdi.hqplus.pass.mapper;

import com.gpdi.hqplus.pass.entity.ProductPass;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zake
 * @since 2019-07-05
 */
public interface ProductPassMapper extends BaseMapper<ProductPass> {
    /**
     *
     * changeStatus 改变前一天放行的状态
     * @param
     * @return
     */
    void changeStatus();
}
