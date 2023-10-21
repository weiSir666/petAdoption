package controller;

import common.MyTools;
import dao.AdoptCenterDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import pojo.Pet;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Adopt_CenterServlet", value = "/Adopt_CenterServlet")
public class Adopt_CenterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        获取图片url、宠物id ，调用方法获得宠物数据
        String path = request.getContextPath();
        int Pet_Id = 0;
        Pet_Id= Integer.parseInt(request.getParameter("pet_id"));

        AdoptCenterDao dao = new AdoptCenterDao();
        HttpSession session = request.getSession();

        Pet petpojo = new Pet();

        try {
            petpojo = dao.getPetInformationById(Pet_Id);
        } catch (Exception e) {
            System.out.println("获取指定宠物失败");
        }
        String imgurl = petpojo.getImgurl();
        session.setAttribute("ImgUrl",petpojo.getImgurl());
        session.setAttribute("PetId", petpojo.getPetId());
        session.setAttribute("PetType", petpojo.getPetType());
        session.setAttribute("PetSex", petpojo.getSex());

        response.sendRedirect(path+"/html/AdoptPet.jsp?imgUrl="+imgurl+"&pet_id="+Pet_Id);

    }
}
