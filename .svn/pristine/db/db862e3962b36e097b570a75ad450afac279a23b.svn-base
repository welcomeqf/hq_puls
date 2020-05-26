package com.gpdi.hqplus.common.util;

import java.util.*;

/**
 * 集合工具类
 *
 * @author: lianghb
 * @create: 2019-04-22 14:31
 **/
public class CollectionUtil {
    private CollectionUtil() {
    }

    /**
     * 判断目标集合是否为空
     *
     * @param map
     * @return
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.size() == 0;
    }

    /**
     * 判断目标集合是否不为空
     *
     * @param map
     * @return
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return map != null && map.size() > 0;
    }

    /**
     * 判断目标集合是否为空
     *
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.size() == 0;
    }

    /**
     * 判断目标集合是否不为空
     *
     * @param collection
     * @return
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return collection != null && collection.size() > 0;
    }

    /**
     * 创建一个新实例
     *
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> HashMap<K, V> createHashMap() {
        return new HashMap<>();
    }

    /**
     * 创建一个新实例
     *
     * @param initialCapacity
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> HashMap<K, V> createHashMap(int initialCapacity) {
        return new HashMap<>(initialCapacity);
    }

    /**
     * 创建一个新实例
     *
     * @param <E>
     * @return
     */
    public static <E> ArrayList<E> createArrayList() {
        return new ArrayList<>();
    }

    /**
     * 创建一个新实例
     *
     * @param initialCapacity
     * @param <E>
     * @return
     */
    public static <E> ArrayList<E> createArrayList(int initialCapacity) {
        return new ArrayList<>(initialCapacity);
    }

    /**
     * 创建一个新实例
     *
     * @param <E>
     * @return
     */
    public static <E> Set<E> createSet() {
        return new HashSet<>();
    }

    /**
     * 创建一个新实例
     *
     * @param initialCapacity
     * @param <E>
     * @return
     */
    public static <E> Set<E> createSet(int initialCapacity) {
        return new HashSet<>(initialCapacity);
    }

    /**
     * 数组转 set
     *
     * @param array
     * @param <E>
     * @return
     */
    public static <E> Set<E> array2Set(E[] array) {
        if (array == null) {
            return createSet();
        }

        Set<E> set = createSet(array.length);
        for (E e : array) {
            set.add(e);
        }
        return set;
    }
}
