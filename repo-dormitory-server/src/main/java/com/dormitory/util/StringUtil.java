package com.dormitory.util;

import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.List;

public class StringUtil extends StringUtils {

    /**
     * isEmpty:判断一个字符串是否是空. <br/>
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return ((str == null) || (str.trim().length() == 0));
    }

    /**
     * isNotEmpty:判断一个字符串是否不是空. <br/>
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return ((str != null) && (str.trim().length() > 0));

    }

    /**
     * lowerFirst:把一个字符串首字母小写. <br/>
     *
     * @param str 对象字符串
     * @return 转换后的字符串
     */
    public static String lowerFirst(String str) {
        if (isEmpty(str)) {
            return "";
        } else {
            if (str.length() > 1) {
                return str.substring(0, 1).toLowerCase() + str.substring(1, str.length());
            } else {
                return str.substring(0, 1).toLowerCase();
            }

        }
    }

    /**
     * 数组转list
     *
     * @param array
     * @return
     */
    public static List<Integer> toList(int[] array) {
        if (array == null || array.length == 0) return null;
        List<Integer> idList = new ArrayList<>();
        for (int index = 0; index < array.length; index++) {
            idList.add(array[index]);
        }
        return idList;
    }

    /**
     * 移除XSS攻击
     */
    public static String removeXSS(String text) {
        if (text != null) {
            return HtmlUtils.htmlEscape(text);
        } else
            return "";
    }

    /**
     * 获取两个数比值
     *
     * @param a
     * @param b
     * @return
     */
    public static String minScale(int a, int b) {
        int tmp = a;
        if (a > b) {
            tmp = b;
        }
        String string = null;
        for (int i = tmp; i > 0; i--) {
            if (a % i == 0 && b % i == 0) {
                string = (a / i) + ":" + (b / i);
                break;
            }
        }

        return string;
    }

    public static void main(String args[]) {
        System.out.println(lowerFirst("test"));
        System.out.println(lowerFirst("t"));
        System.out.println(lowerFirst("HelloWorld."));
    }

}
