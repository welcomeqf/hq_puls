package com.gpdi.hqplus.meeting.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.meeting.entity.query.MeetingAddQuery;
import com.gpdi.hqplus.meeting.entity.query.MeetingPageQuery;
import com.gpdi.hqplus.meeting.entity.query.MeetingRoomQuery;
import com.gpdi.hqplus.meeting.entity.vo.MeetingRoomListVO;
import com.gpdi.hqplus.meeting.entity.vo.TimeBitVO;
import com.gpdi.hqplus.meeting.entity.vo.TimeResourceVO;
import com.gpdi.hqplus.resources.entity.PropertyResource;
import com.gpdi.hqplus.resources.entity.query.PropertyQuery;
import com.gpdi.hqplus.resources.entity.query.TimeResourceQuery;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 会议室资源
 *
 * @author: lianghb
 * @create: 2019-07-12 21:38
 **/
public interface IMeetingService {

    Page<MeetingRoomListVO> pageMeetingRoomVO(MeetingRoomQuery query);

    /**
     * 添加新会议室资源
     *
     * @param query
     */
    void addNewMeetingProperty(PropertyQuery query);

    /**
     * 获取会议室资源
     *
     * @param idSet
     * @return
     */
    Map<Long, PropertyResource> listByIds(Set<Long> idSet);

    /**
     * 通过 id 获取会议室资源
     *
     * @param id
     * @return
     */
    PropertyResource getById(Long id);

    /**
     * 添加会议室资源、添加会议室商品
     *
     * @param query
     */
    void addNewMeetingRoomProduct(MeetingAddQuery query);

    /**
     * delete
     *
     * @param id
     */
    void deleteMeetingRoomProduct(Long id);

    /**
     * update
     *
     * @param query
     */
    void updateMeetingRoomProduct(MeetingAddQuery query);

    /**
     * list
     *
     * @param query
     * @return
     */
    Page<MeetingRoomListVO> listMeetingRoomListVO(MeetingPageQuery query);

    /**
     * 获取预定对象
     *
     * @param query
     * @return
     */
    List<TimeBitVO> getTimeBit(TimeResourceQuery query);

    /**
     * 获取预定对象
     *
     * @param query
     * @return
     */
    TimeResourceVO getTimeResource(TimeResourceQuery query);

    TimeResourceVO resource2TimeResource(PropertyResource resource, Integer[] timeBit);
}
