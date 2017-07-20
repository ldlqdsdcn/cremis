package cn.cityre.edi.mis.base.util;

import java.nio.ByteBuffer;
import java.util.UUID;

public class RandomUtil {

    /**
     * @deprecated  因为有歧义, replaced by {@link #base62UUID()}
     * 
     * @return
     */
    @Deprecated
    public static String base62Key() {
        return base62UUID();
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String base62UUID() {
        UUID uuid = UUID.randomUUID();
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return Base62.encodeBase10(bb.array());
    }

    private static final String ALPHA_NUMERIC_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static final String NUMERIC_STRING = "0123456789";

    private static final String ALPHA_STRING = "abcdefghijklmnopqrstuvwxyz";

    public static String randomString(String chars, int length) {
        StringBuilder builder = new StringBuilder();
        while (length-- != 0) {
            int character = (int) (Math.random() * chars.length());
            builder.append(chars.charAt(character));
        }
        return builder.toString();
    }

    public static String randomString(int length) {
        return randomString(ALPHA_NUMERIC_STRING, length);
    }

    public static String randomAlphaString(int length) {
        return randomString(ALPHA_STRING, length);
    }

    public static String randomNumber(int length) {
        return randomString(NUMERIC_STRING, length);
    }
}
