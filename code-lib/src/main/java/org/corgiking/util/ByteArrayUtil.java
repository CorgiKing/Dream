package org.corgiking.util;

public class ByteArrayUtil {

	/**
	 * 在dest数组后追加length的数据
	 * 
	 * @param src
	 * @param srcPos
	 * @param dest
	 * @param length
	 * @return
	 */
	public static byte[] appendArray(byte[] src, int srcPos, byte[] dest, int length) {
		if (src == null || src.length == 0 || length == 0) {
			return dest;
		}
		int destPos = 0;
		if (dest != null) {
			byte[] buf = new byte[dest.length + length];
			System.arraycopy(dest, 0, buf, 0, dest.length);
			destPos = dest.length;
			dest = buf;
		} else {
			dest = new byte[length];
		}
		System.arraycopy(src, 0, dest, destPos, length);
		return dest;
	}

	/**
	 * 在dest数组从destPos开始添加length的数据
	 * 
	 * @param src
	 * @param srcPos
	 * @param dest
	 * @param destPos
	 * @param length
	 * @return
	 */
	public static byte[] addArray(byte[] src, int srcPos, byte[] dest, int destPos, int length) {

		if (dest.length < destPos + length) {
			byte[] buf = new byte[destPos + length];
			System.arraycopy(dest, 0, buf, 0, destPos);
			dest = buf;
		}
		System.arraycopy(src, 0, dest, destPos, length);
		return dest;
	}

	public static void main(String[] args) {
		System.arraycopy(null, 0, null, 0, 5);
	}
}
