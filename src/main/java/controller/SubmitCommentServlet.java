package controller;

import common.MyTools;
import dao.PetCommentDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import pojo.Comment;
import pojo.Comment1;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

@WebServlet(name = "SubmitCommentServlet", value = "/SubmitCommentServlet")
public class SubmitCommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        从前台获取宠物ID 用户ID 以及评论 并提交到评论表中
        String path=request.getContextPath();
        HttpSession session=request.getSession();
        String str=request.getParameter("user_id");
        int user_id= MyTools.strToint(str);
        str=request.getParameter("pet_id");
        int pet_id=MyTools.strToint(str);


        Comment1 comment=new Comment1();
        comment.setUserId(user_id);
//
//        System.out.println(user_id);

        Date currentDate = new Date();
        comment.setCommentTime(new java.sql.Date(currentDate.getTime()));
        System.out.println(comment.getCommentTime());

        comment.setPetId(pet_id);

//        System.out.println(pet_id);

        comment.setContent(request.getParameter("comment"));

        PetCommentDao dao = new PetCommentDao();
        try {
            int count=dao.SubmitComment(comment);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("调用提交评论方法出错");
        }
        response.sendRedirect(path+"/html/AdoptPet.jsp");
    }
}
