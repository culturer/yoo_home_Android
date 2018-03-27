package com.culturer.yoo_home.bean;

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
    private boolean ActivityType;
    private Long FamilyId;
    private String CreateTime;
    private String Desc;
    private Long AddressId;

    public Activity() {
    }

    public Activity(Long id, boolean activityType, Long familyId, String createTime, String desc, Long addressId) {
        Id = id;
        ActivityType = activityType;
        FamilyId = familyId;
        CreateTime = createTime;
        Desc = desc;
        AddressId = addressId;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public boolean isActivityType() {
        return ActivityType;
    }

    public void setActivityType(boolean activityType) {
        ActivityType = activityType;
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
                ", ActivityType=" + ActivityType +
                ", FamilyId=" + FamilyId +
                ", CreateTime='" + CreateTime + '\'' +
                ", Desc='" + Desc + '\'' +
                ", AddressId=" + AddressId +
                '}';
    }
}
