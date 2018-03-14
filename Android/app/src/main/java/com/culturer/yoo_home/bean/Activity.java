package com.culturer.yoo_home.bean;

import com.culturer.yoo_home.database.TActivity;

/**
 * Created by Administrator on 2017/12/30 0030.
 */

public class Activity {

    /**
     * Id : 1
     * FamilyId : 1
     * CreateTime : 2017-12-28T11:29:36+08:00
     * Desc : try
     * AddressId : 1
     */

    private Long Id;
    private Long FamilyId;
    private String CreateTime;
    private String Desc;
    private Long AddressId;

    public Activity() {
    }

    public Activity(Long id, Long familyId, String createTime, String desc, Long addressId) {
        Id = id;
        FamilyId = familyId;
        CreateTime = createTime;
        Desc = desc;
        AddressId = addressId;
    }

    public TActivity str2TActivity(){
        return new TActivity(Id,FamilyId,CreateTime,Desc,AddressId);
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getFamilyId() {
        return FamilyId;
    }

    public void setFamilyId(Long familyId) {
        FamilyId = familyId;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public Long getAddressId() {
        return AddressId;
    }

    public void setAddressId(Long addressId) {
        AddressId = addressId;
    }



    @Override
    public String toString() {
        return "Activity{" +
                "Id=" + Id +
                ", FamilyId=" + FamilyId +
                ", CreateTime='" + CreateTime + '\'' +
                ", Desc='" + Desc + '\'' +
                ", AddressId=" + AddressId +
                '}';
    }
}
