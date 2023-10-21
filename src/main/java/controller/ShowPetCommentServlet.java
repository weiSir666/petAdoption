package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pojo.Comment;
import pojo.Pet;
import service.ShowPetCommentService;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 用于展示用户对宠物的评论(展示关于某个宠物的所有评论)
 */
@WebServlet("/showPetCommentServlet")
public class ShowPetCommentServlet extends HttpServlet {
    private ShowPetCommentService showPetCommentService = new ShowPetCommentService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String path = request.getContextPath();
        ArrayList<ArrayList<Comment>> commentsList = new ArrayList<>();

        // 获取页码数和总页码数
        int petPage = Integer.parseInt(request.getParameter("petPage"));
        int petAllPage = Integer.parseInt(request.getParameter("petAllPage"));

        // 获取某页数的宠物信息
        ArrayList<Pet> list = (ArrayList<Pet>) session.getAttribute("petList");
        for (int i = 0;i < list.size();i++) {
            // 遍历每个宠物Id，将跟它们有关的评论分别装入集合中
            int petId = list.get(i).getPetId();
            ArrayList<Comment> comments = showPetCommentService.getPetComment(petId);
            commentsList.add(comments);
        }
        System.out.println(commentsList);
        session.setAttribute("commentsList", commentsList);
        response.sendRedirect(path + "/html/adopt_Center.jsp?petPage=" + petPage + "&petAllPage=" + petAllPage);
    }
}