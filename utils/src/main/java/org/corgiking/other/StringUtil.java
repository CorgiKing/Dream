package org.corgiking.other;

public class StringUtil {

	public static String strcat(String... strs) {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.setLength(0);
		for (String str : strs) {
			strBuilder.append(str);
		}
		return strBuilder.toString();
	}
}
