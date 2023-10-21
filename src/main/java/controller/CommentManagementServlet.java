package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pojo.Comment;
import pojo.Comment1;
import service.CommentManagementService;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 点击用户评论模块进入该Servlet，实现分页展示数据
 */
@WebServlet("/commentManagementServlet")
public class CommentManagementServlet extends HttpServlet {
    private CommentManagementService commentManagementService = new CommentManagementService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getContextPath();
        HttpSession session = request.getSession();
        int commentPage = Integer.parseInt(request.getParameter("commentPage"));
        //System.out.println("当前评论页码：" + commentPage);
        String deleteCommentFlag = request.getParameter("deleteCommentFlag");
        int commentAllPage = commentManagementService.getAllCommentPage();
        //System.out.println("总页码数：" + commentAllPage);

        // 获取某页评论信息
        ArrayList<Comment> list = commentManagementService.getCommentInformationByPage(commentPage);
        session.setAttribute("commentList", list);

        response.sendRedirect(path + "/html/CommentManagement.jsp?commentPage=" + commentPage + "&commentAllPage=" + commentAllPage + "&deleteCommentFlag=" + deleteCommentFlag);
    }
}