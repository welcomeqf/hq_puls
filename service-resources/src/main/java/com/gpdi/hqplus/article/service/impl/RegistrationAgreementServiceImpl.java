package com.gpdi.hqplus.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gpdi.hqplus.article.entity.vo.RegistrationAgreementVO;
import com.gpdi.hqplus.article.service.IRegistrationAgreementService;
import com.gpdi.hqplus.common.constants.BusinessCode;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.util.IdGenerator;
import com.gpdi.hqplus.resources.entity.ArticleResource;
import com.gpdi.hqplus.resources.mapper.ArticleResourceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: hmx
 * @CreateDate: 2019/07/17 14:40
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class RegistrationAgreementServiceImpl extends ServiceImpl<ArticleResourceMapper, ArticleResource> implements IRegistrationAgreementService {
	@Autowired
	private IdGenerator idGenerator;

	/**
	 * 获取注册协议富文本内容
	 *
	 * @return RegistrationAgreementVO
	 */
	@Override
	public RegistrationAgreementVO getDetail() {
		ArticleResource articleResource = this.baseMapper.selectOne(new QueryWrapper<ArticleResource>().lambda()
				.eq(ArticleResource::getBusinessCode, BusinessCode.REGISTRATION_AGREEMENT));
		RegistrationAgreementVO vo = new RegistrationAgreementVO();
		if (articleResource == null) {
			return vo;
		} else {
			BeanUtils.copyProperties(articleResource, vo);
			return vo;
		}
	}

	/**
	 * 保存注册协议
	 *
	 * @param vo
	 * @return String
	 */
	@Override
	public String saveRegistrationAgreement(RegistrationAgreementVO vo) {
		ArticleResource articleResource = new ArticleResource();
		BeanUtils.copyProperties(vo, articleResource);
		if (vo.getId() == null ){
			articleResource.setStatus(ArticleResource.STATUS_NORMAL);
			articleResource.setId(idGenerator.getNumberId());
			articleResource.setBusinessCode(BusinessCode.REGISTRATION_AGREEMENT);
			Integer flag =  this.baseMapper.insert(articleResource);
			if (flag == 1){
				return "ok";
			}else {
				log.error("新增注册协议失败！");
				throw new ApplicationException(ErrorCode.SERVICE_ERROR, "新增注册协议失败!");
			}
		}else {
			Integer flag =  this.baseMapper.updateById(articleResource);
			if (flag == 1){
				return "ok";
			}else {
				log.error("编辑注册协议失败！");
				throw new ApplicationException(ErrorCode.SERVICE_ERROR, "编辑注册协议失败!");
			}
		}
	}
}
