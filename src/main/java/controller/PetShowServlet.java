package controller;

import dao.PersonalCenterUpdateDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import pojo.Pet;
import service.PetShowService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * ，宠物中心前台展示宠物信息
 * **/
@WebServlet(name = "PetShowServlet", value = "/PetShowServlet")
public class PetShowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int PetPage = Integer.parseInt(request.getParameter("petPage"));
        HttpSession session = request.getSession();
        PersonalCenterUpdateDao.PetManageDao dao = new PersonalCenterUpdateDao.PetManageDao();
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
            petList = dao.GetInformationByPageNow(PetPage);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获取指定宠物集失败");
        }
        session.setAttribute("petList", petList);
        response.sendRedirect(path + "/showPetCommentServlet?petPage=" + PetPage + "&petAllPage=" + PetAllPage);
    }
}