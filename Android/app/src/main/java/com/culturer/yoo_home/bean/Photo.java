package com.culturer.yoo_home.bean;

/**
 * Created by Administrator on 2017/12/30 0030.
 */

public class Photo {

    /**
     * Id : 1
     * AlbumId : 1
     * FileName : dfsad
     * FileUrl : /1sa3d.p.g
     * CreateTime : 2017-12-28T11:25:29+08:00
     */

    private Long Id;
    private Long AlbumId;
    private String FileName;
    private String FileUrl;
    private String CreateTime;


    public Photo(Long id, Long albumId, String fileName, String fileUrl, String createTime) {
        Id = id;
        AlbumId = albumId;
        FileName = fileName;
        FileUrl = fileUrl;
        CreateTime = createTime;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getAlbumId() {
        return AlbumId;
    }

    public void setAlbumId(Long albumId) {
        AlbumId = albumId;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getFileUrl() {
        return FileUrl;
    }

    public void setFileUrl(String fileUrl) {
        FileUrl = fileUrl;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

//    public TPhoto str2TPhoto(){
//        return new TPhoto(Id,AlbumId,FileName,FileUrl,CreateTime);
//    }

    @Override
    public String toString() {
        return "Photo{" +
                "Id=" + Id +
                ", AlbumId=" + AlbumId +
                ", FileName='" + FileName + '\'' +
                ", FileUrl='" + FileUrl + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                '}';
    }
}
