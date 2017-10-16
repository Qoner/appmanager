package com.qoneway.manager.utils;

import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    public static boolean isBlank(String str) {
        if (null == str) {
            return true;
        }
        if ("".equals(str.trim())) {
            return true;
        }
        return false;
    }

    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        SecureRandom random = new SecureRandom();
        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < length; i++) {
            int num = random.nextInt(str.length());
            buf.append(str.charAt(num));
        }

        return buf.toString();
    }

    /**
     * 左补齐
     * 
     * @param target
     *            目标字符串
     * @param fix
     *            补齐字符
     * @param length
     *            目标长度
     * @return
     */
    public static String lPad(String target, String fix, int length) {
        if (target == null || fix == null || !(target.length() < length))
            return target;
        StringBuffer newStr = new StringBuffer();
        for (int i = 0; i < length - target.length(); i++) {
            newStr.append(fix);
        }
        return newStr.append(target).toString();
    }

    /**
     * 右补齐
     * 
     * @param target
     *            目标字符串
     * @param fix
     *            补齐字符
     * @param length
     *            目标长度
     * @return
     */
    public static String rPad(String target, String fix, int length) {
        if (target == null || fix == null || !(target.length() < length))
            return target;
        StringBuffer newStr = new StringBuffer();
        newStr.append(target);
        for (int i = 0; i < length - target.length(); i++) {
            newStr.append(fix);
        }
        return newStr.toString();
    }

    /**
     * 字符串数据join操作
     * 
     * @param strs
     * @param spi
     * @return
     * @author zhoubo
     */
    public static String join(String[] strs, String spi) {
        StringBuffer buf = new StringBuffer();
        int step = 0;
        for (String str : strs) {
            buf.append(str);
            if (step++ < strs.length - 1)
                buf.append(spi);
        }
        return buf.toString();
    }

    /**
     * 是否为有理数，例：<BR>
     * 123<BR>
     * 1.23<BR>
     * -1.23<BR>
     * 
     * @param str
     * @return
     * @author baixin
     * @author Hone
     */
    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        Pattern pattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
    
    /**
     * 校验正整数
     * @param str
     * @return 是（true）
     * @author Hone
     */
    public static boolean validateInteger(String str) {
        if (str == null) {
            return false;
        }
        Pattern pattern = Pattern.compile("[1-9]+[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
    
    /**
     * 将大于1的整数转为Integer，否则为默认值
     * 用于校验pageNo和pageSize
     * @param num
     * @param defNum 默认值
     * @return
     * @author Hone
     */
    public static Integer parsePageInfo(String num, Integer defNum) {
        if (StringUtil.validateInteger(num)) {
            return Integer.parseInt(num);
        }
        return defNum;
    }
 
}