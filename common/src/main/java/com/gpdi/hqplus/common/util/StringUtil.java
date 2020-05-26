package com.gpdi.hqplus.common.util;
import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

import java.util.regex.Matcher;

import java.util.Random;
import java.util.regex.Pattern;

/**
 * StringUtil
 *
 * @author: lianghb
 * @create: 2019-05-07 16:24
 **/
public class StringUtil {
    private StringUtil() {

    }

    public static String convertToPinYin(String val) {
        if (isBlank(val)){
            return null;
        }
        String result = null;
        try {
            result = PinyinHelper.convertToPinyinString(val, "", PinyinFormat.WITHOUT_TONE);
        } catch (PinyinException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static final String REG_PHONE = "^1[3456789]\\d{9}$";

    /**
     * 正则匹配手机号码
     * @param val
     * @return
     */
    public static boolean regPhone(String val) {
        return match(REG_PHONE, val);
    }

    /**
     * 判断字符串是否为空，空格也算为空
     *
     * @param cs
     * @return
     */
    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否不为空，空格也算为空
     *
     * @param cs
     * @return
     */
    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }

    /**
     * 判断字符串是否为空
     *
     * @param cs
     * @return
     */
    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     * 判断字符串是否不为空
     *
     * @param cs
     * @return
     */
    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }

    /**
     * 首字母转小写
     *
     * @param s
     * @return
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }


    /**
     * 首字母转大写
     *
     * @param s
     * @return
     */
    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * 获取一个随机字符串
     *
     * @param length 表示生成字符串的长度
     * @return
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        return getRandomString(length, base);
    }

    /**
     * 获取一个随机数字
     *
     * @param length
     * @return
     */
    public static String getRandomInteger(int length) {
        String base = "0123456789";
        return getRandomString(length, base);
    }

    public static String getRandomString(int length, String base) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * @param regex
     * 正则表达式字符串
     * @param str
     * 要匹配的字符串
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
     */
    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

}
