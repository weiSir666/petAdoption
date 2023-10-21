package service;

import dao.AdminManageAdoptDao;
import pojo.Adopt;

import java.util.ArrayList;

public class AdminManageAdoptService {
    private AdminManageAdoptDao adminManageAdoptDao = new AdminManageAdoptDao();
    //设置每页展示的数据量
    public static int pageSize = 10;

    /**
     * 获取所有信息
     * @return ArrayList<Adopt>
     */
    public ArrayList<Adopt> getAllInformationByName() {
        return adminManageAdoptDao.getAllInformationByName();
    }

    /**
     * 统计总页码数
     * @return count(总页码数)
     */
    public int getAllPage() {
        int sum = adminManageAdoptDao.getAdoptCount();
        int count = (sum + pageSize - 1)/pageSize;

        return count;
    }

    /**
     * 通过传递的页码数展示该页码数的数据
     * @param page(页码数)
     * @return ArrayList<Adopt>
     */
    public ArrayList<Adopt> getInformationByPage(int page) {
        //偏移量offset
        int offset = (page - 1)*pageSize;
        return adminManageAdoptDao.getInformationByPage(offset, pageSize);
    }

    /**
     * 通过用户id查询用户信息
     * @param adoptId
     * @return Adopt
     */
    public Adopt getInformationByAdoptid(int adoptId) {
        return adminManageAdoptDao.getInformationByAdoptid(adoptId);
    }

    public Adopt getAdoptById(int adopt_id) {
        AdminManageAdoptDao adoptDAO = new AdminManageAdoptDao();
        return adoptDAO.getInformationByAdoptid(adopt_id);
    }

    public boolean updateAdoptState(Adopt adopt) throws Exception {
        AdminManageAdoptDao adoptDAO = new AdminManageAdoptDao();
        return adoptDAO.updateAdoptState(adopt);
    }

    public ArrayList<Adopt> getInformationByUserid(int user_id) {
        AdminManageAdoptDao adoptDao = new AdminManageAdoptDao();
        return adoptDao.getInformationByUserid(user_id);
    }
}
