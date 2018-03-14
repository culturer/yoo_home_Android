package com.culturer.yoo_home.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/12/30 0030.
 */
@Entity
public class TArrangement {
    @Id
    private Long id;
    private Long userId;
    private String desc;
    private String createTime;
    @Generated(hash = 2012247466)
    public TArrangement(Long id, Long userId, String desc, String createTime) {
        this.id = id;
        this.userId = userId;
        this.desc = desc;
        this.createTime = createTime;
    }
    @Generated(hash = 1629564062)
    public TArrangement() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserId() {
        return this.userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getDesc() {
        return this.desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


}
