package com.culturer.yoo_home.bean;

import com.culturer.yoo_home.database.TActivityItem;

/**
 * Created by Administrator on 2017/12/30 0030.
 */

public class ActivityItem {

    /**
     * Id : 1
     * HomeActivityId : 1
     * FamilyActivityId : 0
     */

    private Long Id;
    private Long HomeActivityId;
    private Long FamilyActivityId;

    public ActivityItem(Long id, Long homeActivityId, Long familyActivityId) {
        Id = id;
        HomeActivityId = homeActivityId;
        FamilyActivityId = familyActivityId;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getHomeActivityId() {
        return HomeActivityId;
    }

    public void setHomeActivityId(Long homeActivityId) {
        HomeActivityId = homeActivityId;
    }

    public Long getFamilyActivityId() {
        return FamilyActivityId;
    }

    public void setFamilyActivityId(Long familyActivityId) {
        FamilyActivityId = familyActivityId;
    }

    public TActivityItem str2TActivityItem(){
        return new TActivityItem(Id,HomeActivityId,FamilyActivityId);
    }

    @Override
    public String toString() {
        return "ActivityItem{" +
                "Id=" + Id +
                ", HomeActivityId=" + HomeActivityId +
                ", FamilyActivityId=" + FamilyActivityId +
                '}';
    }
}
