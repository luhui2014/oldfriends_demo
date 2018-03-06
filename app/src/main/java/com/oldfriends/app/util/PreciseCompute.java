package com.oldfriends.app.util;

/**
 * Created by Administrator on 2016/2/23.
 */
import java.math.BigDecimal;

public class PreciseCompute
{
    private static final int DEF_DIV_SCALE = 10;

    public static double add(double paramDouble1, double paramDouble2)
    {
        BigDecimal localBigDecimal1 = new BigDecimal(Double.toString(paramDouble1));
        BigDecimal localBigDecimal2 = new BigDecimal(Double.toString(paramDouble2));
        return localBigDecimal1.add(localBigDecimal2).doubleValue();
    }

    public static double div(double paramDouble1, double paramDouble2)
    {
        return div(paramDouble1, paramDouble2, 10);
    }

    public static double div(double paramDouble1, double paramDouble2, int paramInt)
    {
        if (paramInt < 0)
        {
            IllegalArgumentException localIllegalArgumentException = new IllegalArgumentException("The scale must be a positive integer or zero");
            throw localIllegalArgumentException;
        }
        BigDecimal localBigDecimal1 = new BigDecimal(Double.toString(paramDouble1));
        BigDecimal localBigDecimal2 = new BigDecimal(Double.toString(paramDouble2));
        return localBigDecimal1.divide(localBigDecimal2, paramInt, 4).doubleValue();
    }

    public static double mul(double paramDouble1, double paramDouble2)
    {
        BigDecimal localBigDecimal1 = new BigDecimal(Double.toString(paramDouble1));
        BigDecimal localBigDecimal2 = new BigDecimal(Double.toString(paramDouble2));
        return localBigDecimal1.multiply(localBigDecimal2).doubleValue();
    }

    public static double round(double paramDouble, int paramInt)
    {
        if (paramInt < 0)
        {
            IllegalArgumentException localIllegalArgumentException = new IllegalArgumentException("The scale must be a positive integer or zero");
            throw localIllegalArgumentException;
        }
        BigDecimal localBigDecimal1 = new BigDecimal(Double.toString(paramDouble));
        BigDecimal localBigDecimal2 = new BigDecimal("1");
        return localBigDecimal1.divide(localBigDecimal2, paramInt, 4).doubleValue();
    }

    public static double sub(double paramDouble1, double paramDouble2)
    {
        BigDecimal localBigDecimal1 = new BigDecimal(Double.toString(paramDouble1));
        BigDecimal localBigDecimal2 = new BigDecimal(Double.toString(paramDouble2));
        return localBigDecimal1.subtract(localBigDecimal2).doubleValue();
    }
}
