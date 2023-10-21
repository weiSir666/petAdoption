

//AdminUpdatePetServlet1
package controller;

import dao.PetManageDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.AdminManagePetService;
import service.PetShowService;

import java.io.IOException;
import java.sql.SQLException;

/**
 * 用户编辑界面点击更新用户信息后跳转到该Sevlet
 */
@WebServlet("/adminUpdatePetServlet1")
public class AdminUpdatePetServlet1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String path = request.getContextPath();
        // 从前台获取数据(用户名不可更改)
        int PetId = Integer.parseInt(request.getParameter("petId"));
        String PetRealName = request.getParameter("petName");
        String PetRemark = request.getParameter("petRemark");
        String PetSex = request.getParameter("petSex");

        PetShowService service = new PetShowService();
        PetManageDao dao = new PetManageDao();
        // 调用service中的方法
        int flag = 0;
        flag = dao.adminUpdatePetInformation(PetId,PetRemark,PetRealName,PetSex);
        int allPage = 0;

        try {
            allPage = service.getPetAllPage();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int pageNow = 1;
        response.sendRedirect(path + "/AdminManagePetServlet?flag=" + flag + "&petPage=" + pageNow + "&petAllPage=" +allPage);
    }
}

