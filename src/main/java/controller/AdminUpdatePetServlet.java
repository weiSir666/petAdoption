
//AdminUpdatePetServlet
package controller;

import dao.PetManageDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pojo.RegisterPojo;
import pojo.Pet;
import service.AdminManagePetService;
import service.PetShowService;

import java.io.IOException;

/**
 * 管理员更新宠物信息Servlet
 */
@WebServlet("/adminUpdatePetServlet")
public class AdminUpdatePetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int petId = Integer.parseInt(request.getParameter("pet_id"));
        int pageNow = Integer.parseInt(request.getParameter("petPage"));
        HttpSession session = request.getSession();
        String path = request.getContextPath();
        PetManageDao dao = new PetManageDao();
        //System.out.println("petId = " + petId);
        //根据petId获取数据
        Pet pet = dao.getPetInformationByPetId(petId);
        //System.out.println(pet);
        session.setAttribute("petUpdate_PetInformation", pet);
        response.sendRedirect(path + "/html/petUpdate.jsp?petPage="+pageNow);
    }
}

