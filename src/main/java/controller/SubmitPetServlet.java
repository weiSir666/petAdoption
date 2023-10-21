package controller;

import dao.PetManageDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
/**
 * 提交宠物信息到数据库
 * **/
@WebServlet(name = "SubmitPetServlet", value = "/SubmitPetServlet")
public class SubmitPetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getContextPath();
        PetManageDao dao = new PetManageDao();

        String name = request.getParameter("petName");//宠物名字
        String sex = request.getParameter("sex");//宠物性别
//        int picid = Integer.parseInt(request.getParameter(""));//图片id
        int flag = 0,pageNow=1;

        flag = dao.submitPetInformation(name,sex);

        response.sendRedirect(path + "/AdminManagePetServlet?petPage="+pageNow);
    }
}
