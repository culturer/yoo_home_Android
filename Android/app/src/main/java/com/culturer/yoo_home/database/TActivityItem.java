package com.culturer.yoo_home.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/12/30 0030.
 */

@Entity
public class TActivityItem {
    @Id
    private Long id;
    private Long homeActivityId;
    private Long familyActivityId;
    @Generated(hash = 723677254)
    public TActivityItem(Long id, Long homeActivityId, Long familyActivityId) {
        this.id = id;
        this.homeActivityId = homeActivityId;
        this.familyActivityId = familyActivityId;
    }
    @Generated(hash = 140943825)
    public TActivityItem() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getHomeActivityId() {
        return this.homeActivityId;
    }
    public void setHomeActivityId(Long homeActivityId) {
        this.homeActivityId = homeActivityId;
    }
    public Long getFamilyActivityId() {
        return this.familyActivityId;
    }
    public void setFamilyActivityId(Long familyActivityId) {
        this.familyActivityId = familyActivityId;
    }

    @Override
    public String toString() {
        return "TActivityItem{" +
                "id=" + id +
                ", homeActivityId=" + homeActivityId +
                ", familyActivityId=" + familyActivityId +
                '}';
    }
}
