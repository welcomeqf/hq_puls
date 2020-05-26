package com.gpdi.hqplus.article.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gpdi.hqplus.article.entity.query.NoticeQuery;
import com.gpdi.hqplus.article.entity.vo.NoticeVO;
import com.gpdi.hqplus.resources.entity.ArticleResource;

import java.util.List;

/**
 * @Author: hmx
 * @CreateDate: 2019/07/01 16:00
 */
public interface INoticeService extends IService<ArticleResource> {
	/**
	 *  aap获取资讯列表
	 * @param query
	 * @return
	 */
	Page<NoticeVO> getNoticePage(NoticeQuery query);

	/**
	 * aap获取资讯详情
	 * @param id
	 * @return
	 */
	NoticeVO getNoticeDetailById(Long id);

	/**
	 * 新增公告
	 * @param noticeVO
	 * @return
	 */
	String addNotice(NoticeVO noticeVO);

	/**
	 * web 查询公告集合
	 * @param query
	 * @return
	 */
	Page<NoticeVO> getWebNoticePage(NoticeQuery query);

	/**
	 *  根据id删除公告
	 * @param ids
	 * @return
	 */
	String deleteNoticeByIds(List<Long> ids);
	/**
	 * 编辑公告
	 * @param noticeVO
	 * @return
	 */
	String updateNotice(NoticeVO noticeVO);
}
