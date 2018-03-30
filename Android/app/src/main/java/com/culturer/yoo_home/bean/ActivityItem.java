package com.culturer.yoo_home.bean;

/**
 * Created by Administrator on 2017/12/30 0030.
 */

public class ActivityItem {

    private Long Id;
    private Long FamilyId;
    private Long ActivityId;
    private String Title;
    private String CreateTime;
    private String Desc;
    private int Num;

    public ActivityItem() {
    }
    
    public ActivityItem(Long id, Long familyId, Long activityId, String title, String createTime, String desc, int num) {
        Id = id;
        FamilyId = familyId;
        ActivityId = activityId;
        Title = title;
        CreateTime = createTime;
        Desc = desc;
        Num = num;
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
    
    public Long getActivityId() {
        return ActivityId;
    }
    
    public void setActivityId(Long activityId) {
        ActivityId = activityId;
    }
    
    public String getTitle() {
        return Title;
    }
    
    public void setTitle(String title) {
        Title = title;
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
    
    public int getNum() {
        return Num;
    }
    
    public void setNum(int num) {
        Num = num;
    }
    
    @Override
    public String toString() {
        return "ActivityItem{" +
                "Id=" + Id +
                ", FamilyId=" + FamilyId +
                ", ActivityId=" + ActivityId +
                ", Title='" + Title + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                ", Desc='" + Desc + '\'' +
                ", Num=" + Num +
                '}';
    }
}
