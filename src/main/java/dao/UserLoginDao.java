package dao;

import common.DBConnection;
import pojo.Admin;
import pojo.RegisterPojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 普通用户登录功能Dao层
 *  返回值:RegisterPojo对象
 */
public class UserLoginDao {
    private Connection con = null;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;

    /**
     * 普通用户登陆后进行密码效验
     * @param userName
     * @return RegisterPojo
     */
    public RegisterPojo RegisterPojoLoginDao(String userName) {
        con = DBConnection.getConnection();
        RegisterPojo registerPojo = null;
        try {
            String sql = "SELECT * FROM t_user where uname = ?;";
            pstm = con.prepareStatement(sql);
            pstm.setString(1,userName);
            rs = pstm.executeQuery();
            if(rs.next()) {
                registerPojo = new RegisterPojo();
                registerPojo.setUserName(rs.getString("uname"));
                registerPojo.setUserPassword(rs.getString("password"));
            }
        }
        catch (Exception err) {
            err.printStackTrace();
            System.out.println("普通用户查询出错");
        }finally {
            try{
                pstm.close();
                rs.close();
                DBConnection.closeConnection();
            }catch (Exception err1) {
                err1.printStackTrace();
                System.out.println("数据库关闭错误");
            }
        }
        //System.out.println(registerPojo.getUserName());
        //System.out.println(registerPojo.getUserPassWord());
        return registerPojo;
    }

    /**
     * 根据用户名查询数据
     * @param userName
     * @return RegisterPojo对象
     */
    public RegisterPojo RegisterPojoGetInformationByName(String userName) {
        con = DBConnection.getConnection();
        RegisterPojo registerPojo = null;
        try {
            String sql = "select * from t_user where uname = ?";
            pstm = con.prepareStatement(sql);
            pstm.setString(1,userName);
            rs = pstm.executeQuery();
            //System.out.println(userName);
            if(rs.next()) {
                //封装到registerPojo对象
                registerPojo = new RegisterPojo();
                registerPojo.setUserName(rs.getString("uname"));        //用户名
                registerPojo.setUserPassword(rs.getString("password")); //密码
                registerPojo.setRealName(rs.getString("rname"));        //真实姓名
                registerPojo.setUserAge(rs.getInt("age"));              //年龄
                registerPojo.setUserPhone(rs.getString("telphone"));    //电话
                registerPojo.setPlace(rs.getString("place"));           //地址
                registerPojo.setUserSex(rs.getString("sex"));           //性别
                registerPojo.setUserId(rs.getInt("user_id"));           //用户id
            }
        }
        catch (Exception err) {
            err.printStackTrace();
            System.out.println("普通用户查询出错");
        }finally {
            try{
                rs.close();
                pstm.close();
                DBConnection.closeConnection();
            }catch (Exception err1) {
                err1.printStackTrace();
                System.out.println("数据库关闭错误");
            }
        }
        //System.out.println(registerPojo);

        return registerPojo;
    }
}
