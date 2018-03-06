package com.oldfriends.app.util;

/**
 * Created by Administrator on 2016/2/23.
 */
public class SecurityUtil
{
    public static String deCode(String paramString)
    {
        return toHexString(hexStringToByte(paramString));
    }

    public static byte[] hexStringToByte(String paramString)
    {
        int i = paramString.length() / 2;
        byte[] arrayOfByte = new byte[i];
        char[] arrayOfChar = paramString.toCharArray();
        for (int j = 0; j < i; j++)
        {
            int k = 2 * j;
            arrayOfByte[j] = ((byte)(toByte(arrayOfChar[k]) << 4 | toByte(arrayOfChar[(k + 1)])));
        }
        return arrayOfByte;
    }

    public static String str2HexStr(String paramString)
    {
        char[] arrayOfChar = "0123456789ABCDEF".toCharArray();
        StringBuilder localStringBuilder = new StringBuilder("");
        byte[] arrayOfByte = paramString.getBytes();
        for (int i = 0; i < arrayOfByte.length; i++)
        {
            localStringBuilder.append(arrayOfChar[((0xF0 & arrayOfByte[i]) >> 4)]);
            localStringBuilder.append(arrayOfChar[(0xF & arrayOfByte[i])]);
        }
        return localStringBuilder.toString();
    }

    private static int toByte(char paramChar)
    {
        return (byte)"0123456789ABCDEF".indexOf(paramChar);
    }

    public static String toHexString(byte paramByte)
    {
        String str = Integer.toHexString(paramByte & 0xFF);
        if (str.length() == 1)
        {
            StringBuilder localStringBuilder = new StringBuilder();
            return "0" + str;
        }
        return str;
    }

    public static String toHexString(byte[] paramArrayOfByte)
    {
        StringBuffer localStringBuffer = new StringBuffer();
        for (int i = 0; i < paramArrayOfByte.length; i++)
            localStringBuffer.append(toHexString(paramArrayOfByte[i]));
        return localStringBuffer.toString();
    }
}
