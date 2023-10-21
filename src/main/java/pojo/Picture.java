package pojo;

/**
 * 照片类
 */
public class Picture {
    private int picId;
    private String picUrl;              //图片路径

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Override
    public String toString() {
        return "picture{" +
                "picId=" + picId +
                ", picUrl='" + picUrl + '\'' +
                '}';
    }
}
