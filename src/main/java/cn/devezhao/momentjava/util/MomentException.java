package cn.devezhao.momentjava.util;

/**
 * 
 * @author zhaofang123@gmail.com
 * @since 05/03/2017
 */
public class MomentException extends RuntimeException {
	private static final long serialVersionUID = 1243259279638323854L;

	public MomentException() {
		super();
	}

	public MomentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MomentException(String message, Throwable cause) {
		super(message, cause);
	}

	public MomentException(String message) {
		super(message);
	}

	public MomentException(Throwable cause) {
		super(cause);
	}
}
