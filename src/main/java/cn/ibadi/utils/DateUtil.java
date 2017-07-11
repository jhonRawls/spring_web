package cn.ibadi.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

/** 
 * 日期帮助类
 * @version 1.0 
 * @author pastry
 * @date 2015年10月13日
 */
public class DateUtil {

	public static String DATE_FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.SSS";

	public static String DATE_FORMAT_NORMAL = "yyyy-MM-dd HH:mm:ss";

	public static String DATE_FORMAT_QUERY = "yyyy-MM-dd HH:mm";

	public static String DATE_FORMAT_DATE = "yyyy年MM月dd日";

	public static String DATE_FORMAT = "yyyyMMdd";

	public static String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

	public static String DATE_FORMAT_YYYY_MM_DD_HH = "yyyy-MM-dd HH";

	public static String DATE_FORMAT_YYYYMMDDHHmmss = "yyyyMMddHHmmss";

	public static String DATE_FORMAT_YYYYMMDDHH = "yyyyMMddHH";

	public static String DATE_FORMAT_NORMAL_CN = "yyyy年MM月dd日HH:mm:ss";

	public static String DATE_FORMAT_YYYYMMDDHHmmssSSS = "yyyyMMddHHmmssSSS";

	// 用来全局控制 上一周，本周，下一周的周数变化
	private static int weeks = 0;

	/**
	 * 将字符串转换为Date日期
	 * @param strDate 日期字符串
	 * @return
	 */
	public static Date stringToDate(String strDate, String formatStr) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(formatStr);
			return format.parse(strDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取传入时间的前一天整点时间戳
	 * @param d 
	 * @return
	 */
	public static long getYesterdayTimestamp(Date d) {
		Date dNow = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dNow);
		calendar.add(Calendar.DAY_OF_MONTH, -1); 
		return calendar.getTimeInMillis()/1000;
	}
	
	/**
	 * 获取传入时间的前一天,以format显示
	 * @param d 		日期
	 * @param format 	日期格式
	 * @return
	 */
	public static String getYesterdayTimestamp(Date d, String format) {
		Date dNow = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dNow);
		calendar.add(Calendar.DAY_OF_MONTH, -1); 
		SimpleDateFormat formats = new SimpleDateFormat(format);
		return formats.format(new Date(calendar.getTimeInMillis()));
	}
	
	/** 获取当前月天数 **/
	public static int getDayOfMonth(){
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		int day = calendar.getActualMaximum(Calendar.DATE);
		return day;
	}
	
	/** 获取传入月份天数 **/
	public static int getDayOfMonth(int month){
		
		Calendar time=Calendar.getInstance(); 
		time.clear(); 
		time.set(Calendar.YEAR, time.get(Calendar.YEAR)); 
		//year年
		time.set(Calendar.MONTH,month-1);
		//Calendar对象默认一月为0,month月 
		return time.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	/** 获取当前月份 **/
	public static int getCurrentMonth() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;    
	}
	
	/** 获取当前年份 **/
	public static int getCurrentYear() {
		return Calendar.getInstance().get(Calendar.YEAR);    
	}
	
	/** 获取当前几号 **/
	public static int getCurrentDate() {
		return Calendar.getInstance().get(Calendar.DATE);    
	}
	
	/**
	 * 判断当前时间是否在时间段内
	 * @param startDate		开始时间
	 * @param endDate		结束时间
	 * @param currentDate	当前时间
	 */
	public static boolean isBetweenDate(Date startDate, Date endDate, Date currentDate) {
		if(currentDate.compareTo(startDate) >= 0 && endDate.compareTo(currentDate) >= 0) {
			return true;
		}
		return false;
	}
	
	public static Long timeDifference(Date d){
		Date dNow = new Date();
		//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long between = 0;
        try {
            java.util.Date begin = dNow;
            between = (d.getTime() - begin.getTime())/1000;// 得到两者的毫秒数
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		return between;
	}

	public static Calendar formatRDate(String strDate) {
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		Calendar cal = Calendar.getInstance();
		try {
			date = sdf2.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal.setTime(date);
		return cal;
	}
	
	public static String dateToStr(java.util.Date date) {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_FULL);
		return format.format(date);
	}
	
	public static String currentSSStr(Date d, String dateFormat) {
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		return format.format(d);
	}
	
	/**
	 * 
	 * @param date
	 * @return yyyy-MM-dd HH:mm
	 */
	public static String date2Str(java.util.Date date) {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_QUERY);
		return format.format(date);
	}

	public static String dateToStrNormal(java.util.Date date) {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_NORMAL);
		return format.format(date);
	}

	public static Date strToDate(String strDate) {
		SimpleDateFormat dtFormat = null;
		try {
			if (strDate.length() == DATE_FORMAT_QUERY.length()) {
				dtFormat = new SimpleDateFormat(DATE_FORMAT_QUERY);
			} else if (strDate.length() == DATE_FORMAT_YYYY_MM_DD.length()) {
				dtFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
			} else if (strDate.length() == DATE_FORMAT_NORMAL.length()) {
				dtFormat = new SimpleDateFormat(DATE_FORMAT_NORMAL);
			}

			return dtFormat.parse(strDate);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date stirngToDate(String strDdate, String format) {
		SimpleDateFormat dtFormat = null;
		
		try {
			if (format.equals(DATE_FORMAT_FULL)) {
				dtFormat = new SimpleDateFormat(DATE_FORMAT_FULL);
			} else if (format.equals(DATE_FORMAT_NORMAL)) {
				dtFormat = new SimpleDateFormat(DATE_FORMAT_NORMAL);
			}  else if (format.equals(DATE_FORMAT_QUERY)) {
				dtFormat = new SimpleDateFormat(DATE_FORMAT_QUERY);
			} else if (format.equals(DATE_FORMAT_DATE)) {
				dtFormat = new SimpleDateFormat(DATE_FORMAT_DATE);
			}  else if (format.equals(DATE_FORMAT)) {
				dtFormat = new SimpleDateFormat(DATE_FORMAT);
			} else if (format.equals(DATE_FORMAT_YYYY_MM_DD)) {
				dtFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
			}

			return dtFormat.parse(strDdate);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取时间描述
	 */
	public static String getDateSpoken() {
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		if (hour >= 6 && hour < 8) {
			return "早上";
		} else if (hour >= 8 && hour < 11) {
			return "上午";
		} else if (hour >= 11 && hour < 13) {
			return "中午";
		} else if (hour >= 13 && hour < 18) {
			return "下午";
		} else {
			return "晚上";
		}
	}

	public static String getNowDate() {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_DATE);
		return format.format(new Date());
	}
	
	
	public static String getNowDate(String dateFormate) {
		SimpleDateFormat format = new SimpleDateFormat(dateFormate);
		return format.format(new Date());
	}

	public static String getNowDay() {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
		return format.format(new Date());
	}

	/**
	 * 获取明天 
	 */
	public static String getTomorrowDay() {
		Calendar cd = Calendar.getInstance();
		cd.add(Calendar.DATE, 1);
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
		return format.format(cd.getTime());
	}
	
	/**
	 * 获取昨天
	 */
	public static Date getYesterday() {
		Calendar cd = Calendar.getInstance();
		cd.add(Calendar.DATE, -1);
		return cd.getTime();
	}
	
	
	/**
	 * 几小时前
	 */
	public static Date getBeforeHour(int hour) {
		Calendar cd = Calendar.getInstance();
		cd.add(Calendar.HOUR, hour);
		return cd.getTime();
	}
	
	/**
	 * 获取几天前
	 * 负数：前几天
	 * 证书：后几天
	 */
	public static Date getBeforDay(Integer day) {
		Calendar cd = Calendar.getInstance();
		cd.add(Calendar.DATE, day * (-1));
		return cd.getTime();
	}
	
	/**
	 * 获取当天凌晨时间
	 */
	public static Date getNowDayZero() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
	    cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MILLISECOND, 0);
	    //SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_NORMAL);
	    return  cal.getTime();
	   // return format.format(cal.getTime());
	}
	
	/**
	 * 获取昨天
	 */
	public static String getYesterdayStr() {
		Calendar cd = Calendar.getInstance();
		cd.add(Calendar.DATE, -1);
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
		return format.format(cd.getTime());
	}
	
	
	/**
	 * 获取几分钟前
	 */
	public static Date getBeforeMin(int min) {
		Calendar cd = Calendar.getInstance();
		cd.add(Calendar.MINUTE, min);
		return cd.getTime();
	}
	
	/**
	 * 获取几分钟前(正为后,负为前)
	 */
	public static Date getBeforeMin(int min,Date date) {
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		cd.add(Calendar.MINUTE, min);
		return cd.getTime();
	}
	
	/**
	 * 获得本年第一天的日期
	 */
	public static String getCurrentYearFirstDate() {
		int yearPlus = getYearPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, yearPlus);
		Date yearDay = currentDate.getTime();
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
		// DateFormat df = DateFormat.getDateInstance();
		String preYearDay = format.format(yearDay);
		return preYearDay;
	}

	private static int getYearPlus() {
		Calendar cd = Calendar.getInstance();
		int yearOfNumber = cd.get(Calendar.DAY_OF_YEAR);// 获得当天是一年中的第几天
		cd.set(Calendar.DAY_OF_YEAR, 1);// 把日期设为当年第一天
		cd.roll(Calendar.DAY_OF_YEAR, -1);// 把日期回滚一天。
		int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
		if (yearOfNumber == 1) {
			return -MaxYear;
		} else {
			return 1 - yearOfNumber;
		}
	}

	public static Date getExpireDate(int month) {
		Calendar cd = Calendar.getInstance();
		cd.set(Calendar.MONTH, month);
		return cd.getTime();
	}

	public static String getCNDate(Date lgesSigndate) {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_NORMAL_CN);
		return format.format(lgesSigndate);
	}
	
	public static String strDate2NowDay(String strDate) {
		Date date = strToDate(strDate);
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
		return format.format(date);
	}
	
	public static String strDate2NowDay(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
		return format.format(date);
	}
   
    // 获得当前日期与本周一相差的天数
    private static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return -6;
        } else {
            return 2 - dayOfWeek;
        }
    }

    // 获得上周星期一的日期
    public static String getPreviousMonday() {
        weeks--;
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks);
        Date monday = currentDate.getTime();
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
        String preMonday = format.format(monday);
        return preMonday;
    }

    // 获得当前周星期一的日期
    public static String getCurrentMonday() {
        weeks = 0;
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
        String preMonday = format.format(monday);
        return preMonday;
    }

    // 获得下周星期一的日期
    public static String getNextMonday() {
        weeks++;
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks);
        Date monday = currentDate.getTime();
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
        String preMonday = format.format(monday);
        return preMonday;
    }

    // 获得当周的周日的日期
    public static String getSunday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks + 6);
        Date monday = currentDate.getTime();
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
        String preMonday = format.format(monday);
        return preMonday;
    } 
    /**
     * 得到某年某月的第一天
     * 
     * @param year
     * @param month
     * @return
     */
    public static String getFirstDayOfMonth(int year, int month) {
	    Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.YEAR, year);
	    cal.set(Calendar.MONTH, month-1);
	    cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
	    return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }
    
    /**
     * 得到当年1月的第一天
     * 
     * @param year
     * @param month
     * @return
     */
    public static String getFirstDayOfMonth() {
	    Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
	    cal.set(Calendar.MONTH, 1-1);
	    cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
	    return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }
    
    /**
     * 得到当年12月的最后一天
     * 
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth() {
	    Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
	    cal.set(Calendar.MONTH, 12-1);
	    cal.set(Calendar.DAY_OF_MONTH, 1);
	    int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	    cal.set(Calendar.DAY_OF_MONTH, value);
	    return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }
     
    /**
     * 得到某年某月的最后一天
     * 
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth(int year, int month) {
	    Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.YEAR, year);
	    cal.set(Calendar.MONTH, month-1);
	    cal.set(Calendar.DAY_OF_MONTH, 1);
	    int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	    cal.set(Calendar.DAY_OF_MONTH, value);
	    return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }
    
    
    /**
     * 得到某年某月的前某个月的月数
     * @param pre 前几个月
     * @return
     */
    public static int getLastDayOfMonth(int pre) {
    	Calendar c = Calendar.getInstance();
	    c.add(Calendar.MONTH, -pre);
	    // 因为是0~11月  所以 加1 好是 1~12月
	    return (c.get(Calendar.MONTH)+1);
    }
    
    /**
     * 得到某年某月的前某个月的月数
     * @param pre 前4个月
     * @return
     */
    public static String getPreMonth() {
    	Calendar c = Calendar.getInstance();
	    c.add(Calendar.MONTH, -4);
	    return new SimpleDateFormat("yyyy-MM-01").format(c.getTime());
    }
    
    /**
     * 获取当前月的第一天
     * @param pre 当前月1号  yyyy-MM-01
     * @return
     */
    public static String getCurrentMonthFirstDay() {
    	Calendar c = Calendar.getInstance();
	    c.add(Calendar.MONTH, -0);
	    return new SimpleDateFormat("yyyy-MM-01").format(c.getTime());
    }
    
    /**
     * 获取当前月的最后一天
     * @param pre 当前月1号  yyyy-MM-01
     * @return
     */
    public static String getCurrentMonthLastDay() {
    	Calendar cale = Calendar.getInstance();   
        cale.set(Calendar.DAY_OF_MONTH, cale.getActualMaximum(Calendar.DAY_OF_MONTH));//设置为1号,当前日期既为本月第一天 
	    return new SimpleDateFormat("yyyy-MM-dd").format(cale.getTime());
    }
    
	/**
	 * 判断是否是周末
	 * @return
	 */
    public static boolean isWeekend(){
    	Calendar cal = Calendar.getInstance();
		int week=cal.get(Calendar.DAY_OF_WEEK)-1;
		if(week==0){// week ==6 ||  0代表周日，6代表周六
			return true;
		}
		return false;
	}
    
    /**
     * 获取今天  N年前/后的时间
     * @param years
     * @return
     */
    public static Date todayAfterYear(int years){
    	Calendar calendar = Calendar.getInstance();
    	
    	Date date = new Date(System.currentTimeMillis());
    	
    	calendar.setTime(date);
    	calendar.add(Calendar.YEAR, years);
    	
    	return date = calendar.getTime();
    }
    
    /**
     * 获取某天  N年前/后的时间
     * @param years
     * @return
     */
    public static Date thisDayAfterYear(int years, Date date){
    	Calendar calendar = Calendar.getInstance();
    	
    	calendar.setTime(date);
    	calendar.add(Calendar.YEAR, years);
    	
    	return date = calendar.getTime();
    }
    
    public static String dateToStr(java.util.Date date, String formatStr) {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		return format.format(date);
	}
    
    public static Date getTodayEnd(){
    	return DateUtil.stringToDate(DateUtil.getNowDate(DateUtil.DATE_FORMAT_YYYY_MM_DD + " 23:59:59"), DateUtil.DATE_FORMAT_NORMAL);
    }

    // 获取传入时间的周一
    public static Date getDayMonday(Date date) {
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    	return calendar.getTime();
    }
    
    // 获取传入时间的周日
    public static Date getDaySunday(Date date) {
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
    	return calendar.getTime();
    }
    
    // 获取某个时间的周一和周日
    public static Date[] getMondayAndSunday(Date startDate, Date endDate) {
    	Date[] dates = new Date[3];
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(startDate);
    	calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
    	if(calendar.getTime().after(endDate)) {
    		dates[1] = endDate;
    	} else {
    		dates[1] = calendar.getTime();
    	}
    	
    	calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
    	if(calendar.getTime().before(startDate)) {
    		dates[0] = startDate;
    	} else {
    		dates[0] = calendar.getTime();
    	}
    	
    	calendar.add(Calendar.WEEK_OF_MONTH, 1);
    	calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
    	dates[2] = calendar.getTime();
    	return dates;
    }
    
    // 获取2个日期天数
    public static int daysOfTwo(Date fDate, Date oDate) {

        Calendar aCalendar = Calendar.getInstance();

        aCalendar.setTime(fDate);

        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);

        aCalendar.setTime(oDate);

        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);

        return day2 - day1;

     }
    

	/**
	 * 判断是否为周一
	 */
	public static boolean isMonday(String dateStr) {
		Date d = stringToDate(dateStr, DATE_FORMAT_YYYY_MM_DD);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d); 
		int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if(week == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * 判断是否为1号
	 */
	public static boolean isMonthNo1(String dateStr) {
		if(StringUtils.isNotBlank(dateStr)) {
			StringBuilder sb = new StringBuilder();
			sb.append(dateStr.substring(0, 8)).append("01");
			if(sb.toString().equals(dateStr)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 获取当前日期上一个月日期
	 */
	public static String getLastMonthDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd");
		return formats.format(new Date(calendar.getTimeInMillis()));
	}

	/**
	 * 格式化传入的日期
	 * @param d 		日期
	 * @param format 	日期格式
	 * @return
	 */
	public static String formatDate(String d, String format) {
		SimpleDateFormat formats = new SimpleDateFormat(DATE_FORMAT_NORMAL);
		Date date=null;
		try {
			date=formats.parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new SimpleDateFormat(format).format(date);
	}

	
	/*--------时间戳方法start--------*/

	/**
	 *  获取当前时间到1970.1.1的秒数
	 */
	public static long getCurrSeconds() {
        return new Date().getTime() / 1000;
    }
	
	/**
	 * 返回从1970年1月1日到现在的总毫秒数 ,时间戳
	 */
	public static long getCurrMiliseconds(){
		return System.currentTimeMillis();
	}
	
	/**
	 * 返回Date类型，milisecond,毫秒 
	 */
	public static Date getDate(long milisecond) throws ParseException{
		SimpleDateFormat formats = new SimpleDateFormat(DATE_FORMAT_NORMAL);
		 String d = formats.format(milisecond);  
		 Date date=formats.parse(d);
		 return date;
		
	}
	
	/**
	 * 返回时间戳 
	 */
	public static long getMiliseconds(Date date){
		return date.getTime();
	}
	/*--------时间戳方法end--------*/
	
	public static void main(String[] args) throws Exception {
//		System.out.println(new Date().getTime());
//		System.out.println(getCurrentDate());
//		System.out.println(getYesterdayStr());
//		System.out.println(daysOfTwo(strToDate("2016-01-14"), strToDate("2016-02-29")));
//		System.out.println("2016-01-02".substring(0, 8));
//		long aa=getCurrMiliseconds();
//		System.out.println(aa);
//		System.out.println(getDate(aa));
//		Date date=new Date();
//		System.out.println(getMiliseconds(date));
		System.out.println(DateUtil.dateToStr(new Date(), DateUtil.DATE_FORMAT));
	}
}