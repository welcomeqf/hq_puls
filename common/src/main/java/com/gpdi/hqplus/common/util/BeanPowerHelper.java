package com.gpdi.hqplus.common.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beust.jcommander.internal.Maps;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanMap;

import java.util.List;
import java.util.Map;


/**
 * dozer工具类：深度克隆
 *
 * @author: lianghb
 * created on : 2019-05-07 12:37
 **/
public class BeanPowerHelper {
    private BeanPowerHelper() {
    }

    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = Maps.newHashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key + "", beanMap.get(key));
            }
        }
        return map;
    }

    /**
     * 部分克隆：值为null、空字符串不拷贝
     */
    public static void mapPartOverrider(final Object sources, final Object destinations) {
        if (sources == null) {
            return;
        }
        BeanUtils.copyProperties(sources, destinations);
    }

    /**
     * 全部克隆：值为null、空字符串也拷贝
     */
    public synchronized static <T> T mapCompleteOverrider(final Object sources, Class<T> destinationClass) {
        if (sources == null) {
            return null;
        }
        T dest = null;
        try {
            dest = destinationClass.newInstance();
            BeanUtils.copyProperties(sources, dest);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return dest;
    }

    /**
     * 全部克隆：值为null、空字符串也拷贝
     */
    public synchronized static void mapCompleteOverrider(final Object sources, final Object destinations) {
        if (sources == null) {
            return;
        }
        BeanUtils.copyProperties(sources, destinations);
    }

    /**
     * 拷贝分页对象
     *
     * @param sources
     * @param destinationList
     * @param <T>
     * @return
     */
    public static <T> Page<T> mapPage(final Page sources, final List<T> destinationList) {
        Page<T> page = new Page<>();
        page.setCurrent(sources.getCurrent());
        page.setSize(sources.getSize());
        page.setTotal(sources.getTotal());
        page.setPages(sources.getPages());
        page.setRecords(destinationList);
        return page;
    }

}