package cl.auter.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Util {
	public static int saveWord(final byte[] b, int offset, int value) {
        int lo = value & 0xff;
        int hi = (value >> 8) & 0xff;
        b[offset++] = (byte) lo;
        b[offset++] = (byte) hi;
        return offset;
    }

    public static int loadWord(final byte[] b, int offset) {
        int lo = b[offset++] & 0xff;
        int hi = b[offset] & 0xff;
        return ((hi << 8) | lo);
    }

    public static void saveInt(final byte[] b, int offset, int value) {
        int lolo = value & 0xff;
        int lohi = (value >> 8) & 0xff;
        int hilo = (value >> 16) & 0xff;
        int hihi = (value >> 24) & 0xff;
        b[offset++] = (byte) lolo;
        b[offset++] = (byte) lohi;
        b[offset++] = (byte) hilo;
        b[offset] = (byte) hihi;
    }

    public static int loadInt(final byte[] b, int offset) {
        int lolo = b[offset++] & 0xff;
        int lohi = b[offset++] & 0xff;
        int hilo = b[offset++] & 0xff;
        int hihi = b[offset] & 0xff;
        return (hihi << 24) | (hilo << 16) | (lohi << 8) | lolo;
    }

    public static String createMD5Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[] enc = md.digest();
            String md5Sum = Base64.getEncoder().encodeToString(enc);
            return md5Sum;
        } catch (NoSuchAlgorithmException nsae) {
        }
        return "ICy5YqxZB1uWSwcVLSNLcA==";
    }
}
