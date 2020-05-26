package com.gpdi.hqplus.common.advice;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.gpdi.hqplus.common.entity.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 公共字段自动填充
 *
 * @author: lianghb
 * @create: 2019-06-10 14:35
 **/
@Slf4j
@Component
public class AutoFillMetaObjectHandler implements MetaObjectHandler {

    private final String FIELD_CREATE_TIME="createTime";
    private final String FIELD_UPDATE_TIME="updateTime";
    private final String FIELD_PROJECT_CODE="projectCode";
    /**
     * 插入元对象字段填充（用于插入时对公共字段的填充）
     *
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 创建时间
        Object createTime = metaObject.getValue(FIELD_CREATE_TIME);
        if (createTime == null) {
            setInsertFieldValByName(FIELD_CREATE_TIME, LocalDateTime.now(), metaObject);
        }

        // 更新时间
        Object updateTime = metaObject.getValue(FIELD_UPDATE_TIME);
        if (updateTime == null) {
            setInsertFieldValByName(FIELD_UPDATE_TIME, LocalDateTime.now(), metaObject);
        }

        // 园区代号
        if (metaObject.hasSetter(FIELD_PROJECT_CODE)) {
            Object parkCode = metaObject.getValue(FIELD_PROJECT_CODE);
            if (parkCode == null && UserContext.get() != null) {
                setInsertFieldValByName(FIELD_PROJECT_CODE, UserContext.get().getProjectCode(), metaObject);
            }
        }
    }

    /**
     * 更新元对象字段填充（用于更新时对公共字段的填充）
     *
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新时间
        setUpdateFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }
}
