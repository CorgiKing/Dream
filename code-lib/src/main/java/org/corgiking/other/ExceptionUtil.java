package org.corgiking.other;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtil {

	/**
	 * 将错误堆栈信息转换成字符串
	 * 
	 * @param throwable
	 * @return
	 */
	public static String getStackTraceAsString(Throwable throwable) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		throwable.printStackTrace(printWriter);
		return stringWriter.getBuffer().toString();
	}

}
