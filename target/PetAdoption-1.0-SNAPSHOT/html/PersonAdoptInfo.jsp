<%@ page import="java.util.ArrayList" %>
<%@ page import="pojo.Adopt" %>
<%@ page import="dao.PersonAdoptInfoDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath();%>
<html>
<head>
    <link rel="stylesheet" href="../css/PersonAdoptInfo.css">
    <title>个人领养信息</title>
</head>
<body>
<div class="return">
    <a href="mainPage2.jsp" class="zhuye">主页</a>
    <a href="PersonalCenter.jsp" class="return_personalcenter">返回个人中心</a>
<%--    <a href="adopApply.jsp" class="return_adoptapply">返回领养申请</a>--%>
</div>
<div class="tittle">
    <h1>个人领养信息</h1>
</div>
<div class="info">
    <table>
        <thead>
        <th width="15%">领养id</th>
        <th width="15%">用户id</th>
        <th width="15%">宠物id</th>
        <th width="15%">申请理由</th>
        <th width="15%">申请状态</th>
        <th width="15%">申请时间</th>
        </thead>
        <tbody>
        <%
            path = request.getContextPath();
            //ArrayList<Adopt> list = (ArrayList<Adopt>) session.getAttribute("pageList2");
            int userid = (int) session.getAttribute("RegisterPojoUserId");
            ArrayList<Adopt> list = PersonAdoptInfoDao.getInformationByUserId(userid);
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
        <tr>
            <td width="15%" align="center"><%=list.get(i).getAdoptId()%></td>
            <td width="15%" align="center"><%=user_id%></td>
            <td width="15%" align="center"><%=pet_id%></td>
            <td width="15%" align="center"><%=apply_reason%></td>
            <td width="15%" align="center"><% if (state == 0) {%>
                审核中
                <% } else if (state == 1) {%>
                审核通过
                <% } else if (state == 2) {%>
                审核未通过
                <% } %></td>
            <td width="15%" align="center"><%=apply_time%></td>
            <%
                }
            %>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
