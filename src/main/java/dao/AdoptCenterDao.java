package dao;

import common.DBConnection;
import pojo.Pet;

import java.security.PrivateKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdoptCenterDao {
    private Connection con = null;

    private PreparedStatement pstm = null;
    private ResultSet rs = null;


    //输入图片url返回对应宠物数据
    public Pet getPetInformantionByUrl(String imgurl)  {

        con=DBConnection.getConnection();
        Pet pet=new Pet();

        try {
            String sql="SELECT * FROM t_pet,t_picture WHERE t_pet.pet_id=t_picture.pic_id and pic_url = ?";
            pstm=con.prepareStatement("SELECT * FROM t_pet,t_picture WHERE t_pet.pet_id=t_picture.pic_id and pic_url = ?;");
            pstm.setString(1,imgurl);
            rs=pstm.executeQuery();


            if(rs.next()){
                pet.setPetType(rs.getString("pet_type"));
                pet.setSex(rs.getString("sex"));
                pet.setPetId(rs.getInt("pet_id"));
            }else {
                System.out.println("结果集为空");
            }

            pstm.close();
            rs.close();
            DBConnection.closeConnection();

        } catch (Exception e) {
            System.out.println("信息封装失败");
        }

        return pet;
    }
    /**
     * 输入宠物id
     * 输出宠物对象
     * **/
    public Pet getPetInformationById(int pet_id){
        con=DBConnection.getConnection();
        Pet pet=new Pet();

        try {
            String sql="SELECT * FROM t_pet,t_picture WHERE t_pet.pet_id=t_picture.pic_id and pic_url = ?";
            pstm=con.prepareStatement("SELECT * FROM t_pet,t_picture WHERE t_pet.pic_id=t_picture.pic_id and pet_id = ?;");
            pstm.setInt(1,pet_id);
            rs=pstm.executeQuery();


            if(rs.next()){
                pet.setPetType(rs.getString("pet_type"));
                pet.setSex(rs.getString("sex"));
                pet.setPetId(pet_id);
                pet.setImgurl(rs.getString("pic_url"));
            }else {
                System.out.println("结果集为空");
            }

            pstm.close();
            rs.close();
            DBConnection.closeConnection();

        } catch (Exception e) {
            System.out.println("信息封装失败");
        }

        return pet;
    }
}
