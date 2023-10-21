<% String path = request.getContextPath();
    int pageNow;%><%--管理员管理用户信息界面--%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="pojo.Adopt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/adoptManagement.css">
    <link rel="stylesheet" href="../css/userManagement.css">
    <title>管理员界面</title>
</head>
<body>
<!-- 左侧部分 -->
<div class="adoptManagement00">
    <div class="adoptManagement01"><a href="<%=path%>/html/mainPage.jsp?exitAdmin=1" class="adoptManagement02">宠物领养系统</a></div>
    <div class="adoptManagement03">
        <span class="adoptManagement04">用户管理</span>
    </div>
    <div class="adoptManagement05">
        <a href="<%=path%>/adminManageUserServlet?pageNow=<%=1%>" class="adoptManagement06">用户信息</a>
    </div>
    <div class="adoptManagement07">
        <a href="<%=path%>/AdminManagePetServlet?petPage=1" class="adoptManagement08">宠物管理</a>
    </div>
    <div class="adoptManagement09">
        <a href="<%=path%>/commentManagementServlet?commentPage=1" class="adoptManagement10">评论管理</a>
    </div>
    <div class="adoptManagement11">
        <a href="<%=path%>/adminManageAdoptServlet?pageNow=1" class="adoptManagement12">领养审核管理</a>
    </div>
    <div class="userManagement20">
        <a href="../html/UpdateVideo.jsp" class="userManagement21">视频管理</a>
    </div>
</div>
<!-- 右上侧部分 -->
<div class="adoptManagement13">
    <div class="adoptManagement14">
        <span class="adoptManagement15">领养管理</span>
    </div>
    <div class="adoptManagement16">
        <div class="adoptManagement2-01">
            <table class="adoptManagement2-02">
                <thead>
                <th>领养id</th>
                <th>用户id</th>
                <th>宠物id</th>
                <th>申请理由</th>
                <th>申请状态</th>
                <th>申请时间</th>
                </thead>
                <tbody>
                <%
                    //当前页码数
                    pageNow = Integer.parseInt(request.getParameter("pageNow"));
//总页
                    int allPage = Integer.parseInt(request.getParameter("allPage"));
                    path = request.getContextPath();
                    System.out.println("jsp页面页码" + pageNow);
//取数据
                    ArrayList<Adopt> list = (ArrayList<Adopt>) session.getAttribute("pageList1");
                    int adopt_id = 0;
                    int user_id = 0;
                    int pet_id = 0;
                    String apply_reason = null;
                    int state = 0;
                    String apply_time = null;
                    for (int i = 0; i < list.size(); i++) {
//需要进行判断是否为空，如果为空则不显示内容
                        adopt_id = list.get(i).getAdoptId();
                        user_id = list.get(i).getUserId();
                        pet_id = list.get(i).getPetId();
                        if (list.get(i).getApply_reason() == null) {
                            apply_reason = "用户未填写";
                        } else {
                            apply_reason = list.get(i).getApply_reason();
                        }
                        state = list.get(i).getState();
                        if (list.get(i).getApplyTime() == null) {
                            apply_time = "用户未填写";
                        } else {
                            apply_time = String.valueOf(list.get(i).getApplyTime());
                        }
                %>
                <tr class="adoptManagement2-03">
                    <td><%=list.get(i).getAdoptId()%></td>
                    <td><%=user_id%></td>
                    <td><%=pet_id%></td>
                    <td><%=apply_reason%></td>
                    <td><% if (state == 0) {%>
                        审核中
                        <% } else if (state == 1) {%>
                        审核通过
                        <% } else if (state == 2) {%>
                        审核未通过
                        <% } %></td>
                    <td ><%=apply_time%></td>

                    <td>
                        <%--编辑和删除按钮--%>
                        <a style="text-align: center; text-decoration: none; color: blue" href="<%=path%>/AdminUpdateAdoptServlet?adopt_id=<%=adopt_id%>&state=1">同意申请</a>
                        <a style="text-align: center; text-decoration: none; color: blue" href="<%=path%>/AdminUpdateAdoptServlet?adopt_id=<%=adopt_id%>&state=2">拒绝申请</a>
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
                    <td></td>
                    <td style="text-align: center">
                        <%-- 上一页 --%>
                        <% if (pageNow > 1) {%>
                        <a class="page-link" href="<%=path%>/adminManageAdoptServlet?pageNow=<%=pageNow - 1%>">上一页</a>
                        <% } %>

                        <%-- 下一页 --%>
                        <% if (pageNow < allPage) {%>
                        <a class="page-link" href="<%=path%>/adminManageAdoptServlet?pageNow=<%=pageNow + 1%>">下一页</a>
                        <% } %><a class="page-link" href="<%=path%>/adminManageAdoptServlet?pageNow=<%=pageNow %>">刷新</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>