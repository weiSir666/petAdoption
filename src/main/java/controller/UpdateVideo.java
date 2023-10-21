package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
/**
 * 该servlet用于管理员上传视频。
 * */
@WebServlet("/updateVideo")
@MultipartConfig
public class UpdateVideo extends HttpServlet {
    //存储路径
    private static final String path = "C:/Users/32261/Desktop/gcsj2/PetAdoption/src/main/webapp/video/";
    // 视频规定名称
    private static final String video_name = "1.mp4";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path2 = request.getContextPath();
        Part filePart = request.getPart("videoFile");
        InputStream fileContent = filePart.getInputStream();

        File oldVideoFile = new File(path + video_name);
        System.out.println(path + video_name);
        if(oldVideoFile.exists()){
            oldVideoFile.delete();
        }

        Files.copy(fileContent, Paths.get(path + video_name));
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("视频上传成功！");

        request.setAttribute("url",path + video_name);

        fileContent.close();
    }
}
