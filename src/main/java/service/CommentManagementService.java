package service;

import dao.CommentManagementDao;
import pojo.Comment;
import pojo.Comment1;
import pojo.User;

import java.util.ArrayList;

/**
 * 评论管理Service层
 */
public class CommentManagementService {
    private CommentManagementDao commentManagementDao = new CommentManagementDao();
    private static int commentPageSize = 20;                //评论管理每页展示数据量

    /**
     * 获取所有评论信息
     * @return list<Comment>
     */
    public ArrayList<Comment> getAllCommentInformation() {
        return commentManagementDao.getAllCommentInformation();
    }

    /**
     * 统计评论总页码数
     * @return count(评论总页码数)
     */
    public int getAllCommentPage() {
        int sum = commentManagementDao.getAllComment();
        int count = (sum + commentPageSize - 1) / commentPageSize;

        return count;
    }

    /**
     * 通过传递的页码数展示该页码数的数据
     * @param page(页码数)
     * @return ArrayList<User>
     */
    public ArrayList<Comment> getCommentInformationByPage(int page) {
        //偏移量offset
        int offset = (page - 1) * commentPageSize;
        return commentManagementDao.getCommentInformationByPage(offset, commentPageSize);
    }

    /**
     * 通过评论Id删除评论信息
     * @param commentId
     * @return true or false
     */
    public boolean deleteCommentByCommentId(int commentId) {
        int count = commentManagementDao.deleteCommentByCommentId(commentId);
        if (count == 1) {
            return true;
        }
        else  {
            return false;
        }
    }
}
