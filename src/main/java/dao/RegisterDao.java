package dao;

import common.DBConnection;
import pojo.PersonalCenterPojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *  用户注册类Dao层
 *  返回值：PersonalCenterPojo
 */

public class RegisterDao {
    private PersonalCenterPojo personalcenterpojo = new PersonalCenterPojo();
    private PreparedStatement pStmt = null;
    private ResultSet rs = null;
    private Connection con = null;

    public int GetUserByName(String username){
        int num = 0;
        //判断是否查询到该用户名的标记，如果能查到num的值为1,查不到num值就为0；
        try{
            con = DBConnection.getConnection();

            pStmt = con.prepareStatement("select uname from t_user where uname = ?;");
            pStmt.setString(1,username);
            rs = pStmt.executeQuery();
            if(rs.next()){//不为空则说明这个用户存在
                num = 1;
            }
            pStmt.close();
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("查找失败！");
        }
        finally {
            DBConnection.closeConnection();
        }
        return num;
    }
    public int insertUser(PersonalCenterPojo personalcenterpojo){
        int count=0;
        try{
            con = DBConnection.getConnection();

            pStmt = con.prepareStatement("insert into t_user(uname,password) values(?,?)");
            pStmt.setString(1,personalcenterpojo.getUsername());
            pStmt.setString(2,personalcenterpojo.getUserpassword());
            count = pStmt.executeUpdate();
            System.out.println(count);
            pStmt.close();
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("添加用户失败！");
        }
        finally {
            DBConnection.closeConnection();
        }
        return count;
    }
}