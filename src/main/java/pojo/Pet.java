package pojo;

/**
 * 宠物实体类
 */
public class Pet {
    private int petId;
    private int picId;          //图片id
    private String imgurl;
    private String sex;
    private String petType;     //宠物类型
    private String remark;      //备注
    private int adoptstate;

    public int getAdoptstate() {
        return adoptstate;
    }

    public void setAdoptstate(int adoptstate) {
        this.adoptstate = adoptstate;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "petId=" + petId +
                ", picId=" + picId +
                ", imgurl='" + imgurl + '\'' +
                ", sex='" + sex + '\'' +
                ", petType='" + petType + '\'' +
                ", remark='" + remark + '\'' +
                ", adoptstate=" + adoptstate +
                '}';
    }
}
