package com.culturer.yoo_home.bean;

/**
 * Created by Administrator on 2017/12/30 0030.
 */

public class Family {

    /**
     * Id : 6
     * FamilyName : YooHome~
     * FamilyNotifyTitle : 欢迎来到yooplus~
     * FamilyNotifyContent : 欢迎来到yooplus~ 2017-12-28 08:28:15
     * CreateTime : 2017-12-28T00:28:16+08:00
     * Msg :
     */

    private int Id;
    private String FamilyName;
    private String FamilyNotifyTitle;
    private String FamilyNotifyContent;
    private String CreateTime;
    private String Msg;

    public Family(int id, String familyName, String familyNotifyTitle, String familyNotifyContent, String createTime, String msg) {
        Id = id;
        FamilyName = familyName;
        FamilyNotifyTitle = familyNotifyTitle;
        FamilyNotifyContent = familyNotifyContent;
        CreateTime = createTime;
        Msg = msg;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFamilyName() {
        return FamilyName;
    }

    public void setFamilyName(String familyName) {
        FamilyName = familyName;
    }

    public String getFamilyNotifyTitle() {
        return FamilyNotifyTitle;
    }

    public void setFamilyNotifyTitle(String familyNotifyTitle) {
        FamilyNotifyTitle = familyNotifyTitle;
    }

    public String getFamilyNotifyContent() {
        return FamilyNotifyContent;
    }

    public void setFamilyNotifyContent(String familyNotifyContent) {
        FamilyNotifyContent = familyNotifyContent;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    @Override
    public String toString() {
        return "Family{" +
                "Id=" + Id +
                ", FamilyName='" + FamilyName + '\'' +
                ", FamilyNotifyTitle='" + FamilyNotifyTitle + '\'' +
                ", FamilyNotifyContent='" + FamilyNotifyContent + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                ", Msg='" + Msg + '\'' +
                '}';
    }
}
