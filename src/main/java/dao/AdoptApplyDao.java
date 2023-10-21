package dao;

import common.DBConnection;
import pojo.AdoptApplyPojo;
import pojo.PersonalCenterPojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.Instant;

public class AdoptApplyDao {
    private AdoptApplyPojo adoptApplyPojo = new AdoptApplyPojo();
    private PreparedStatement pStmt = null;
    private ResultSet rs = null;
    private Connection con = null;

    public int getAdoptByID(int user_id){
        int num = 0;
        //判断是否查询到该用户名的标记，如果能查到num的值为1,查不到num值就为0；
        try{
            con = DBConnection.getConnection();

            pStmt = con.prepareStatement("select count(*) from t_adopt where user_id = ?;");
            pStmt.setInt(1,user_id);
            rs = pStmt.executeQuery();
            if(rs.next()){//不为空则说明这个用户存在
                num = rs.getInt(1);
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

    public int insertApply(AdoptApplyPojo adoptApplyPojo){
        int count=0;


        try{
            con = DBConnection.getConnection();

            pStmt = con.prepareStatement("insert into t_adopt(user_id,pet_id,apply_reason,state,apply_time) values(?,?,?,0,?)");
            pStmt.setInt(1,adoptApplyPojo.getUser_id());
            pStmt.setInt(2,adoptApplyPojo.getPet_id());
            pStmt.setString(3,adoptApplyPojo.getApply_reason());
            pStmt.setTimestamp(4,new Timestamp(System.currentTimeMillis()));
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
