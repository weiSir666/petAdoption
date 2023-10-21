package controller;

import dao.PetManageDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import pojo.Pet;
import service.PetShowService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 后台管理员展示宠物信息
 * **/
@WebServlet(name = "AdminManagePetServlet", value = "/AdminManagePetServlet")
public class AdminManagePetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int PetPage = Integer.parseInt(request.getParameter("petPage"));//一般默认为一
        HttpSession session = request.getSession();
        PetManageDao dao = new PetManageDao();
        PetShowService service = new PetShowService();

        String path = request.getContextPath();
        int PetAllPage = 0;
        try {
            PetAllPage = service.getPetAllPage();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获取宠物总页数出错");
        }

        ArrayList<Pet> petList = null;
        try {
            petList = dao.AdminGetInformationByPageNow(PetPage);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获取指定宠物集失败");
        }
        session.setAttribute("petList", petList);
        response.sendRedirect(path + "/html/PetManagement.jsp?petPage=" + PetPage + "&petAllPage=" + PetAllPage);
    }
}
