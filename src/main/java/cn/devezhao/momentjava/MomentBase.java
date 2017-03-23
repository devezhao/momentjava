package cn.devezhao.momentjava;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import cn.devezhao.momentjava.spec.MomentFormat;
import cn.devezhao.momentjava.spec.MomentLocale;
import cn.devezhao.momentjava.spec.MomentRelative;

/**
 * 
 * @author zhaofang123@gmail.com
 * @since 03/22/2017
 */
public class MomentBase implements MomentRelative<MomentBase>, MomentLocale<MomentBase>, MomentFormat {

	private Calendar date;
	private String locale = Locale.getDefault().toString();
	
	protected MomentBase() {
		this.date = Calendar.getInstance();
	}
	
	protected MomentBase(String source) {
		this();
		this.date.setTime(DateUtils.parse(source));
	}
	
	protected MomentBase(String source, String pattern) {
		this();
		this.date.setTime(DateUtils.parse(source, pattern));
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

	public MomentBase startOf(String unit) {
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

	public MomentBase endOf(String unit) {
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

	public String fromNow() {
		long nowLeft = Calendar.getInstance().getTimeInMillis() - this.date.getTimeInMillis();
		return nowLeft + "";
	}
	
	public String locale() {
		return this.locale;
	}
	
	public MomentBase locale(String locale) {
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
	
	public Date date() {
		return date.getTime();
	}
	
	@Override
	public String toString() {
		return String.valueOf(this.date.getTime());
	}
}
