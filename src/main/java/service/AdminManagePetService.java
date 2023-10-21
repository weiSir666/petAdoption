package service;

import dao.PetManageDao;

import java.sql.SQLException;

public class AdminManagePetService {
    /**
     * 根据宠物id删除数据
     *  0，失败
     * **/
    public int deletePet(int petid) throws SQLException {
        int flag=0;

        PetManageDao dao =new PetManageDao();
        flag = dao.adminDeletePetInformantionById(petid);

        return flag;
    }
}
