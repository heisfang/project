package com.imooc.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class DateUtil {
    public static final String ISO_DATE_FORMAT = "yyyyMMdd";

    public static final String ISO_EXPANEDE_DATE_FORMAT = "yyyy-MM-dd";

    public static final String DATETIME_PATTERM = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_PATTERN ="yyyyMMddHHmmss";

    private static boolean LENIENT_DATE = false;

    private static Random random = new Random();
    private static final int ID_BYTES = 10;

    public synchronized static String generateId(){
        StringBuffer result = new StringBuffer();
        result = result.append(System.currentTimeMillis());
        for(int i=0;i< ID_BYTES;i++){
            result = result.append(random.nextInt(10));
        }
        return result.toString();
    }

    protected static final float normalizedJuliam(float JD){
        float f = Math.round(JD + 0.5f) -0.5f;
        return f;
    }

    public static Date stringToDate(String dateText,String format)  {
        if(dateText==null){
            return null;
        }

        DateFormat df = null;

        try {
            if(format==null){
                df=new SimpleDateFormat();
            }else {
                df= new SimpleDateFormat(format);
            }
            return df.parse(dateText);
        } catch (ParseException e) {
            return null;
        }

    }



}
