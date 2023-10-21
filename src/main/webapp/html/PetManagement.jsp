<%--管理员管理宠物信息界面--%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="pojo.User" %>
<%@ page import="pojo.Pet" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% String path = request.getContextPath(); %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
    <link rel="stylesheet" href="../css/userManagement.css">
    <link rel="stylesheet" href="../css/userManagement2.css">
    <title>管理员界面</title>
</head>
<body>
<!-- 左侧部分 -->
<div class="userManagement00">
    <div class="userManagement01"><a href="<%=path%>/html/mainPage.jsp?exitAdmin=1" class="userManagement02">宠物领养系统</a></div>
    <div class="userManagement03">
        <span class="userManagement04">用户管理</span>
    </div>
    <div class="userManagement05">
        <a href="<%=path%>/adminManageUserServlet?pageNow=1" class="userManagement06">用户信息</a>
    </div>
    <div class="userManagement07">
        <a href="<%=path%>/AdminManagePetServlet?petPage=1" class="userManagement08">宠物管理</a>
    </div>
    <div class="userManagement09">
        <a href="<%=path%>/commentManagementServlet?commentPage=1" class="userManagement10">评论管理</a>
    </div>
    <div class="userManagement11">
        <a href="<%=path%>/adminManageAdoptServlet?pageNow=1" class="userManagement12">领养审核管理</a>
    </div>
    <div class="userManagement20">
        <a href="../html/UpdateVideo.jsp" class="userManagement21">视频管理</a>
    </div>
</div>
<!-- 右上侧部分 -->
<div class="userManagement13">
    <div class="userManagement14">
        <span class="userManagement15">宠物管理</span>
    </div>
    <div class="userManagement16">
    <span class="userManagement17">插入宠物信息
      <form action="<%=path%>/SubmitPetServlet" name="submitPetForm" id="submitPetForm">
        <input type="text" name="petName" id="petName" class="userManagement18" placeholder="宠物名字">
          <input type="text" name="sex" id="sex" class="userManagement18" style="left: 420px" placeholder="性别">
        <input type="submit" class="userManagement19" id="button" style="left: 682px" value="插入" onclick="return check()">
      </form>
    </span>

        <div class="userManagement2-01">
            <table class="userManagement2-02">
                <thead>
                <th>宠物id</th>
                <th>备注（管理员填写）</th>
                <th>宠物名称</th>
                <th>性别</th>
                <th>类型</th>
                <th>领养状态</th>
                <th>操作</th>
                </thead>
                <tbody>
                <%
                    //当前页码数
                    int pageNow = Integer.parseInt(request.getParameter("petPage"));
                    //总页码
                    int allPage = Integer.parseInt(request.getParameter("petAllPage"));
                    System.out.println("jsp页面页码" + pageNow);
                    //取数据
                    ArrayList<Pet> list = (ArrayList<Pet>)session.getAttribute("petList");
                    int pet_id = 0;
                    String remark = null;
                    String petname = null;
                    String petsex = null;
                    String adoptstate = null;
                    String userSex = null;
                    for (int i = 0;i < list.size();i++) {
                        //需要进行判断是否为空，如果为空则不显示内容
                        pet_id = list.get(i).getPetId();
                        remark = list.get(i).getRemark();
                        if (list.get(i).getRemark() == null){
                            remark = "暂无备注";
                        }
                        //1:已领养 0:审核中 2：未通过（拒绝）
                        if (list.get(i).getAdoptstate() == 1) {
                            adoptstate = "已被领养";
                        }
                        else {
                            adoptstate = "未被领养或审核中";
                        }
                %>
                <tr class="userManagement2-03">
                    <td><%=list.get(i).getPetId()%></td>
                    <td><%=remark%></td>
                    <td><%=list.get(i).getPetType()%></td>
                    <td><%=list.get(i).getSex()%></td>
                    <td></td><%--暂为空的宠物类型--%>
                    <td><%=adoptstate%></td>
                    <td><%=userSex%></td>
                    <td>
                        <%--编辑和删除按钮--%>
                        <a style="text-align: center; text-decoration: none; color: blue"  href="<%=path%>/adminUpdatePetServlet?pet_id=<%=pet_id%>&petPage=<%=pageNow%>">编辑</a>
                        <a style="text-align: center; text-decoration: none; color: blue"  href="<%=path%>/AdminDeletePetServlet?pet_id=<%=pet_id%>&petPage=<%=pageNow%>">删除</a>
                    </td>
                    <%
                        }
                    %>
                </tr>
                <tr>
                    <td>当前页码数:<%=pageNow%></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td style="text-align: center">
                        <% if (allPage != 1) {%>
                        <% if(pageNow == 1) {%>
                        <a style="text-decoration: none; color: blue" href="<%=path%>/AdminManagePetServlet?petPage=<%=pageNow + 1%>">下一页</a>
                        <%}%>
                        <% if(pageNow == allPage) {%>
                        <a style="text-decoration: none; color: blue" href="<%=path%>/AdminManagePetServlet?petPage=<%=pageNow - 1%>">上一页</a>
                        <%}%>
                        <% if(pageNow > 1 && pageNow < allPage) {%>
                        <a style="text-decoration: none; color: blue" href="<%=path%>/AdminManagePetServlet?petPage=<%=pageNow - 1%>">上一页</a>
                        <a style="text-decoration: none; color: blue" href="<%=path%>/AdminManagePetServlet?petPage=<%=pageNow + 1%>">下一页</a>
                        <%}%>
                        <% }%>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    function check(){
        let username = /^雌$|^雄$/;
        let name = document.getElementById("sex").value;
        //性别不为雌或雄
        if(!username.test(name)){
            alert("性别为雌或雄");
            return false;
        }
        else {
            return true;
        }
    }
<%--    <%--%>
<%--      String deleteflag = request.getParameter("deleteFlag");--%>
<%--      String updateflag = request.getParameter("updateFlag");--%>

<%--        if(deleteflag != null ){--%>
<%--            if(deleteflag.equals("0")){--%>
<%--                out.println("alert('删除宠物数据失败')");--%>
<%--            }else {--%>
<%--                out.println("alert('删除宠物数据成功')");--%>
<%--            }--%>
<%--        }--%>
<%--        if(updateflag != null){--%>
<%--            if(updateflag.equals("0")){--%>
<%--                out.println("alert('更新宠物数据失败')");--%>
<%--            }else {--%>
<%--                out.println("alert('更新宠物数据成功')");--%>
<%--            }--%>
<%--        }--%>
<%--    %>--%>
</script>
</html>