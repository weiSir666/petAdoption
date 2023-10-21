package service;

import dao.PersonalCenterUpdateDao;

import java.sql.SQLException;

public class PetShowService {

    /**
     * 获得宠物总页码数
     **/
    public int getPetAllPage() throws SQLException {
        int page = 1;
        PersonalCenterUpdateDao.PetManageDao dao = new PersonalCenterUpdateDao.PetManageDao();
        double count = dao.getPetCount();
        page = (int) Math.ceil(count / 3);

        return page;
    }

    public int adminGetPetAllPage() throws SQLException {
        int page = 1;
        PersonalCenterUpdateDao.PetManageDao dao = new PersonalCenterUpdateDao.PetManageDao();
        double count = dao.getPetCount();
        page = (int) Math.ceil(count / 20);

        return page;
    }
}