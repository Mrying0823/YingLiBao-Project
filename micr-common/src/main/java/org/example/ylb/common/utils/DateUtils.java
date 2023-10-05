package org.example.ylb.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 邓和颖
 * @since 2023/10/4 8:40
 * 公共工具类
 * 对 Date 类型数据进行处理的工具类
 */

public class DateUtils {
    /**
     *  对指定 date 对象格式化：yyyy-MM-dd HH:mm:ss
     */
    public static String formatDateTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static String formatTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(date);
    }

    // 对指定 String 对象进行格式化
    public static String reformatDateTime(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date parseDate = sdf.parse(date);

            return sdf.format(parseDate);
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

    public static String reformatDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date parseDate = sdf.parse(date);

            return sdf.format(parseDate);
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

    public static String reformatTime(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        try {
            Date parseDate = sdf.parse(date);

            return sdf.format(parseDate);
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }
}

