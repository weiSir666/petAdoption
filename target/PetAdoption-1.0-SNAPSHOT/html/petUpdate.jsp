<%--宠物编辑--%>
<%@ page import="pojo.User" %>
<%@ page import="pojo.Pet" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/userUpdate.css">
    <title>宠物信息</title>
</head>
<!--
<%
    HttpSession session1 = request.getSession();
    int pageNow = Integer.parseInt(request.getParameter("petPage"));
    Pet pet = (Pet) session1.getAttribute("petUpdate_PetInformation");
    String path = request.getContextPath();
%>
-->
<body class="background">
<!-- 顶部褐色框 -->
<div class="userUpdate01">
    <a href="<%=path%>/AdminManagePetServlet?petPage=<%=pageNow%>" class="userUpdate02">返回</a>
    <div class="userUpdate03" style="left: 650px;">宠物信息</div>
</div>
<form action="<%=path%>/adminUpdatePetServlet1" method="post" id="petUpdateForm">
    <!-- 用户信息 -->
    <input type="hidden" name="petId" id="petId" value="<%=pet.getPetId()%>">
    <input type="hidden" name="petPage" id="petPAge" value="<%=pageNow%>">
    <div class="userUpdate04" style="left: 500px;">宠物名：</div>
    <input type="text" class="userUpdate05" id="petName" name="petName" value="<%=pet.getPetType()%>" style="left: 770px;">
    <div class="userUpdate04" style="top: 170px;left: 500px;">备注:</div>
    <input type="text" class="userUpdate05" style="top: 180px;left: 770px;" id="petRemark" name="petRemark" value="<%=pet.getRemark()%>" >
    <div class="userUpdate04" style="top: 240px;left: 500px;">性别：</div>
    <input type="text" class="userUpdate05" style="top: 250px;left: 770px;" id="petSex" name="petSex" value="<%=pet.getSex()%>">
    <input type="button" class="userUpdate06" value="更新" onclick="userUpdateCheck()" id="userUpdateButton01" style="top: 300px;left: 760px;">
</form>
</body>
<script language="JavaScript">
    /**
     * 该方法用于判断管理员更新用户信息的时候格式的正确性
     */
    function userUpdateCheck() {
        let petSex = document.getElementById("petSex").value;

        let userUpdateForm = document.getElementById("petUpdateForm");

        // 判断性别格式
        if (petSex != "用户未填写" && petSex != "") {
            if (petSex != "雄" && petSex != "雌") {
                alert("性别必须为雄或者雌");
                return ;
            }
        }
        userUpdateForm.submit();
    }
</script>
</html>