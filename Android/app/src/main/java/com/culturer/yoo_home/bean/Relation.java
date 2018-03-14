package com.culturer.yoo_home.bean;

/**
 * Created by Administrator on 2017/12/30 0030.
 */

public class Relation {

    /**
     * Id : 12
     * FatherId : 0
     * MotherId : 0
     * MateId : 0
     * UserId : 12
     */

    private Long Id;
    private Long FatherId;
    private Long MotherId;
    private Long MateId;
    private Long UserId;

    public Relation(Long id, Long fatherId, Long motherId, Long mateId, Long userId) {
        Id = id;
        FatherId = fatherId;
        MotherId = motherId;
        MateId = mateId;
        UserId = userId;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getFatherId() {
        return FatherId;
    }

    public void setFatherId(Long fatherId) {
        FatherId = fatherId;
    }

    public Long getMotherId() {
        return MotherId;
    }

    public void setMotherId(Long motherId) {
        MotherId = motherId;
    }

    public Long getMateId() {
        return MateId;
    }

    public void setMateId(Long mateId) {
        MateId = mateId;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    @Override
    public String toString() {
        return "Relation{" +
                "Id=" + Id +
                ", FatherId=" + FatherId +
                ", MotherId=" + MotherId +
                ", MateId=" + MateId +
                ", UserId=" + UserId +
                '}';
    }
}
