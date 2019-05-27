package org.apache.cordova.pos;

import java.io.UnsupportedEncodingException;
import java.util.Hashtable;

import android.content.Context;
import android.media.AudioManager;
import android.os.Build;

public class QPOSUtil {
    static final String HEXES = "0123456789ABCDEF";

    public static String byteArray2Hex(byte[] raw) {
        if (raw == null) {
            return null;
        }
        final StringBuilder hex = new StringBuilder(2 * raw.length);
        for (final byte b : raw) {
            hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(HEXES.charAt((b & 0x0F)));
        }
        return hex.toString();
    }

    public static String convertHexToString(String hex) {

        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        for (int i = 0; i < hex.length() - 1; i += 2) {
            String output = hex.substring(i, (i + 2));
            int decimal = Integer.parseInt(output, 16);
            sb.append((char) decimal);
            temp.append(decimal);
        }

        return sb.toString();
    }

    public static byte[] HexStringToByteArray(String hexString) {//
        if (hexString == null || hexString.equals("")) {
            return new byte[] {};
        }
        if (hexString.length() == 1 || hexString.length() % 2 != 0) {
            hexString = "0" + hexString;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static byte[] CNToHex(String str) {
        byte[] b = null;
        try {
            b = str.getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return b;
    }

    public static String getHexString(byte[] b) {
        StringBuffer result = new StringBuffer("");
        for (int i = 0; i < b.length; i++) {
            result.append("0x" + Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1) + ",");
        }
        return result.substring(0, result.length() - 1);
    }

    public static byte[] IntToHex(int i) {
        String string = null;
        if (i >= 0 && i < 10) {
            string = "0" + i;
        } else {
            string = Integer.toHexString(i);
        }
        return HexStringToByteArray(string);
    }

    public static void printHexString(byte[] b) {
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            System.out.print(hex.toUpperCase());
        }

    }

    public static int byteArrayToInt(byte[] b) {
        int result = 0;
        for (int i = 0; i < b.length; i++) {
            result <<= 8;
            result |= (b[i] & 0xff); //
        }
        return result;
    }

    public static byte XorByteStream(byte[] b, int startPos, int Len) {
        byte bRet = 0x00;
        for (int i = 0; i < Len; i++) {
            bRet ^= b[startPos + i];
        }
        return bRet;
    }

    public static byte[] get(byte[] array, int offset) {
        return get(array, offset, array.length - offset);
    }

    public static byte[] get(byte[] array, int offset, int length) {
        byte[] result = new byte[length];
        System.arraycopy(array, offset, result, 0, length);
        return result;
    }

    public static void turnUpVolume(Context context, int factor) {
        int sv;

        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        sv = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, sv * factor / 10, AudioManager.FLAG_PLAY_SOUND);
    }

    public static byte[] bcd2asc(byte[] src) {
        byte[] results = new byte[src.length * 2];
        for (int i = 0; i < src.length; i++) {
            // 高Nibble转换
            if (((src[i] & 0xF0) >> 4) <= 9) {
                results[2 * i] = (byte) (((src[i] & 0xF0) >> 4) + 0x30);
            } else {
                results[2 * i] = (byte) (((src[i] & 0xF0) >> 4) + 0x37); // 大写A~F
            }
            // 低Nibble转换
            if ((src[i] & 0x0F) <= 9) {
                results[2 * i + 1] = (byte) ((src[i] & 0x0F) + 0x30);
            } else {
                results[2 * i + 1] = (byte) ((src[i] & 0x0F) + 0x37); // 大写A~F
            }
        }
        return results;
    }

    public static byte[] ecb(byte[] in) {

        byte[] a1 = new byte[8];

        for (int i = 0; i < (in.length / 8); i++) {
            byte[] temp = new byte[8];
            System.arraycopy(in, i * 8, temp, 0, temp.length);
            a1 = xor8(a1, temp);
        }
        if ((in.length % 8) != 0) {
            byte[] temp = new byte[8];
            System.arraycopy(in, (in.length / 8) * 8, temp, 0, in.length - (in.length / 8) * 8);
            a1 = xor8(a1, temp);
        }
        return bcd2asc(a1);
    }

    public static byte[] xor8(byte[] src1, byte[] src2) {
        byte[] results = new byte[8];
        for (int i = 0; i < results.length; i++) {
            results[i] = (byte) (src1[i] ^ src2[i]);
        }
        return results;
    }
}
