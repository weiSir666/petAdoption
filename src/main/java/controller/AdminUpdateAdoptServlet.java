package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import pojo.Adopt;
import service.AdminManageAdoptService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AdminUpdateAdoptServlet")
public class AdminUpdateAdoptServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int adopt_id = Integer.parseInt(request.getParameter("adopt_id"));
        int state = Integer.parseInt(request.getParameter("state"));
        HttpSession session = request.getSession();
        AdminManageAdoptService adminManageAdoptService = new AdminManageAdoptService();
        Adopt adopt = adminManageAdoptService.getInformationByAdoptid(adopt_id);
        adopt.setState(state);
        boolean result = false;
        try {
            result = adminManageAdoptService.updateAdoptState(adopt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (result) {
            switch (state) {
                case 1:
                    response.getWriter().write("successful processing!");
                    session.setAttribute("flag",1);
                    break;
                case 2:
                    response.getWriter().write("unsuccessful processing!");
                    session.setAttribute("flag",2);
                    break;
                default:
                    response.getWriter().write("操作成功！");
                    break;
            }
        } else {
            response.getWriter().write("操作失败！");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}