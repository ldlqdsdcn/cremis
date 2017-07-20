package cn.cityre.edi.mis.base.util;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * base62 encode & decode
 * 
 * @author wesley
 *
 */
public class Base62 {
	final private static String characters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	final private static BigInteger ZERO = BigInteger.valueOf(0L);
	final private static BigInteger BASE = BigInteger.valueOf(62L);
	final private static Integer LENGTH = 16;

	/**
	 * 将byte 数组转换为base62字符串，数组必须为16个字节，可以是md5或uuid。
	 * 
	 * @param b10bytes
	 * @return base62字符串，注意：字符串的长度是不确定的，一般为11个字符。
	 */
	public static String encodeBase10(byte[] b10bytes) {
		if (b10bytes.length != LENGTH) {
			throw new IllegalArgumentException("bytes must be " + LENGTH + " bytes length");
		}

		String ret = "";
		BigInteger bi = new BigInteger(1, b10bytes);
		while (bi.compareTo(ZERO) != 0) {
			BigInteger bia[] = bi.divideAndRemainder(BASE);
			bi = bia[0];
			ret = characters.charAt(bia[1].intValue()) + ret;
		}
		return ret;
	}

	/**
	 * 将base62字符串解码为byte array。
	 * 
	 * @param b62
	 *            必须是encodeBase10生成的字符串
	 * @return
	 */
	public static byte[] decodeBase62(String b62) {
		for (char character : b62.toCharArray()) {
			if (!characters.contains(String.valueOf(character))) {
				throw new IllegalArgumentException("Invalid character(s) in string: " + character);
			}
		}
		BigInteger ret = BigInteger.valueOf(0L);
		b62 = new StringBuffer(b62).reverse().toString();
		BigInteger count = BigInteger.valueOf(1L);
		for (char character : b62.toCharArray()) {
			ret = ret.add(BigInteger.valueOf(characters.indexOf(character)).multiply(count));
			count = count.multiply(BASE);
		}
		final byte[] result = ret.toByteArray();
		if (result.length > LENGTH) {
			return Arrays.copyOfRange(result, result.length - LENGTH, result.length);
		} else if (result.length < LENGTH) {
			ByteBuffer buf = ByteBuffer.allocate(LENGTH);
			for (int i = 0; i < (LENGTH - result.length); i++) {
				buf.put((byte) 0);
			}
			buf.put(result);
			return buf.array();
		} else {
			return result;
		}
	}
}
