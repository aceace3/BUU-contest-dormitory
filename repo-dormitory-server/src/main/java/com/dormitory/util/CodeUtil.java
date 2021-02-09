package com.dormitory.util;

import java.util.Random;
import java.util.UUID;

/**
 * 常用code生产工具
 *
 * 定长随机数，定长随机字串，32位UUID
 */
public class CodeUtil {

    /**
     * 获得N位随机数
     * @param length 随机数长度
     * @return
     */
    public static String getRandomNum(int length) {
        String str = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < length; ++i)
        {
            int number = random.nextInt(10);//[0,62)

            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 获得N位随机字符串
     * @param length 随机数长度
     * @return
     */
    public static String getLoginToken(int length) {
        String str = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < length; ++i)
        {
            int number = random.nextInt(62);

            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 生产全局唯一的UUID（去掉-号）
     * @return 返回字符串32个字符
     */
    public static String generateUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
