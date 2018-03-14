package com.culturer.yoo_home.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/12/30 0030.
 */

@Entity
public class TPhoto {
    @Id
    private Long id;
    private Long albumId;
    private String fileName;
    private String fileUrl;
    private String createTime;
    @Generated(hash = 1729585453)
    public TPhoto(Long id, Long albumId, String fileName, String fileUrl,
            String createTime) {
        this.id = id;
        this.albumId = albumId;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.createTime = createTime;
    }
    @Generated(hash = 1759900668)
    public TPhoto() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getAlbumId() {
        return this.albumId;
    }
    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }
    public String getFileName() {
        return this.fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getFileUrl() {
        return this.fileUrl;
    }
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
    public String getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TPhoto{" +
                "id=" + id +
                ", albumId=" + albumId +
                ", fileName='" + fileName + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
