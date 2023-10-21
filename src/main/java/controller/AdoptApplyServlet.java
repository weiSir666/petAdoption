package controller;

import dao.AdoptApplyDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import pojo.AdoptApplyPojo;
import service.AdoptApplyService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AdoptApplyServlet")
public class AdoptApplyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public AdoptApplyServlet(){
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getContextPath();//获取站点的根路径
        HttpSession session = request.getSession();

        int num = 0;//用来判断用户是否存在的标志,num为0表示没有该用户，则可以进行注册操作，num为1的话，说明已经存在该用户，则不能添加;
        request.setCharacterEncoding("UTF-8");
        int user_id = Integer.parseInt(request.getParameter("user_id"));

        try{
            AdoptApplyService adoptApplyService = new AdoptApplyService();
            num = adoptApplyService.AdoptApply(user_id);
            System.out.println(num);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("判断用户是否存在失败！");
        }

        if (num <= 3){
            AdoptApplyDao adoptApplyDao = new AdoptApplyDao();
            AdoptApplyPojo adoptApplyPojo = new AdoptApplyPojo();

            adoptApplyPojo.setUser_id(Integer.parseInt(request.getParameter("user_id")));
            adoptApplyPojo.setPet_id(Integer.parseInt(request.getParameter("pet_id")));
            adoptApplyPojo.setTelphone(request.getParameter("telphone"));
            adoptApplyPojo.setSite(request.getParameter("site"));
            adoptApplyPojo.setApply_reason(request.getParameter("apply_reason"));

            int flag = 0;
            flag = adoptApplyDao.insertApply(adoptApplyPojo);

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            if (flag != 0) {
                int jspflag = 1;
                request.setAttribute("flag",jspflag);
                response.sendRedirect(path + "/html/adopApply.jsp");

            }
        }
        if(num >= 4){
            int flag = 1;
            int jspflag = 0;
            request.setAttribute("flag",jspflag);
            session.setAttribute("user_id",user_id);
            response.setContentType("text/html;charset=UTF-8");
            response.sendRedirect(path + "/html/adopApply.jsp");

        }
    }
}
