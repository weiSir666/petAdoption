package pojo;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 * 领养宠物类
 */
public class Adopt {
    private int adoptId;
    private int userId;
    private int petId;
    private int state; //状态，用于表示是否被领养
    private Date applyTime; //申请时间

    private String apply_reason;

    public String getApply_reason() {
        return apply_reason;
    }

    public void setApply_reason(String apply_reason) {
        this.apply_reason = apply_reason;
    }

    public int getAdoptId() {
        return adoptId;
    }

    public void setAdoptId(int adoptId) {
        this.adoptId = adoptId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }


}
