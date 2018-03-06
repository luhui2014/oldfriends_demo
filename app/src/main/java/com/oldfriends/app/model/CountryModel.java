package com.oldfriends.app.model;

/**
 * Created by Administrator on 2016/2/24.
 */
public class CountryModel
{
    private String areaNumber;
    private String chinaName;
    private String englishName;

    public String getAreaNumber()
    {
        return this.areaNumber;
    }

    public String getChinaName()
    {
        return this.chinaName;
    }

    public String getEnglishName()
    {
        return this.englishName;
    }

    public void setAreaNumber(String paramString)
    {
        this.areaNumber = paramString;
    }

    public void setChinaName(String paramString)
    {
        this.chinaName = paramString;
    }

    public void setEnglishName(String paramString)
    {
        this.englishName = paramString;
    }
}
