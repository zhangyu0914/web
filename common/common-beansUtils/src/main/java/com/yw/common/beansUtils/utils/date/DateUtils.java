package com.yw.common.beansUtils.utils.date;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import com.yw.common.beansUtils.utils.string.StringUtils;

/**
 * <pre>
 * 功   能:   
 * 创建者: 陈林林(Vickey)
 * 修改者: 
 * 日   期: 2014-6-12下午11:52:32
 * Q  Q: 308052847
 * </pre>
 */
public class DateUtils {

	private static Logger log = Logger.getLogger(DateUtils.class.getName());
	
	public final static String PATTERN_24_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss"; // 24小时制
	public final static String PATTERN_24_YYYY_MM_DD_HH_MM_SS_sss = "yyyy-MM-dd HH:mm:ss,SSS"; // 24小时制
	public final static String PATTERN_24_YYYYMMDD_HH_MM_SS = "yyyyMMdd HH:mm:ss"; // 24小时制
	public final static String PATTERN_24_YYYY__MM__DD__HH__MM__SS = "yyyy_MM_dd_HH_mm_ss"; // 24小时制
	public final static String PATTERN_24_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm"; // 24小时制
	public final static String PATTERN_12_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd hh:mm:ss"; // 12小时制
	public final static String PATTERN_24_YYYYMMDDHHMMSS = "yyyyMMddHHmmss"; // 24小时制
	public final static String PATTERN_24_YYYYMMDDHHMM = "yyyyMMddHHmm"; // 24小时制
	public final static String PATTERN_12_YYYYMMDDHHMMSS = "yyyyMMddhhmmss"; // 12小时制
	public final static String PATTERN_24_YYYYMM = "yyyyMM";
	public final static String PATTERN_24_YYYYMMDD = "yyyyMMdd";
	public final static String PATTERN_24_YYYY_MM_DD = "yyyy-MM-dd";
	public final static String PATTERN_24_YY_MM_DD = "yy-MM-dd";
	public final static String PATTERN_24_YYYY_MM = "yyyy-MM";
	public final static String PATTERN_24_YYYY_MM_CN = "yyyy年MM月";
	public final static String PATTERN_HH_MM = "HH:mm";
	public final static String PATTERN_HH_MM_SS = "HH:mm:ss";
	public final static String PATTERN_MM_DD = "MM/dd";
	public final static String PATTERN_YYYY = "yyyy";
	public final static String PATTERN_MM = "MM";
	public final static String PATTERN_DD = "dd";
	public static final String PARAM_TIME_STAR = " 00:00:00";
	public static final String PARAM_TIME_END = " 23:59:59";
	public static final long MILLI_PER_SEC = 1000;
	public static final long MILLI_PER_MIN = MILLI_PER_SEC * 60;
	public static final long MILLI_PER_HOUR = MILLI_PER_MIN * 60;
	public static final long MILLI_PER_DAY = MILLI_PER_HOUR * 24;
	
	public static void main(String[] args) {
		try {
			Date date = formatUTC("2017-06-01T01:58:31.759Z");
			System.out.println(format(date.getTime(), PATTERN_24_YYYY_MM_DD_HH_MM_SS));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 将Date类型转换为字符串
	 * 
	 * @param date
	 *            日期类型
	 * @return 日期字符串
	 */
	public static String format(Date date) throws Exception {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * <pre>
	 * 说       明: 国际化时间
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月1日下午6:51:31
	 * Q    Q: 308053847
	 * </pre>
	 */
	public static Date formatUTC(String strDate) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		df.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date date = df.parse(strDate);
		
//		TimeZone time = TimeZone.getDefault();
		return date;
	}
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月16日上午9:53:43
	 * Q    Q: 308053847
	 * </pre>
	 */
	public static String formatUTCStr(String strDate, String pattern) throws Exception {
		if (pattern == null || pattern.equals("") || pattern.equals("null")) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		return DateUtils.format(DateUtils.formatUTC(strDate), pattern);
	}

	/**
	 * 将Date类型转换为字符串
	 * 
	 * @param date
	 *            日期类型
	 * @param pattern
	 *            字符串格式
	 * @return 日期字符串
	 */
	public static String format(Date date, String pattern) throws Exception {
		if (date == null) {
			return "null";
		}
		if (pattern == null || pattern.equals("") || pattern.equals("null")) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		return new java.text.SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 将字符串转换为Date类型
	 * 
	 * @param date
	 *            字符串类型
	 * @return 日期类型
	 * @throws Exception
	 */
	public static Date format(String date) throws Exception {
		return format(date, null);
	}

	/**
	 * 将字符串格式化
	 * 
	 * @param date
	 *            字符串类型
	 * @param pattern
	 *            格式
	 * @return 日期类型
	 * @throws Exception
	 */
	public static String formatString(String date, String pattern)
			throws Exception {
		if (pattern == null || pattern.equals("") || pattern.equals("null")) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		if (date == null || date.equals("") || date.equals("null")) {
			return null;
		}

		Date d = format(date, pattern);
		date = new java.text.SimpleDateFormat(pattern).format(d);
		return date;
	}

	/**
	 * 将字符串转换为Date类型
	 * 
	 * @param date
	 *            字符串类型
	 * @param pattern
	 *            格式
	 * @return 日期类型
	 * @throws ParseException
	 */
	public static Date format(String date, String pattern) throws Exception {
		if (pattern == null || pattern.equals("") || pattern.equals("null")) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		if (date == null || date.equals("") || date.equals("null")) {
			return new Date();
		}
		Date d = new java.text.SimpleDateFormat(pattern).parse(date);
		return d;
	}
	
	/**
	 *<pre>
	 * 说       明: 字符串时间转换为毫秒数
	 * @param datetime
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-9-18上午9:48:25
	 *</pre>
	 */
	public static Date parseDate(String datetime) throws Exception{
        if (datetime == null) {
			return null;
        }
        if (datetime.length() < 4 || datetime.length() > 14) {
			return null;
		}
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PATTERN_24_YYYYMMDDHHMMSS);
        DateFormat dateFormat = simpleDateFormat;
        if(datetime.length() < 14){
            dateFormat = new SimpleDateFormat(PATTERN_24_YYYYMMDDHHMMSS.substring(0, datetime.length()));
        }
        return dateFormat.parse(datetime);
    }

	/**
	 * 得到几天前的时间
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateBefore(Date d, int day) throws Exception {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		return now.getTime();
	}

	/**
	 * <pre>
	 * 说       明: 得到几天后的时间
	 * @param d
	 * @param day
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-7-24下午3:41:18
	 * </pre>
	 */
	public static Date getDateAfter(Date d, int day) throws Exception {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}
	
	/**
	 *<pre>
	 * 说       明: 得到指定分钟以后的时间
	 * @param time
	 * @param minute
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-12-30下午4:07:19
	 *</pre>
	 */
	public static Timestamp getDateAfterMinute(Timestamp time, int minute) throws Exception {
		Calendar now = Calendar.getInstance();
		now.setTime(new Date(time.getTime()));
		now.set(Calendar.MINUTE, now.get(Calendar.MINUTE) + minute);
		return new Timestamp(now.getTimeInMillis());
	}
	
	/**
	 * <pre>
	 * 说       明: 得到指定小时以后的时间
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年4月26日下午2:50:01
	 * Q    Q: 308053847
	 * </pre>
	 */
	public static String getDateAfterHour(Timestamp time, int hour) throws Exception {
		if (StringUtils.isBlank(time)) {
			time = DateUtils.getTimestamp();
		}
		Calendar now = Calendar.getInstance();
		now.setTime(new Date(time.getTime()));
		now.set(Calendar.HOUR, now.get(Calendar.HOUR) + hour);
		return format(new Timestamp(now.getTimeInMillis()), PATTERN_24_YYYY_MM_DD_HH_MM_SS);
	}
	
	public static Date getDateAfter(Long time, int day) throws Exception {
		Calendar now = Calendar.getInstance();
		now.setTime(new Date(time));
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}

	/**
	 *<pre>
	 * 说       明: 
	 * 医患通端: 
	 * @param time
	 * @param day
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-11-16下午6:16:04
	 *</pre>
	 */
	public static Timestamp getDateAfter(Timestamp time, int day) throws Exception {
		Calendar now = Calendar.getInstance();
		now.setTime(new Date(time.getTime()));
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return formatDate(now.getTime(), PATTERN_24_YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 *<pre>
	 * 说       明: 得到当前几天后的时间
	 * @param day
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-11-12下午4:26:28
	 *</pre>
	 */
	public static Timestamp getDateAfter(int day) throws Exception {
		Calendar now = Calendar.getInstance();
		now.setTime(new Date());
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return formatDate(now.getTime(), PATTERN_24_YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * <pre>
	 * 说       明: 得到几天后的时间
	 * @param d
	 * @param day
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-7-24下午3:50:09
	 * </pre>
	 */
	public static Date getDateAfter(String d, int day) throws Exception {
		return getDateAfter(format(d, PATTERN_24_YYYY_MM_DD_HH_MM_SS), day);
	}

	/**
	 * <pre>
	 * 说       明: 将字符串转换为Date类型
	 * @param d
	 * @param day
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-7-24下午3:50:06
	 * </pre>
	 */
	public static String getDateAfterToStr(String d, int day) throws Exception {
		Date date = getDateAfter(format(d, PATTERN_24_YYYY_MM_DD_HH_MM_SS), day);
		return format(date, PATTERN_24_YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 获取间隔天数
	 * 
	 * @param strBeginDate
	 * @param strEndDate
	 * @return
	 * @throws ParseException
	 */
	public static long getIntervalDay(String strBeginDate, String strEndDate)
			throws ParseException {
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		Date beginDate = format.parse(strBeginDate);
		Date endDate = format.parse(strEndDate);
		long intervalDayNum = (beginDate.getTime() - endDate.getTime())
				/ (24 * 60 * 60 * 1000);
		return intervalDayNum;
	}

	/**
	 * 将Date换为Timestamp类型
	 * 
	 * @param date
	 *            字符串类型
	 * @return Timestamp类型
	 * @throws Exception
	 */
	public static Timestamp formatDate(Date date) throws Exception {
		return formatDate(date, null);
	}

	/**
	 * 将字符串换为Timestamp类型
	 * 
	 * @param date
	 *            字符串类型
	 * @return Timestamp类型
	 * @throws Exception
	 */
	public static Timestamp formatDate(String date) throws Exception {
		return formatDate(date, null);
	}

	/**
	 * 将Date换为Timestamp类型
	 * 
	 * @param date
	 *            字符串类型
	 * @return Timestamp类型
	 */
	public static Timestamp formatDate(Date date, String pattern)
			throws Exception {
		if (date == null) {
			return null;
		}
		if (pattern == null || pattern.equals("") || pattern.equals("null")) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		Timestamp t = null;
		if (date == null || date.equals("") || date.equals("null")) {
			t = new Timestamp(new Date().getTime());
		}
		t = Timestamp.valueOf(new SimpleDateFormat(pattern).format(date));
		return t;
	}

	/**
	 * 将字符串转换为Timestamp类型
	 * 
	 * @param date
	 *            字符串类型
	 * @return Timestamp类型
	 * @throws Exception
	 */
	public static Timestamp formatDate(String date, String pattern)
			throws Exception {
		if (date == null) {
			return null;
		}
		if (pattern == null || pattern.equals("") || pattern.equals("null")) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		Timestamp t = null;
		if (date == null || date.equals("") || date.equals("null")) {
			t = new Timestamp(new Date().getTime());
		}
		t = new Timestamp(new java.text.SimpleDateFormat(pattern).parse(date)
				.getTime());
		return t;
	}

	/**
	 * 获取间隔月份后时间
	 * 
	 * @param intervalMonth
	 * @param strBeginDate
	 * @return
	 * @throws ParseException
	 */
	public static String getMonthAfter(Integer intervalMonth,
			String strBeginDate) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(
				PATTERN_24_YYYY_MM_DD_HH_MM_SS);
		Date now = sdf.parse(strBeginDate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.MONTH, intervalMonth);
		return sdf.format(calendar.getTime());
	}
	
	/**
	 *<pre>
	 * 说       明: 得到指定年后/前的时间
	 * @param intervalYear
	 * @param strBeginDate
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-11-13下午1:39:14
	 *</pre>
	 */
	public static String getYearAfter(Integer intervalYear, String strBeginDate) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_24_YYYY_MM_DD_HH_MM_SS);
		Date now = sdf.parse(strBeginDate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.YEAR, intervalYear);
		return sdf.format(calendar.getTime());
	}
	
	/**
	 *<pre>
	 * 说       明: 
	 * @param intervalYear
	 * @param strBeginDate
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-11-16下午6:19:58
	 *</pre>
	 */
	public static Timestamp getYearAfter(Integer intervalYear, Timestamp strBeginDate) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(strBeginDate.getTime()));
		calendar.add(Calendar.YEAR, intervalYear);
		return new Timestamp(calendar.getTime().getTime());
	}

	/**
	 * 获取间隔月份后时间
	 * 
	 * @param intervalMonth
	 * @param strBeginDate
	 * @return
	 * @throws Exception
	 */
	public static String getMonthAfter(Integer intervalMonth, Date strBeginDate)
			throws Exception {
		return getMonthAfter(intervalMonth,
				format(strBeginDate, PATTERN_24_YYYY_MM_DD_HH_MM_SS));
	}

	/**
	 * 获取间隔月份后/前时间
	 * @param intervalMonth
	 * @param strBeginDate
	 * @return
	 * @throws Exception
	 */
	public static Timestamp getMonthAfter(Integer intervalMonth,Timestamp strBeginDate) throws Exception {
		String time = getMonthAfter(intervalMonth,format(strBeginDate, PATTERN_24_YYYY_MM_DD_HH_MM_SS));
		return formatDate(time, PATTERN_24_YYYY_MM_DD_HH_MM_SS);
	}
	
	/**
	 *<pre>
	 * 说       明: 获取间隔月份后/前时间
	 * @param intervalMonth
	 * @param strBeginDate
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-11-13上午11:25:26
	 *</pre>
	 */
	public static Timestamp getMonthAfter(Integer intervalMonth,Long strBeginDate) throws Exception {
		String time = getMonthAfter(intervalMonth,format(strBeginDate, PATTERN_24_YYYY_MM_DD_HH_MM_SS));
		return formatDate(time,PATTERN_24_YYYY_MM_DD_HH_MM_SS);
	}
	
	/**
	 * <pre>
	 * 说       明: 获取间隔月份后/前时间
	 * @param intervalMonth
	 * @param strBeginDate
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-8-6下午2:14:01
	 * </pre>
	 */
	public static Timestamp getMonthAfterToTimestamp(Integer intervalMonth,
			Timestamp strBeginDate) throws Exception {
		String time = getMonthAfter(intervalMonth,
				format(strBeginDate, PATTERN_24_YYYY_MM_DD_HH_MM_SS));
		return formatDate(time, PATTERN_24_YYYY_MM_DD_HH_MM_SS);
	}
	
	public static Timestamp getYearAfter(Integer intervalYear,Long strBeginDate) throws Exception {
		String time = getYearAfter(intervalYear, format(strBeginDate, PATTERN_24_YYYY_MM_DD_HH_MM_SS));
		return formatDate(time,PATTERN_24_YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 获取Timestamp类型当前时间
	 * 
	 * @return
	 */
	public static Timestamp getTimestamp() throws Exception {
		return new Timestamp(new Date().getTime());
	}

	/**
	 * <pre>
	 * 说   明:  在当前系统时间基础上，添加或减少指定秒数
	 * @param plusSeconds
	 * @return
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-7-4上午11:00:52
	 * </pre>
	 */
	public static Timestamp getTimestamp(int plusSeconds) throws Exception {
		return new Timestamp(new Date().getTime() + (plusSeconds * 1000));
	}

	/**
	 * <pre>
	 * 说   明:  根据传入的时间,得到指定增加后的天数
	 * @param time
	 * @param day
	 * @return
	 * @throws Exception
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-8-27下午4:17:18
	 * </pre>
	 */
	public static Timestamp getTimestamp(Timestamp time, int day)
			throws Exception {
		return new Timestamp(time.getTime() + (day * 24 * 60 * 60 * 1000));
	}
	
	/**
	 *<pre>
	 * 说       明: 根据传入的时间,得到指定增加后的小时数
	 * 医患通端: 
	 * @param time
	 * @param hour
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-11-30下午3:30:09
	 *</pre>
	 */
	public static Timestamp getTimestampForHour(Timestamp time, int hour)
			throws Exception {
		if (StringUtils.isBlank(time)) {
			time = DateUtils.getTimestamp();
		}
		return new Timestamp(time.getTime() + (hour * 24 * 60 * 1000));
	}

	/**
	 * <pre>
	 * 说   明:  根据传入的时间,得到指定增加后的分数
	 * @param time
	 * @param day
	 * @return
	 * @throws Exception
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-8-27下午4:17:18
	 * </pre>
	 */
	public static Timestamp getTimestampForMinute(Timestamp time, int minute)
			throws Exception {
		return new Timestamp(time.getTime() + (minute * 60 * 1000));
	}

	/**
	 * <pre>
	 * 说   明:  根据传入的时间,得到指定增加后的秒数
	 * @param time
	 * @param day
	 * @return
	 * @throws Exception
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-8-27下午4:17:18
	 * </pre>
	 */
	public static Timestamp getTimestampForSecond(Timestamp time, int second)
			throws Exception {
		return new Timestamp(time.getTime() + (second * 1000));
	}

	/**
	 * <pre>
	 * 说   明: 得到指定格式的时间,但时分秒都为0
	 * 创建者: 陈林林(Vickey)
	 * 日   期: 2014-6-15 下午08:51:28
	 * @param pattern
	 * @return
	 * Q  Q: 308052847
	 * </pre>
	 * 
	 * @throws Exception
	 */
	public static Timestamp getTimestamp(String pattern) throws Exception {
		return formatDate(format(new Date(), PATTERN_24_YYYY_MM_DD)
				+ PARAM_TIME_STAR, pattern);
	}

	/**
	 * Timestamp转化为String
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatTs(Timestamp date, String pattern)
			throws Exception {
		SimpleDateFormat df = new SimpleDateFormat(pattern);// 定义格式，不显示毫秒
		return df.format(date);
	}

	/**
	 * <pre>
	 * 说   明: 把时间转为指定格式
	 * 创建者: 陈林林(Vickey)
	 * 修改者: 
	 * 日   期: 2014-5-21 下午04:49:28
	 * @param astime
	 * @param asPattern
	 * @return
	 * @throws Exception
	 * </pre>
	 */
	public static String format(Timestamp astime, String asPattern)
			throws Exception {
		if (astime == null) {
			return null;
		}
		return format(astime.getTime(),
				(asPattern != null && !asPattern.equals("")) ? asPattern
						: PATTERN_24_YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * <pre>
	 * 说   明: 毫秒转为指定格式时间
	 * 创建者: 陈林林(Vickey)
	 * 修改者: 
	 * 日   期: 2014-5-21 下午04:49:09
	 * @param millisecond
	 * @param pattern
	 * @return
	 * @throws Exception
	 * </pre>
	 */
	public static String format(long millisecond, String pattern)
			throws Exception {
		SimpleDateFormat format = new SimpleDateFormat(
				pattern == null ? "yyyy-MM-dd HH:mm:ss" : pattern);
		return format.format(millisecond);
	}

	/**
	 *<pre>
	 * 说       明: 
	 * @param millisecond
	 * @param pattern
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-11-17下午5:28:43
	 *</pre>
	 */
	public static Timestamp formatLong(long millisecond, String pattern)
			throws Exception {
		SimpleDateFormat format = new SimpleDateFormat(
				pattern == null ? "yyyy-MM-dd HH:mm:ss" : pattern);
		return formatDate(format.format(millisecond));
	}

	/**
	 * <pre>
	 * 说   明: 得到当前系统时间
	 * 创建者: 陈林林(Vickey)
	 * 修改者: 
	 * 日   期: 2014-5-22 下午01:46:49
	 * @param pattern
	 * @return
	 * @throws Exception
	 * </pre>
	 */
	public static String getSysStringTime(String pattern) throws Exception {
		if (pattern == null || pattern.equals("")) {
			pattern = PATTERN_24_YYYY_MM_DD_HH_MM_SS;
		}
		Long l = System.currentTimeMillis();
		return converterMillisecondToStringTime(l, pattern);
	}
	
	public static String getSysStringTime(){
		Long l = System.currentTimeMillis();
		try {
			return converterMillisecondToStringTime(l, PATTERN_24_YYYY_MM_DD_HH_MM_SS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <pre>
	 * 说   明: 毫秒转为指定格式时间
	 * 创建者: 陈林林(Vickey)
	 * 修改者: 
	 * 日   期: 2014-5-22 下午01:47:31
	 * @param millisecond
	 * @param pattern
	 * @return
	 * </pre>
	 */
	public static String converterMillisecondToStringTime(long millisecond,
			String pattern) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat(
				pattern == null ? "yyyy-MM-dd HH:mm:ss" : pattern);
		return format.format(millisecond);
	}

	/**
	 * <pre>
	 * 说   明: 得到当前系统时间
	 * 创建者: 陈林林(Vickey)
	 * 修改者: 
	 * 日   期: 2014-5-22 下午04:14:05
	 * @param pattern
	 * @return
	 * @throws Exception
	 * </pre>
	 */
	public static Timestamp getSysTimeForTS(String pattern) throws Exception {
		if (pattern == null || pattern.equals("")) {
			pattern = PATTERN_24_YYYY_MM_DD_HH_MM_SS;
		}
		Long l = System.currentTimeMillis();
		SimpleDateFormat sf = new SimpleDateFormat(pattern);
		String strDate = sf.format(l);
		return new Timestamp(sf.parse(strDate).getTime());
	}

	/**
	 * <pre>
	 * 说   明: 计算2个日期相差的天数
	 * 创建者: 陈林林(Vickey)
	 * 修改者: 
	 * 日   期: 2014-5-23 下午04:48:19
	 * @param beginDateStr
	 * @param endDateStr
	 * @param pattern
	 * @return
	 * @throws ParseException
	 * </pre>
	 */
	public static Long getSubtractDays(String beginDateStr, String endDateStr,
			String pattern) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date beginDate = format.parse(beginDateStr);
		Date endDate = format.parse(endDateStr);
		long day = (endDate.getTime() - beginDate.getTime())
				/ (24 * 60 * 60 * 1000) + 1;
		return day;
	}

	/**
	 * <pre>
	 * 说       明: 获取两个时间的秒数
	 * 涉及版本: V3.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2016年7月4日下午3:21:59
	 * Q    Q: 308053847
	 * </pre>
	 */
	public static Long getSubtractSeconds(String beginDateStr,
			String endDateStr, String pattern) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date beginDate = format.parse(beginDateStr);
		Date endDate = format.parse(endDateStr);
		long seconds = (endDate.getTime() - beginDate.getTime()) / (1000);
		return seconds;
	}

	/**
	 * <pre>
	 * 说   明:  获取两个时间的天数
	 * @param beginDate
	 * @param endDate
	 * @param pattern
	 * @return
	 * @throws Exception
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-10-12下午9:35:24
	 * </pre>
	 */
	public static Long getSubtractDays(Timestamp beginDate, Timestamp endDate,
			String pattern) throws Exception {

		String beginDateStr = DateUtils.format(beginDate, pattern);
		String endDateStr = DateUtils.format(endDate, pattern);
		return getSubtractDays(endDateStr, beginDateStr, pattern);
	}

	/**
	 * <pre>
	 * 说       明: 两个时间差:秒
	 * @param beginDate
	 * @param endDate
	 * @param pattern
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-2-2下午5:13:26
	 * </pre>
	 */
	public static Long getSubtractSeconds(Timestamp beginDate,
			Timestamp endDate, String pattern) throws Exception {

		String beginDateStr = DateUtils.format(beginDate, pattern);
		String endDateStr = DateUtils.format(endDate, pattern);
		return getSubtractSeconds(beginDateStr, endDateStr, pattern);
	}

	public static Long getDaysFromLong(Long time) throws ParseException {
		long days = time / MILLI_PER_DAY;
		return days;
	}

	public static Long getHoursFromLong(Long time) throws ParseException {
		long hour = ( time % MILLI_PER_DAY ) / MILLI_PER_HOUR;
		return hour;
	}

	public static Long getMinsFromLong(Long time) throws ParseException {
		long min = ( time % MILLI_PER_HOUR) / MILLI_PER_MIN;
		return min;
	}

	public static Long getSecsFromLong(Long time) throws ParseException {
		long sec = ( time % MILLI_PER_MIN) / MILLI_PER_SEC;
		return sec;
	}

	/**
	 * <pre>
	 * 说   明: 根据输入的指定日期，按指定日期格式，得到指定天
	 * 创建者: 陈林林(Vickey)
	 * 修改者: 
	 * 日   期: 2014-5-23 下午05:32:25
	 * @param date
	 * @param pattern
	 * @param day
	 * @return
	 * @throws Exception
	 * </pre>
	 */
	public static String getStandardDate(String date, String pattern, int day)
			throws Exception {
		if (pattern == null || pattern.equals("")) {
			pattern = PATTERN_24_YYYY_MM_DD_HH_MM_SS;
		}
		// 当前系统时间
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(simpleDateFormat.parse(date));
		calendar.add(Calendar.DATE, day);
		Date tomorrowDate = new Date(calendar.getTimeInMillis());

		return simpleDateFormat.format(tomorrowDate);
	}

	/**
	 * <pre>
	 * 说   明: 日期转换
	 * 创建者: 陈林林(Vickey)
	 * 修改者: 
	 * 日   期: 2014-5-29 下午05:45:19
	 * @param time
	 * @param pattern
	 * @return
	 * @throws Exception
	 * </pre>
	 */
	public static String formatStr(String time, String pattern)
			throws Exception {
		if (time == null || time.equals("")) {
			return null;
		}
		Long millisecond = Timestamp.valueOf(time).getTime();
		SimpleDateFormat format = new SimpleDateFormat(
				pattern == null ? PATTERN_24_YYYYMMDDHHMMSS : pattern);
		return format.format(new Date(millisecond));
	}

	/**
	 * <pre>
	 * 说   明: 日期转换
	 * 创建者: 陈林林(Vickey)
	 * 修改者: 
	 * 日   期: 2014-5-29 下午05:53:53
	 * @param asDate
	 * @param asPattern
	 * @return
	 * @throws Exception
	 * </pre>
	 */
	public static Timestamp formatToTimestamp(String asDate, String asPattern)
			throws Exception {
		java.sql.Timestamp lStamp = null;
		if (asDate == null || asDate.length() == 0)
			lStamp = new java.sql.Timestamp(System.currentTimeMillis());
		else {
			if (asPattern == null || asPattern.length() == 0) {
				try {
					lStamp = java.sql.Timestamp.valueOf(asDate);
				} catch (Exception e) {
					lStamp = new java.sql.Timestamp(System.currentTimeMillis());
					throw new Exception(e);
				}
			} else {
				try {
					SimpleDateFormat lFormat = new SimpleDateFormat(asPattern);
					lStamp = new java.sql.Timestamp(lFormat.parse(asDate)
							.getTime());
				} catch (Exception e) {
					lStamp = new java.sql.Timestamp(System.currentTimeMillis());
					throw new Exception(e);
				}
			}
		}
		return lStamp;
	}

	/**
	 * <pre>
	 * 说   明: 判断2个日期是否相等
	 * 创建者: 陈林林(Vickey)
	 * 修改者: 
	 * 日   期: 2014-5-31 下午03:55:43
	 * @param beginDate
	 * @param endDate
	 * @return
	 * </pre>
	 */
	public boolean equalsTime(Timestamp beginDate, Timestamp endDate)
			throws Exception {
		int result = beginDate.compareTo(endDate);
		return result == 0 ? true : false;
	}

	/**
	 * <pre>
	 * 说   明: 判断2个日期是否相等
	 * 创建者: 陈林林(Vickey)
	 * 修改者: 
	 * 日   期: 2014-5-31 下午03:55:43
	 * @param beginDate
	 * @param endDate
	 * @return
	 * </pre>
	 */
	public boolean equalsTime(Date beginDate, Date endDate) throws Exception {
		int result = beginDate.compareTo(endDate);
		return result == 0 ? true : false;
	}

	/**
	 * <pre>
	 * 说   明: 判断2个日期是否相等
	 * 创建者: 陈林林(Vickey)
	 * 修改者: 
	 * 日   期: 2014-5-31 下午03:55:43
	 * @param beginDate
	 * @param endDate
	 * @return
	 * </pre>
	 */
	public boolean equalsTime(String beginDate, String endDate)
			throws Exception {
		return beginDate.equals(endDate);
	}

	/**
	 * 两个时间比较
	 * 
	 * @param date
	 * @return 0:表示时间日期相同 1:表示日期1>日期2 -1:表示日期1<日期2
	 */
	public static int compareDateWithNow(Date date1, Date date2)
			throws Exception {
		int rnum = date1.compareTo(date2);
		return rnum;
	}

	/**
	 * 将时间置为0时0分钟0秒
	 * 
	 * @param date
	 *            要转换的日期
	 * @return 转换后的日期
	 */
	public static Date getDateZero(Date date) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int y = calendar.get(Calendar.YEAR);
		int m = calendar.get(Calendar.MONTH);
		int d = calendar.get(Calendar.DATE);
		calendar.clear();
		calendar.set(Calendar.YEAR, y);
		calendar.set(Calendar.MONTH, m);
		calendar.set(Calendar.DATE, d);
		return calendar.getTime();
	}

	/**
	 * 计算字符串时间差 仅限时间格式为：yyyy-MM-dd
	 * 
	 * @param beginDateStr
	 * @param endDateStr
	 * @return
	 * @throws ParseException
	 */
	public static Long getSubtractDay(String beginDateStr, String endDateStr)
			throws ParseException {
		beginDateStr = beginDateStr.replace("-", "").trim();
		endDateStr = endDateStr.replace("-", "").trim();
		Long beginDate = Long.parseLong(beginDateStr);
		Long endDate = Long.parseLong(endDateStr);
		long day = beginDate - endDate;
		return day;
	}

	/**
	 * 获取月的第一天
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static String getMonthFirstDay(Date date) throws Exception {

		if (StringUtils.isBlank(date))
			return "";

		SimpleDateFormat df = new SimpleDateFormat(PATTERN_24_YYYYMMDD);

		String dateStr = format(date, PATTERN_24_YYYYMMDD);

		GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
		gcLast.setTime(DateUtils.format(dateStr, PATTERN_24_YYYYMMDD));
		gcLast.set(Calendar.DAY_OF_MONTH, 1);
		String day_first = df.format(gcLast.getTime());
		StringBuffer str = new StringBuffer().append(day_first).append(
				" 00:00:00");
		return str.toString();

	}

	/**
	 * 获取月的最后一天
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static String getMonthLastDay(Date date) throws Exception {
		if (StringUtils.isBlank(date))
			return "";

		Calendar cale = Calendar.getInstance();
		cale.set(Calendar.DAY_OF_MONTH, 0);// 设置为1号,当前日期既为本月第一天
		String ss = getMonthAfter(1, date);
		Date d = format(ss);
		String s = getMonthFirstDay(d);
		return s;

	}
	
	 /**
     * 得到本周周一
     *
     * @return yyyy-MM-dd
     */
    public static String getMondayOfThisWeek() {
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 1);
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        return df2.format(c.getTime());
    }


    /**
     * 得到本周周日
     *
     * @return yyyy-MM-dd
     */
    public static String getSundayOfThisWeek() {
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 7);
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        return df2.format(c.getTime());
    }
}
