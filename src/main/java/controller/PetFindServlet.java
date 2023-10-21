package controller;

import dao.PetFindDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/PetFindServlet")
public class PetFindServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public PetFindServlet(){super();}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getContextPath();                 //获取站点的根路径
        HttpSession session = request.getSession();
        PetFindDao petFindDao = new PetFindDao();
        int petId = 0;
        String petType = request.getParameter("petType");
        System.out.println(petType);
        petId = petFindDao.find_petId_by_petType(petType);

        System.out.println(petId);
        if(petId == 0){
            response.setContentType("text/html;charset=UTF-8");
            response.sendRedirect(path + "/html/mainPage.jsp?pet_Id=" + petId);
        }
        else{
            response.setContentType("text/html;charset=UTF-8");
            response.sendRedirect(path + "/Adopt_CenterServlet?pet_id=" + petId);
        }

    }
}