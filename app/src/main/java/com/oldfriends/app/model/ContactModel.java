package com.oldfriends.app.model;

/**
 * Created by Administrator on 2016/2/24.
 */
public class ContactModel
{
    private int contactId;
    private String contactName;
    private String friendId = "";
    private String friendNickName = "";
    private String phoneNumber;
    private Long photoId;

    public int getContactId()
    {
        return this.contactId;
    }

    public String getContactName()
    {
        return this.contactName;
    }

    public String getFriendId()
    {
        return this.friendId;
    }

    public String getFriendNickName()
    {
        return this.friendNickName;
    }

    public String getPhoneNumber()
    {
        return this.phoneNumber;
    }

    public Long getPhotoId()
    {
        return this.photoId;
    }

    public void setContactId(int paramInt)
    {
        this.contactId = paramInt;
    }

    public void setContactName(String paramString)
    {
        this.contactName = paramString;
    }

    public void setFriendId(String paramString)
    {
        this.friendId = paramString;
    }

    public void setFriendNickName(String paramString)
    {
        this.friendNickName = paramString;
    }

    public void setPhoneNumber(String paramString)
    {
        this.phoneNumber = paramString;
    }

    public void setPhotoId(Long paramLong)
    {
        this.photoId = paramLong;
    }
}