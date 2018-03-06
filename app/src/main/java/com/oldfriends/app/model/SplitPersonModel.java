package com.oldfriends.app.model;

/**
 * Created by Administrator on 2016/2/24.
 */
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class SplitPersonModel implements Parcelable
{
    private String imageUrl;
    private Double money;
    private String name;

    public SplitPersonModel(){

    }
    public SplitPersonModel(String mImageUrl, String mName, Double mMoney){
        this.name = mName;
        this.imageUrl = mImageUrl;
        this.money = mMoney;
    }

//    public static final Parcelable.Creator<SplitPersonModel> CREATOR;
//
//    static
//    {
//        CREATOR =new Parcelable.Creator()
//        {
//            public SplitPersonModel createFromParcel(Parcel paramAnonymousParcel)
//            {
//                SplitPersonModel localSplitPersonModel = new SplitPersonModel();
//                localSplitPersonModel.setImageUrl(paramAnonymousParcel.readString());
//                localSplitPersonModel.setMoney(paramAnonymousParcel.readDouble());
//                localSplitPersonModel.setName(paramAnonymousParcel.readString());
//                return localSplitPersonModel;
//            }
//
//            public SplitPersonModel[] newArray(int paramAnonymousInt)
//            {
//                return new SplitPersonModel[paramAnonymousInt];
//            }
//        };
//    }

    public int describeContents()
    {
        return 0;
    }

    public String getImageUrl()
    {
        return this.imageUrl;
    }

    public Double getMoney()
    {
        return this.money;
    }

    public String getName()
    {
        return this.name;
    }

    public void setImageUrl(String paramString)
    {
        this.imageUrl = paramString;
    }

    public void setMoney(Double paramDouble)
    {
        this.money = paramDouble;
    }

    public void setName(String paramString)
    {
        this.name = paramString;
    }

    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
        paramParcel.writeString(this.name);
        paramParcel.writeString(this.imageUrl);
        paramParcel.writeDouble(this.money.doubleValue());
    }

    public static final Parcelable.Creator<SplitPersonModel> CREATOR  = new Creator<SplitPersonModel>() {
        //实现从source中创建出类的实例的功能
        @Override
        public SplitPersonModel createFromParcel(Parcel source) {
            SplitPersonModel personModel  = new SplitPersonModel();
            personModel.name = source.readString();
            personModel.imageUrl= source.readString();
            personModel.money = source.readDouble();
            return personModel;
        }
        //创建一个类型为T，长度为size的数组
        @Override
        public SplitPersonModel[] newArray(int size) {
            return new SplitPersonModel[size];
        }
    };
}
