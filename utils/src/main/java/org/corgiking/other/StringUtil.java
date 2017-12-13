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

	public static void main(String[] args) {

		new Thread() {
			public void run() {
				int i = 0;
				while (++i < 10000) {
					String str = StringUtil.strcat("Corgi", " King");
					System.out.println(str);
				}
			};
		}.start();

		new Thread() {
			public void run() {
				int i = 0;
				while (++i < 10000) {
					String str = StringUtil.strcat("Corgi", " King");
					System.out.println(str);
				}
			};
		}.start();
	}
}
