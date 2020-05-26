package com.gpdi.hqplus.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gpdi.hqplus.article.entity.vo.RegistrationAgreementVO;
import com.gpdi.hqplus.resources.entity.ArticleResource;

/**
 * @Author: hmx
 * @CreateDate: 2019/07/17 14:40
 */
public interface IRegistrationAgreementService  extends IService<ArticleResource> {

	/**
	 * 获取注册协议富文本内容
	 * @return RegistrationAgreementVO
	 */
	RegistrationAgreementVO getDetail();
	/**
	 * 保存注册协议
	 * @param vo
	 * @return
	 */
	String saveRegistrationAgreement(RegistrationAgreementVO vo);
}
