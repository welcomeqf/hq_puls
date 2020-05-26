package com.gpdi.hqplus.resources.util;

import com.gpdi.hqplus.common.util.CollectionUtil;
import com.gpdi.hqplus.common.util.StringUtil;
import com.gpdi.hqplus.resources.service.IProductResourceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @Description 复制属性
 * @Author wzr
 * @CreateDate 2019-07-09
 * @Time 15:55
 */
public class CopyProperties {

    public static void copyAll(Object resource,Object target){
        BeanUtils.copyProperties(resource,target);
    }

    public static void copyIgnoreNull(Object resource , Object target){
        //从resource复制到target，所以查找出resource中的空字段，不复制
        String[] ignoreProperties = getIgnoreProperties(resource);
        BeanUtils.copyProperties(resource,target,ignoreProperties);
    }

    /**
     * 获取目标对象中为空字段的字段名数组
     * @param resource
     * @return
     */
    private static String[] getIgnoreProperties(Object resource){
        //对象不能为空
        Assert.notNull(resource,"target不能为空");
        //使用spring的BeanWrapper代理，并获取对象的属性
        final BeanWrapper beanWrapper = new BeanWrapperImpl(resource);
        PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();
        //创建新集合。不需要排序所以选择set
        Set<String> noValPropertySet = CollectionUtil.createSet();
        //创建流
        Arrays.stream(propertyDescriptors).forEach(propertyDescriptor -> {
            //通过getName获取属性名，getPropertyValue获取属性值
            Object propertyValue = beanWrapper.getPropertyValue(propertyDescriptor.getName());
            //如果属性可转为字符串类型，根据StringUtil.isEmpty来判断
            if (propertyValue==null|| StringUtil.isEmpty(propertyValue.toString())){
                noValPropertySet.add(propertyDescriptor.getName());
            }else {

                //如果属性实现了迭代器，根据是否有hasNext来判断
                if (Iterable.class.isAssignableFrom(propertyDescriptor.getClass())){
                    Iterable iterable = (Iterable) propertyValue;
                    Iterator iterator = iterable.iterator();
                    if (!iterator.hasNext()){
                        noValPropertySet.add(propertyDescriptor.getName());
                    }
                }

                //如果属性是map集合，根据CollectionUtil.isEmpty来判断
                if (Map.class.isAssignableFrom(propertyDescriptor.getClass())){
                    Map map = (Map)propertyValue;
                    if(CollectionUtil.isEmpty(map)){
                        noValPropertySet.add(propertyDescriptor.getName());
                    }
                }
            }
        });

        //创建相同大小的数组
        String[] strings = new String[noValPropertySet.size()];

        //转为数组
        return noValPropertySet.toArray(strings);

    }
}
