package controller;

import dao.PetManageDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import service.PetShowService;

import java.io.IOException;
import java.sql.SQLException;

/**
 * 删除宠物
 */
@WebServlet(name = "AdminDeletePetServlet", value = "/AdminDeletePetServlet")
public class AdminDeletePetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int PetPage = Integer.parseInt(request.getParameter("petPage"));//一般默认为一
        int pet_id = Integer.parseInt(request.getParameter("pet_id"));
        int falg = 0;
        String path = request.getContextPath();
        PetManageDao dao = new PetManageDao();

        PetShowService service = new PetShowService();

        int PetAllPage = 0;
        try {
            PetAllPage = service.getPetAllPage();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获取宠物总页数出错");
        }

        try {
            falg = dao.adminDeletePetInformantionById(pet_id);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("宠物删除失败");
        }

        response.sendRedirect(path + "/AdminManagePetServlet?petPage=" + PetPage + "&petAllPage=" + PetAllPage +"&deleteFlag=" + falg);
    }
}
