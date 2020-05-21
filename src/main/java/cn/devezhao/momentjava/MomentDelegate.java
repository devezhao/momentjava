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
	
	private static final Map<String, Integer> UNIT2INT_MAP = new HashMap<>();
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

	@Override
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

	@Override
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

	@Override
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
			String time = String.format(I18nUtils.string(this.locale(), "RelativeTime.mm"), nowLeft);
			return String.format(inago, time);
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
	
	@Override
    public String locale() {
		return this.locale;
	}
	
	@Override
    public MomentDelegate locale(String locale) {
		this.locale = locale;
		return this;
	}
	
	// --
	// MomentFormat
	
	@Override
    public String format() {
		return format(this.format);
	}
	
	@Override
    public String format(String pattern) {
		return new SimpleDateFormat(pattern).format(date());
	}
	
	// --
	// MomentCalendar
	
	@Override
    public MomentDelegate subtract(int amount, String unit) {
		return add(-amount, unit);
	}
	
	@Override
    public MomentDelegate add(int amount, String unit) {
		if (unit.endsWith("s")) {
			unit = unit.substring(0, unit.length() - 2);
		}

        switch (unit) {
            case UNIT_YEAR:
            case UNIT_YEAR_SHORT:
                this.dateRaw.add(Calendar.YEAR, amount);
                break;
            case UNIT_MONTH:
            case UNIT_MONTH_SHORT:
                this.dateRaw.add(Calendar.MONTH, amount);
                break;
            case UNIT_DAY:
            case UNIT_DAY_SHORT:
                this.dateRaw.add(Calendar.DAY_OF_MONTH, amount);
                break;
            case UNIT_HOUR:
            case UNIT_HOUR_SHORT:
                this.dateRaw.add(Calendar.HOUR_OF_DAY, amount);
                break;
            case UNIT_MINUTE:
            case UNIT_MINUTE_SHORT:
                this.dateRaw.add(Calendar.MINUTE, amount);
                break;
            case UNIT_SECOND:
            case UNIT_SECOND_SHORT:
                this.dateRaw.add(Calendar.SECOND, amount);
                break;
            case UNIT_MILLISECOND:
            case UNIT_MILLISECOND_SHORT:
                this.dateRaw.add(Calendar.MILLISECOND, amount);
                break;
            default:
                throw new IllegalArgumentException("无效的时间单位: " + unit);
        }
		return this;
	}
	
	@Override
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

        return new SimpleDateFormat(this.format).format(this.date());
	}
	
	@Override
    public Date date() {
		return dateRaw.getTime();
	}
	
	@Override
	public String toString() {
		return String.valueOf(this.dateRaw.getTime());
	}
}
