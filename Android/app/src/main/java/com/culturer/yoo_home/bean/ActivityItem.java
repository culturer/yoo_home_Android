package com.culturer.yoo_home.bean;

/**
 * Created by Administrator on 2017/12/30 0030.
 */

public class ActivityItem {

    /**
     * Id : 1
     * FamilyId : 0
     * HomeActivityId : 1
     * FamilyActivityId : 0
     * Title : 出发
     * Time : 下午3:00
     * Desc : 坐车前往目的地
     */

    private Long Id;
    private Long FamilyId;
    private Long HomeActivityId;
    private Long FamilyActivityId;
    private String Title;
    private String Time;
    private String Desc;

    public ActivityItem() {
    }

    public ActivityItem(Long id, Long familyId, Long homeActivityId, Long familyActivityId, String title, String time, String desc) {
        Id = id;
        FamilyId = familyId;
        HomeActivityId = homeActivityId;
        FamilyActivityId = familyActivityId;
        Title = title;
        Time = time;
        Desc = desc;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public Long getFamilyId() {
        return FamilyId;
    }

    public void setFamilyId(Long FamilyId) {
        this.FamilyId = FamilyId;
    }

    public Long getHomeActivityId() {
        return HomeActivityId;
    }

    public void setHomeActivityId(Long HomeActivityId) {
        this.HomeActivityId = HomeActivityId;
    }

    public Long getFamilyActivityId() {
        return FamilyActivityId;
    }

    public void setFamilyActivityId(Long FamilyActivityId) {
        this.FamilyActivityId = FamilyActivityId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String Desc) {
        this.Desc = Desc;
    }

    @Override
    public String toString() {
        return "ActivityItem{" +
                "Id=" + Id +
                ", FamilyId=" + FamilyId +
                ", HomeActivityId=" + HomeActivityId +
                ", FamilyActivityId=" + FamilyActivityId +
                ", Title='" + Title + '\'' +
                ", Time='" + Time + '\'' +
                ", Desc='" + Desc + '\'' +
                '}';
    }
}
