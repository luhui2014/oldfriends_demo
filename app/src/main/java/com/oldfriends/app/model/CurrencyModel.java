package com.oldfriends.app.model;

/**
 * Created by Administrator on 2016/2/24.
 */
public class CurrencyModel
{
    private String currencyName;
    private String englishName;
    private String shortName;
    private String symbol;

    public String getCurrencyName()
    {
        return this.currencyName;
    }

    public String getEnglishName()
    {
        return this.englishName;
    }

    public String getShortName()
    {
        return this.shortName;
    }

    public String getSymbol()
    {
        return this.symbol;
    }

    public void setCurrencyName(String paramString)
    {
        this.currencyName = paramString;
    }

    public void setEnglishName(String paramString)
    {
        this.englishName = paramString;
    }

    public void setShortName(String paramString)
    {
        this.shortName = paramString;
    }

    public void setSymbol(String paramString)
    {
        this.symbol = paramString;
    }
}
