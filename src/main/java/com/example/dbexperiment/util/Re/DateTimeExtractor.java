package com.example.dbexperiment.util.Re;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateTimeExtractor {
    public static String dealString(Date input) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM月dd日HH点mm分");
        String dateString = dateFormat.format(input);
        // 定义正则表达式
            try {
                return dateString;
            } catch (Exception e) {
                e.printStackTrace();
            }
        return null;
    }
}
