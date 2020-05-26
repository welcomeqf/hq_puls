package com.gpdi.hqplus.common.util;

/**
 * 判断mapper.xml文件 判断入参是否为空
 *
 * @Author: hmx
 * @CreateDate: 2019/07/09 14:26
 */
public class Ognl {
	/**
	 * 判断 string 是否为null,空格  是
	 *
	 * @param str
	 * @return
	 */
	public static boolean isString(String str) {
		return StringUtil.isNotBlank(str);
	}

	/**
	 * 判断是否为 Long
	 *
	 * @param object
	 * @return
	 */
	public static boolean isLong(Object object) {
		return object instanceof Long;
	}

	/**
	 * 判断对象是否为null
	 *
	 * @param object
	 * @return
	 */
	public static boolean isNotEmpty(Object object) {
		return object != null;
	}
}
