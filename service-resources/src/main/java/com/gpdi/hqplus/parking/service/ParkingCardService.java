package com.gpdi.hqplus.parking.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gpdi.hqplus.parking.entity.vo.ParkingCardVO;
import com.gpdi.hqplus.resources.entity.Book;

import java.util.List;

/**
 * @Description 停车场月卡相关功能
 * @Author wzr
 * @CreateDate 2019-07-01
 * @Time 15:25
 */
public interface ParkingCardService extends IService<Book> {

    /**
     * 返回当前用户所有月卡列表
     * @return
     */
    List<ParkingCardVO> listParkingCardByUser();

    /**
     * 月卡续费
     * @param bookId 月卡id
     * @param timePeriod 单位为月
     */
    void renewalParkingCard(Long bookId,int timePeriod);

    /**
     * 根据id查询停车场月卡
     * @param bookId 月卡id
     * @return
     */
    ParkingCardVO getParkingCardById(Long bookId);

    /**
     * 预约停车场月卡
     * @param map
     */
    void bookParkingCard(ParkingCardVO parkingCard);

    /**
     * 分页查询停车场月卡预约记录
     * @param page 分页参数
     * @return
     */
    Page listParkingCardByUserAndPage(Page page);

    /**
     * 根据预约id删除停车场预约记录（取消预约）
     * @param bookId 预约id
     */
    void deleteParkingCardById(Long bookId);

    /*上方为app端可操作的方法*/
    /*下方为web端可操作的方法，部分通用方法通用*/

    /**
     * 停车场月卡审核通过
     * @param bookId
     */
    void passParkingCard(Long bookId);

    /**
     * 停车场月卡审核不通过
     * @param bookId
     */
    void rejectParkingCard(Long bookId);

    /**
     * 展示所有停车场月卡
     * @return
     */
    List<ParkingCardVO> listParkingCard();

    /**
     * 分页展示所有停车场月卡
     * @param page
     * @return
     */
    Page listParkingCardByPage(Page page);

}
