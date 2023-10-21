package pojo;

public class PersonalCenterPojo {
    private int userID;         //用户ID
    public String username;     //用户名
    public String realname;     //用户姓名
    public String userpassword; //用户密码
    public String sex;          //用户性别
    public int age;             //用户年龄
    public String telphone;     //用户电话号码
    public String place;        //地址

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "PersonalCenterPojo{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", realname='" + realname + '\'' +
                ", userpassword='" + userpassword + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", telphone='" + telphone + '\'' +
                ", place='" + place + '\'' +
                '}';
    }
}
