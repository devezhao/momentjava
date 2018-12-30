package cn.devezhao.momentjava;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.devezhao.momentjava.spec.MomentBase;
import cn.devezhao.momentjava.spec.MomentCalendar;
import cn.devezhao.momentjava.spec.MomentFormat;
import cn.devezhao.momentjava.spec.MomentLocale;
import cn.devezhao.momentjava.spec.MomentRelative;
import cn.devezhao.momentjava.util.DateUtils;
import cn.devezhao.momentjava.util.I18nUtils;

/**
 * 
 * @author zhaofang123@gmail.com
 * @since 03/22/2017
 */
public class MomentDelegate implements MomentBase<MomentDelegate>, MomentRelative<MomentDelegate>, MomentLocale<MomentDelegate>, MomentFormat, MomentCalendar<MomentDelegate> {

	private static String defaultLocale = "zh_CN";
	private static String defaultFormat = "yyyy-MM-dd HH:mm:ss";
	
	private Calendar dateRaw;
	private String locale = defaultLocale;
	private String format = defaultFormat;
	
	protected MomentDelegate() {
		this.dateRaw = Calendar.getInstance();
	}
	
	protected MomentDelegate(String source) {
		this();
		this.dateRaw.setTime(DateUtils.parse(source));
	}
	
	protected MomentDelegate(String source, String pattern) {
		this();
		this.dateRaw.setTime(DateUtils.parse(source, pattern));
	}
	
	protected MomentDelegate(Date date) {
		this.dateRaw = DateUtils.calendar(date);
	}
	
	/**
	 * @param locale
	 * @param format
	 */
	protected static void config(String locale, String format) {
		defaultLocale = locale;
		defaultFormat = format;
	}
	
	// --
	// MomentRelative
	
	private static final Map<String, Integer> UNIT2INT_MAP = new HashMap<String, Integer>();
	static {
		UNIT2INT_MAP.put(UNIT_YEAR, 1);
		UNIT2INT_MAP.put(UNIT_YEAR_SHORT, 1);
		UNIT2INT_MAP.put(UNIT_MONTH, 2);
		UNIT2INT_MAP.put(UNIT_MONTH_SHORT, 2);
		UNIT2INT_MAP.put(UNIT_DAY, 3);
		UNIT2INT_MAP.put(UNIT_DAY_SHORT, 3);
		UNIT2INT_MAP.put(UNIT_HOUR, 4);
		UNIT2INT_MAP.put(UNIT_HOUR_SHORT, 4);
		UNIT2INT_MAP.put(UNIT_MINUTE, 5);
		UNIT2INT_MAP.put(UNIT_MINUTE_SHORT, 5);
		UNIT2INT_MAP.put(UNIT_SECOND, 6);
		UNIT2INT_MAP.put(UNIT_SECOND_SHORT, 6);
		UNIT2INT_MAP.put(UNIT_MILLISECOND, 7);
		UNIT2INT_MAP.put(UNIT_MILLISECOND_SHORT, 7);
	}

	public MomentDelegate startOf(String unit) {
		if (!UNIT2INT_MAP.containsKey(unit)) {
			throw new IllegalArgumentException("无效的时间单位: " + unit);
		}
		int unitInt = UNIT2INT_MAP.get(unit);
		this.dateRaw.set(Calendar.MILLISECOND, 0);
		if (unitInt < 6) {
			this.dateRaw.set(Calendar.SECOND, 0);
		}
		if (unitInt < 5) {
			this.dateRaw.set(Calendar.MINUTE, 0);
		}
		if (unitInt < 4) {
			this.dateRaw.set(Calendar.HOUR_OF_DAY, 0);
		}
		if (unitInt < 3) {
			this.dateRaw.set(Calendar.DAY_OF_MONTH, 1);
		}
		if (unitInt < 2) {
			this.dateRaw.set(Calendar.MONTH, 0);
		}
		return this;
	}

	public MomentDelegate endOf(String unit) {
		if (!UNIT2INT_MAP.containsKey(unit)) {
			throw new IllegalArgumentException("无效的时间单位: " + unit);
		}
		int unitInt = UNIT2INT_MAP.get(unit);
		this.dateRaw.set(Calendar.MILLISECOND, 999);
		if (unitInt < 6) {
			this.dateRaw.set(Calendar.SECOND, 59);
		}
		if (unitInt < 5) {
			this.dateRaw.set(Calendar.MINUTE, 59);
		}
		if (unitInt < 4) {
			this.dateRaw.set(Calendar.HOUR_OF_DAY, 23);
		}
		if (unitInt < 3) {
			this.dateRaw.set(Calendar.DAY_OF_MONTH, 1);
			this.dateRaw.add(Calendar.MONTH, 1);
			this.dateRaw.add(Calendar.DAY_OF_MONTH, -1);
		}
		if (unitInt < 2) {
			this.dateRaw.set(Calendar.MONTH, 11);
		}
		return this;
	}

	// TODO 优化 月、年 的计算
	public String fromNow() {
		long nowLeft = this.dateRaw.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
		
		String inago = nowLeft < 0 
				? I18nUtils.string(this.locale(),"RelativeTime.past")
				: I18nUtils.string(this.locale(),"RelativeTime.future");
		
		nowLeft = Math.abs(nowLeft);
		nowLeft /= 1000;  // 秒
		
		if (nowLeft < 45) {
			return String.format(inago, I18nUtils.string(this.locale(), "RelativeTime.s"));
		} else if (nowLeft < 90) {
			return String.format(inago, I18nUtils.string(this.locale(), "RelativeTime.m"));
		}
		
		nowLeft /= 60;  // 分
		if (nowLeft < 45) {
			return String.format(inago, I18nUtils.string(this.locale(), nowLeft + "RelativeTime.mm"));
		} else if (nowLeft < 90) {
			return String.format(inago, I18nUtils.string(this.locale(), "RelativeTime.h"));
		} else if (nowLeft <= 60 * 21) {
			String time = String.format(I18nUtils.string(this.locale(), "RelativeTime.hh"), (int) (nowLeft / 60));
			return String.format(inago, time);
		}
		
		nowLeft /= 60;  // 时
		if (nowLeft < 24 + 12) {
			return String.format(inago, I18nUtils.string(this.locale(), "RelativeTime.d"));
		} else if (nowLeft <= 24 * 26) {
			String time = String.format(I18nUtils.string(this.locale(), "RelativeTime.dd"), (int) (nowLeft / 24));
			return String.format(inago, time);
		}
		
		nowLeft /= 24;  // 日
		if (nowLeft < 30 + 15) {
			return String.format(inago, I18nUtils.string(this.locale(), "RelativeTime.M"));
		} else if (nowLeft < 30 * 11 + 15) {
			String time = String.format(I18nUtils.string(this.locale(), "RelativeTime.MM"), (int) (nowLeft / 30));
			return String.format(inago, time);
		}
		
		nowLeft /= 30;  // 月
		if (nowLeft < 12 + 6) {
			return String.format(inago, I18nUtils.string(this.locale(), "RelativeTime.y"));
		} else {
			String time = String.format(I18nUtils.string(this.locale(), "RelativeTime.yy"), (int) (nowLeft / 12));
			return String.format(inago, time);
		}
	}
	
	// --
	// MomentLocale
	
	public String locale() {
		return this.locale;
	}
	
	public MomentDelegate locale(String locale) {
		this.locale = locale;
		return this;
	}
	
	// --
	// MomentFormat
	
	public String format() {
		return format(this.format);
	}
	
	public String format(String pattern) {
		return new SimpleDateFormat(pattern).format(date());
	}
	
	// --
	// MomentCalendar
	
	public MomentDelegate subtract(int amount, String unit) {
		return add(-amount, unit);
	}
	
	public MomentDelegate add(int amount, String unit) {
		if (unit.endsWith("s")) {
			unit = unit.substring(0, unit.length() - 2);
		}
		if (UNIT_YEAR.equals(unit) || UNIT_YEAR_SHORT.equals(unit)) {
			this.dateRaw.add(Calendar.YEAR, amount);
		} else if (UNIT_MONTH.equals(unit) || UNIT_MONTH_SHORT.equals(unit)) {
			this.dateRaw.add(Calendar.MONTH, amount);
		} else if (UNIT_DAY.equals(unit) || UNIT_DAY_SHORT.equals(unit)) {
			this.dateRaw.add(Calendar.DAY_OF_MONTH, amount);
		} else if (UNIT_HOUR.equals(unit) || UNIT_HOUR_SHORT.equals(unit)) {
			this.dateRaw.add(Calendar.HOUR_OF_DAY, amount);
		} else if (UNIT_MINUTE.equals(unit) || UNIT_MINUTE_SHORT.equals(unit)) {
			this.dateRaw.add(Calendar.MINUTE, amount);
		} else if (UNIT_SECOND.equals(unit) || UNIT_SECOND_SHORT.equals(unit)) {
			this.dateRaw.add(Calendar.SECOND, amount);
		} else if (UNIT_MILLISECOND.equals(unit) || UNIT_MILLISECOND_SHORT.equals(unit)) {
			this.dateRaw.add(Calendar.MILLISECOND, amount);
		} else {
			throw new IllegalArgumentException("无效的时间单位: " + unit);
		}
		return this;
	}
	
	public String calendar() {
		int dayLeft = DateUtils.getDayLeft(date());
		String time = new SimpleDateFormat(" HH:mm:ss").format(this.date());
		if (dayLeft == 0) {
			return I18nUtils.string(this.locale(), "Calendar.today") + time;
		} else if (dayLeft == 1) {
			return I18nUtils.string(this.locale(), "Calendar.tomorrow") + time;
		} else if (dayLeft == -1) {
			return I18nUtils.string(this.locale(), "Calendar.yesterday") + time;
		}
		
		// TODO 优化周X显示
//		if (Math.abs(dayLeft) <= 6) {
//			int wd = this.dateRaw.get(Calendar.DAY_OF_WEEK) - 1;
//			if (wd < 0) {
//				wd = 0;
//			}
//			return I18nUtils.string(this.locale(), "Calendar.weekday" + wd) + time;
//		}
		
		String date = new SimpleDateFormat(this.format).format(this.date());
		return date;
	}
	
	public Date date() {
		return dateRaw.getTime();
	}
	
	@Override
	public String toString() {
		return String.valueOf(this.dateRaw.getTime());
	}
}
