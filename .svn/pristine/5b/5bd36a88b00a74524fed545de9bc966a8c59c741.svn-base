package com.gpdi.hqplus.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gpdi.hqplus.article.entity.vo.IntroduceVO;
import com.gpdi.hqplus.article.service.IIntroduceService;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.util.BeanPowerHelper;
import com.gpdi.hqplus.resources.entity.ArticleResource;
import com.gpdi.hqplus.resources.mapper.ArticleResourceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 园区介绍
 *
 * @author liujiahui
 * @since 2019-07-05
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class IntroduceServiceImpl extends ServiceImpl<ArticleResourceMapper, ArticleResource> implements IIntroduceService {

    /**
     * 查看园区介绍
     *
     * @author liujiahui
     * @since 2019-07-05
     */
    @Override
    public IntroduceVO getIntroduce(String parkCode) {

        ArticleResource articleResource = baseMapper.selectOne(new QueryWrapper<ArticleResource>()
                .lambda()
                .eq(ArticleResource::getId, parkCode));

        if (articleResource != null){

            return BeanPowerHelper.mapCompleteOverrider(articleResource,IntroduceVO.class);

        }else {
            log.error("园区介绍无信息");
            throw new ApplicationException(ErrorCode.AUTHENTICATION_ERROR,"该园区暂无介绍信息");
        }
    }

}
