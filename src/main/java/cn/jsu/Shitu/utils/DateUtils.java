package cn.jsu.projectmanage.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Mo
 * @createTime 2022/1/5 20:03
 * @descripiton
 */
public class DateUtils {
    private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

    private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");

    private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");

    private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private final static SimpleDateFormat sdfTimeString = new SimpleDateFormat("yyyyMMddHHmmss");

    public DateUtils() {
    }

    /**
     * 获取 YYYY 格式
     *
     * @return yyyy
     */
    public static String getYear() {
        return sdfYear.format(new Date());
    }

    /**
     * 获取 YYYY-MM-DD 格式
     *
     * @return YYYY-MM-DD
     */
    public static String getDay() {
        return sdfDay.format(new Date());
    }

    /**
     * 获取 YYYYMMDD 格式
     *
     * @return YYYYMMDD
     */
    public static String getDays() {
        return sdfDays.format(new Date());
    }

    /**
     * 获取 YYYY-MM-DD HH:mm:ss 格式
     *
     * @return YYYY-MM-DD HH:mm:ss
     */
    public static String getTime() {
        return sdfTime.format(new Date());
    }

    /**
     * 通过date获取一个格式化YYYY-MM-DD HH:mm:ss
     * @param date
     * @return
     */
    public static String getTimeByDate(Date date) {
        return sdfTime.format(date);
    }

    /**
     * 获取 YYYYMMDDHHmmss 格式
     *
     * @return YYYYMMDDHHmmss
     */
    public static String getTimeString() {
        return sdfTimeString.format(new Date());
    }

    /**
     * <p>
     * 日期比较，如果s > = e 返回 true 否则返回 false
     * </p>
     *
     * @param s:
     * @param e:
     * @return：boolean
     * @author：bood
     * @date：2020/10/16
     */
    public static boolean compareDate(String s, String e) {
        if (fomatDate(s) == null || fomatDate(e) == null) {
            return false;
        }
        return fomatDate(s).getTime() >= fomatDate(e).getTime();
    }

    /**
     * <p>
     * 格式化日期
     * </p>
     *
     * @param date:
     * @return：java.util.Date
     * @author：bood
     * @date：2020/10/16
     */
    public static Date fomatDate(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * <p>
     * 校验日期是否合法
     * </p>
     *
     * @param s:
     * @return：boolean
     * @author：bood
     * @date：2020/10/16
     */
    public static boolean isValidDate(String s) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fmt.parse(s);
            return true;
        } catch (Exception e) {
            // 如果 throw java.text.ParseException 或者 NullPointerException，就说明格式不对
            return false;
        }
    }

    /**
     * <p>
     * 时间相减得到年数
     * </p>
     *
     * @param startTime:
     * @param endTime:
     * @return：int
     * @author：bood
     * @date：2020/10/16
     */
    public static int getDiffYear(String startTime, String endTime) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / (1000 * 60 * 60 * 24)) / 365);
            return years;
        } catch (Exception e) {
            // 如果 throw java.text.ParseException 或者 NullPointerException，就说明格式不对
            return 0;
        }
    }

    /**
     * <p>
     * 时间相减得到天数
     * </p>
     *
     * @param beginDateStr:
     * @param endDateStr:
     * @return：long
     * @author：bood
     * @date：2020/10/16
     */
    public static long getDaySub(String beginDateStr, String endDateStr) {
        long day = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = null;
        Date endDate = null;

        try {
            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        // System.out.println("相隔的天数="+day);
        return day;
    }

    /**
     * <p>
     * 得到 n 天之后的日期
     * </p>
     *
     * @param days:
     * @return：java.lang.String
     * @author：bood
     * @date：2020/10/16
     */
    public static String getAfterDayDate(String days) {
        int daysInt = Integer.parseInt(days);
        // java.util包
        Calendar canlendar = Calendar.getInstance();
        // 日期减 如果不够减会将月变动
        canlendar.add(Calendar.DATE, daysInt);
        Date date = canlendar.getTime();

        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);

        return dateStr;
    }

    /**
     * 得到n天之后是周几
     *
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
        int daysInt = Integer.parseInt(days);
        // java.util包
        Calendar canlendar = Calendar.getInstance();
        // 日期相减，如果不够减会将月变动
        canlendar.add(Calendar.DATE, daysInt);
        Date date = canlendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);

        return dateStr;
    }

    /**
     * <p>
     * 获得当前时间之前或之后几个月的日期
     * </p>
     *
     * @param amount: 值可正可负，负<==>before 正<==>after
     * @return：java.lang.String yyyy-MM-dd HH:mm:ss
     * @author：bood
     * @date：2020/10/16
     */
    public static String getAfterMonthDate(Integer amount) {
        //获取当前时间
        Date nowDate = new Date();
        //获取日历类实例
        Calendar cl = Calendar.getInstance();
        //设置当前时间
        cl.setTime(nowDate);
        cl.add(Calendar.MONTH, amount);
        return sdfTime.format(cl.getTime());
    }

    /**
     * <p>
     * 获得当前时间之前或之后几年的日期
     * </p>
     *
     * @param amount: 值可正可负，负<==>before 正<==>after
     * @return：java.lang.String yyyy-MM-dd HH:mm:ss
     * @author：bood
     * @date：2020/10/16
     */
    public static String getAfterYearDate(Integer amount) {
        //获取当前时间
        Date nowDate = new Date();
        //获取日历类实例
        Calendar cl = Calendar.getInstance();
        //设置当前时间
        cl.setTime(nowDate);
        cl.add(Calendar.YEAR, amount);
        return sdfTime.format(cl.getTime());
    }


    /**
     * 获取某个时间段内所有日期
     * @param begin
     * @param end
     * @return
     */
    public static List<String> getDayBetweenDates(String begin, String end) {
        Date dBegin = strToDate(begin);
        Date dEnd = strToDate(end);
        List<String> lDate = new ArrayList<String>();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        lDate.add(sd.format(dBegin));
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(sd.format(calBegin.getTime()));
        }
        return lDate;
    }


    public static String getNowTime() {
        return dateToStr(new Date());
    }

    /**
     * 日期转换成字符串
     * @param date
     * @return str
     */
    public static String dateToStr(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
        return str;
    }

    /**
     * 字符串转换成日期
     * @param str
     * @return date
     */
    public static Date strToDate(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date getDate(String endTime, int i) {
        return new Date(strToDate(endTime).getTime() + (long)i * 24 * 60 * 60 * 1000);
    }
}
