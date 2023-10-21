package service;

import dao.ShowPetCommentDao;
import pojo.Comment;

import java.util.ArrayList;

/**
 * 有关某个宠物评论Service层
 */
public class ShowPetCommentService {
    private ShowPetCommentDao showPetCommentDao = new ShowPetCommentDao();

    /**
     * 根据宠物id查询跟该宠物所有相关的评论
     * @param petId
     * @return list
     */
    public ArrayList<Comment> getPetComment(int petId) {
        return showPetCommentDao.getPetComment(petId);
    }
}
