package com.gpdi.hqplus.parking.constants;

public class ParkingConstants {
    /***************************************停车场月卡相关常量*****************************************/
    public static final String PARKING_CARD_TYPE = "ParkingCard";

    /**
     * 停车场月卡审核未通过
     */
    public static final String PARKING_CARD_STATUS_FAIL = "-1";
    /**
     * 停车场月卡审核中
     */
    public static final String PARKING_CARD_STATUS_AUDITING ="0";
    /**
     * 停车场月卡审核通过，等待缴费
     */
    public static final String PARKING_CARD_STATUS_PASS = "1";
    /**
     * 停车场月卡正常缴费中
     */
    public static final String PARKING_CARD_STATUS_NORMAL = "2";
    /**
     * 停车场月卡逾期未续费
     */
    public static final String PARKING_CARD_STATUS_OVERDUE = "3";

    /**
     * 设备id列表
     */
    public static final String EQUIPMENT_IDS = "equipmentIds";


    private ParkingConstants() { throw new IllegalStateException("Utility class"); }
}
