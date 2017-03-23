package cn.devezhao.momentjava;

/**
 * 
 * @author zhaofang123@gmail.com
 * @since 03/23/2017
 */
public class DateFormatException extends RuntimeException {
	private static final long serialVersionUID = 1243259279638323854L;

	public DateFormatException() {
		super();
	}

	public DateFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DateFormatException(String message, Throwable cause) {
		super(message, cause);
	}

	public DateFormatException(String message) {
		super(message);
	}

	public DateFormatException(Throwable cause) {
		super(cause);
	}
}
