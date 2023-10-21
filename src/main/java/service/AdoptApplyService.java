package service;

import dao.AdoptApplyDao;

import java.sql.SQLException;

public class AdoptApplyService {
    private AdoptApplyDao adoptApplyDao = new AdoptApplyDao();

    //验证用户名是否存在
    public int AdoptApply(int user_id)throws SQLException {
        return adoptApplyDao.getAdoptByID(user_id);
    }
}
