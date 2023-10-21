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
 * 删除评论Servlet
 */
@WebServlet("/deleteComment")
public class DeleteComment extends HttpServlet {
    private CommentManagementService commentManagementService = new CommentManagementService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getContextPath();
        // 从前台获取评论Id
        int commentId = Integer.parseInt(request.getParameter("comment_id"));
        boolean deleteCommentFlag = commentManagementService.deleteCommentByCommentId(commentId);
        response.sendRedirect(path + "/commentManagementServlet?deleteCommentFlag=" + deleteCommentFlag + "&commentPage=1");
    }
}