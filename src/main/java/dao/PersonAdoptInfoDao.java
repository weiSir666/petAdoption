package dao;

import common.DBConnection;
import pojo.Adopt;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersonAdoptInfoDao {
    private static Connection con = null;
    private static ResultSet rs = null;
    private static PreparedStatement pstm  = null;
    public static ArrayList<Adopt> getInformationByUserId(int user_id) throws Exception {
        ArrayList<Adopt> list = new ArrayList<>();
        con = DBConnection.getConnection();
        try{
            String sql="SELECT * FROM t_adopt WHERE user_id = ?";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1,user_id);
            rs = pstm.executeQuery();
            while (rs.next()){
                Adopt adopt = new Adopt();
                adopt.setAdoptId(rs.getInt("adopt_id"));
                adopt.setUserId(rs.getInt("user_id"));
                adopt.setPetId(rs.getInt("pet_id"));
                adopt.setApply_reason(rs.getString("apply_reason"));
                adopt.setState(rs.getInt("state"));
                adopt.setApplyTime(rs.getDate("apply_time"));
                list.add(adopt);
            }
        } catch (Exception e) {
            System.out.println("获取个人领养申请失败");
            throw new RuntimeException(e);
        }finally {
            rs.close();
            pstm.close();
            DBConnection.closeConnection();
        }
        return list;
    }
}
