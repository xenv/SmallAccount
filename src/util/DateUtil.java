package util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    private static final long millisecondsOfOneDay = 1000*60*60*24;
    private static Calendar c = Calendar.getInstance();

    public static java.sql.Date util2sql(java.util.Date d){
        return new java.sql.Date(d.getTime());
    }

    public static Date today(){
        c.setTime(new Date());
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);
        return c.getTime();
    }

    public static Date monthBegin(){
        c.setTime(new Date());
        c.set(Calendar.DATE,1);
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);
        return c.getTime();
    }

    public static Date monthEnd(){
        c.setTime(new Date());
        c.set(Calendar.DATE,1);
        c.add(Calendar.MONTH,1);
        c.add(Calendar.DATE,-1);
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);
        return c.getTime();
    }

    public static int thisMonthTotalDay(){
        c.setTime(new Date());
        monthEnd();
        return c.get(Calendar.DATE);
    }

    public static int thisMonthLeftDay(){
        int TotalDay = thisMonthTotalDay();
        today();
        int today = c.get(Calendar.DATE);
        return TotalDay-today;
    }
}
