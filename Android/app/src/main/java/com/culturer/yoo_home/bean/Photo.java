package com.culturer.yoo_home.bean;

/**
 * Created by Administrator on 2017/12/30 0030.
 */

public class Photo {
    
    private Long Id;
    private Long AlbumId;
    private Long UserId;
    private Long FamilyId;
    private Long ArticleId;
    private String FileName;
    private String FileUrl;
    private String CreateTime;
    
    public Photo(Long id, Long albumId, Long userId, Long familyId, Long articleId, String fileName, String fileUrl, String createTime) {
        Id = id;
        AlbumId = albumId;
        UserId = userId;
        FamilyId = familyId;
        ArticleId = articleId;
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
    
    public Long getUserId() {
        return UserId;
    }
    
    public void setUserId(Long userId) {
        UserId = userId;
    }
    
    public Long getFamilyId() {
        return FamilyId;
    }
    
    public void setFamilyId(Long familyId) {
        FamilyId = familyId;
    }
    
    public Long getArticleId() {
        return ArticleId;
    }
    
    public void setArticleId(Long articleId) {
        ArticleId = articleId;
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
    
    @Override
    public String toString() {
        return "Photo{" +
                "Id=" + Id +
                ", AlbumId=" + AlbumId +
                ", UserId=" + UserId +
                ", FamilyId=" + FamilyId +
                ", ArticleId=" + ArticleId +
                ", FileName='" + FileName + '\'' +
                ", FileUrl='" + FileUrl + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                '}';
    }
    
}
