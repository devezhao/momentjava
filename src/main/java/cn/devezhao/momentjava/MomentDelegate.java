package cn.devezhao.momentjava;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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
public class MomentDelegate implements MomentRelative<MomentDelegate>, MomentLocale<MomentDelegate>, MomentFormat, MomentCalendar<MomentDelegate> {

	private Calendar date;
	private String locale = Locale.getDefault().toString();
	
	protected MomentDelegate() {
		this.date = Calendar.getInstance();
	}
	
	protected MomentDelegate(String source) {
		this();
		this.date.setTime(DateUtils.parse(source));
	}
	
	protected MomentDelegate(String source, String pattern) {
		this();
		this.date.setTime(DateUtils.parse(source, pattern));
	}
	
	protected MomentDelegate(Date date) {
		this.date = DateUtils.calendar(date);
	}
	
	// --
	// MomentRelative
	
	static final Map<String, Integer> UNIT_INT = new HashMap<String, Integer>();
	static {
		UNIT_INT.put("year", 		1);
		UNIT_INT.put("month", 		2);
		UNIT_INT.put("day", 		3);
		UNIT_INT.put("hour", 		4);
		UNIT_INT.put("minute", 		5);
		UNIT_INT.put("second", 		6);
	}

	public MomentDelegate startOf(String unit) {
		int unitInt = UNIT_INT.get(unit);
		this.date.set(Calendar.MILLISECOND, 0);
		if (unitInt < 6) {
			this.date.set(Calendar.SECOND, 0);
		}
		if (unitInt < 5) {
			this.date.set(Calendar.MINUTE, 0);
		}
		if (unitInt < 4) {
			this.date.set(Calendar.HOUR_OF_DAY, 0);
		}
		if (unitInt < 3) {
			this.date.set(Calendar.DAY_OF_MONTH, 1);
		}
		if (unitInt < 2) {
			this.date.set(Calendar.MONTH, 0);
		}
		return this;
	}

	public MomentDelegate endOf(String unit) {
		int unitInt = UNIT_INT.get(unit);
		this.date.set(Calendar.MILLISECOND, 999);
		if (unitInt < 6) {
			this.date.set(Calendar.SECOND, 59);
		}
		if (unitInt < 5) {
			this.date.set(Calendar.MINUTE, 59);
		}
		if (unitInt < 4) {
			this.date.set(Calendar.HOUR_OF_DAY, 23);
		}
		if (unitInt < 3) {
			this.date.set(Calendar.DAY_OF_MONTH, 1);
			this.date.add(Calendar.MONTH, 1);
			this.date.add(Calendar.DAY_OF_MONTH, -1);
		}
		if (unitInt < 2) {
			this.date.set(Calendar.MONTH, 11);
		}
		return this;
	}

	// TODO 优化 月、年 的计算
	public String fromNow() {
		long nowLeft = Calendar.getInstance().getTimeInMillis() - this.date.getTimeInMillis();
		
		String inago = nowLeft < 0 ? I18nUtils.string(this.locale(),"RelativeTime.past")
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
	
	public String locale() {
		return this.locale;
	}
	
	public MomentDelegate locale(String locale) {
		this.locale = locale;
		return this;
	}
	
	public String format() {
		return new SimpleDateFormat("yyyyMMdd HH:mm:ss SSS").format(date());
	}
	
	public String format(String pattern) {
		// TODO Auto-generated method stub
		return format();
	}
	
	public MomentDelegate subtract(int amount, String unit) {
		return add(-amount, unit);
	}
	
	public MomentDelegate add(int amount, String unit) {
		if ("years".equals(unit) || "y".equals(unit)) {
			this.date.add(Calendar.YEAR, -amount);
		} else if ("months".equals(unit) || "M".equals(unit)) {
			this.date.add(Calendar.MONTH, -amount);
		} else if ("days".equals(unit) || "d".equals(unit)) {
			this.date.add(Calendar.DAY_OF_MONTH, -amount);
		} else if ("hours".equals(unit) || "h".equals(unit)) {
			this.date.add(Calendar.HOUR_OF_DAY, -amount);
		} else if ("minutes".equals(unit) || "m".equals(unit)) {
			this.date.add(Calendar.MINUTE, -amount);
		} else if ("seconds".equals(unit) || "s".equals(unit)) {
			this.date.add(Calendar.SECOND, -amount);
		} else if ("milliseconds".equals(unit) || "ms".equals(unit)) {
			this.date.add(Calendar.MILLISECOND, -amount);
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
		
		if (Math.abs(dayLeft) <= 6) {
			int wd = this.date.get(Calendar.DAY_OF_WEEK);
			return I18nUtils.string(this.locale(), "Calendar.weekday" + wd) + time;
		}
		
		String date = new SimpleDateFormat("yyyy-MM-dd").format(this.date());
		return date;
	}
	
	public Date date() {
		return date.getTime();
	}
	
	@Override
	public String toString() {
		return String.valueOf(this.date.getTime());
	}
}
