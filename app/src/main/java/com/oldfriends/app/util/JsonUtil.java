package com.oldfriends.app.util;

/**
 * Created by Administrator on 2016/2/23.
 */
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class JsonUtil
{
    private static ObjectMapper om;

    static
    {
        ObjectMapper localObjectMapper = new ObjectMapper();
        om = localObjectMapper;
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> T deSerialize(String paramString, Class<T> paramClass)
    {
        if (paramString == null)
            return null;
        try
        {
            Object localObject = om.readValue(paramString, paramClass);
            return (T)localObject;
        }
        catch (IOException localIOException)
        {
            RuntimeException localRuntimeException = new RuntimeException(localIOException);
            throw localRuntimeException;
        }
    }

    public static <T> T deSerialize(String paramString, TypeReference<T> paramTypeReference)
    {
        try
        {
            Object localObject = om.readValue(paramString, paramTypeReference);
            return (T)localObject;
        }
        catch (IOException localIOException)
        {
            RuntimeException localRuntimeException = new RuntimeException(localIOException);
            throw localRuntimeException;
        }
    }

    public static Map<String, ?> deSerializeMap(String paramString)
    {
        try
        {
            Map localMap = (Map)om.readValue(paramString, Map.class);
            return localMap;
        }
        catch (Exception localException)
        {
            RuntimeException localRuntimeException = new RuntimeException(localException);
            throw localRuntimeException;
        }
    }

    public static byte[] writeValueAsBytes(Object paramObject)
    {
        try
        {
            byte[] arrayOfByte = om.writeValueAsBytes(paramObject);
            return arrayOfByte;
        }
        catch (Exception localException)
        {
            RuntimeException localRuntimeException = new RuntimeException(localException);
            throw localRuntimeException;
        }
    }

    public static String writeValueAsString(Object paramObject)
    {
        return writeValueAsString(paramObject, null);
    }

    public static String writeValueAsString(Object paramObject, String paramString)
    {
        try
        {
            byte[] arrayOfByte = om.writeValueAsBytes(paramObject);
            if (paramString == null);
            for (String str1 = "utf-8"; ; str1 = paramString)
            {
                String str2 = new String(arrayOfByte, str1);
                return str2;
            }
        }
        catch (Exception localException)
        {
            RuntimeException localRuntimeException = new RuntimeException(localException);
            throw localRuntimeException;
        }
    }
}
