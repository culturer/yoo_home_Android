package com.culturer.yoo_home.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/12/30 0030.
 */

@Entity
public class TActivity {
    @Id
    private Long id;
    private Long familyId;
    private String createTime;
    private String desc;
    private Long addressId;
    @Generated(hash = 123710340)
    public TActivity(Long id, Long familyId, String createTime, String desc,
            Long addressId) {
        this.id = id;
        this.familyId = familyId;
        this.createTime = createTime;
        this.desc = desc;
        this.addressId = addressId;
    }
    @Generated(hash = 879791674)
    public TActivity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getFamilyId() {
        return this.familyId;
    }
    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }
    public String getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public String getDesc() {
        return this.desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public Long getAddressId() {
        return this.addressId;
    }
    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    @Override
    public String toString() {
        return "TActivity{" +
                "id=" + id +
                ", familyId=" + familyId +
                ", createTime='" + createTime + '\'' +
                ", desc='" + desc + '\'' +
                ", addressId=" + addressId +
                '}';
    }
}
