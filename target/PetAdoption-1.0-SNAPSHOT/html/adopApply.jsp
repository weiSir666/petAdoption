<%@ page import="pojo.Pet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path =request.getContextPath();
    int petId = (int) session.getAttribute("PetId");
%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>领养申请界面</title>
</head>
<link href="../css/adoptApply.css" rel="stylesheet">
<body>
<div class="top">
    <h1>申请领养</h1>
</div>
<div class="tiaozhuan">
    <a href="<%=path%>/Adopt_CenterServlet?petPage=1&pet_id=<%=petId%>" class="return">返回领养中心</a>
    <a href="PersonAdoptInfo.jsp" class="myadopt" align="center">查看个人领养信息</a>
</div>
<div class="main">

    <img src="../images/head.webp" id="toux"><td></td>
    <div class="user">
        <form action="../AdoptApplyServlet" method="post" name="frm" id="frm">
            用户ID<br>      <input type="text" value="${sessionScope.RegisterPojoUserId}" id="user_id" name="user_id" readonly><br><br>
            宠物ID<br>      <input type="text" value="${sessionScope.PetId}" id="pet_id" name="pet_id" readonly><br><br>
            手机号<br>      <input type="tel" value="" id="telphone" name="telphone"><br><br>
            地址<br>       <input type="text" value="" id="site" name="site"><br><br>
            申请理由<br>    <input type="text" value="" id="apply_reason" name="apply_reason"><br><br>
            <br>
            <input type="button" value="领养申请" class="button" onclick="cheak()">
        </form>
    </div>
    <img src="${sessionScope.ImgUrl}" alt="宠物图片" class="img">
</div>
</body>
<script>
    function cheak() {
        var regex = /^\d{11}$/;
        if (user_id.value == "") {
            alert("用户ID不能为空!");
            return false;
        } else if (pet_id.value == "") {
            alert("宠物ID不能为空!");
            return false;
        } else if (telphone.value == "" || !regex.test(telphone.value)) {
            alert("请输入正确的电话!");
            return false;
        } else if (site.value == "") {
            alert("地址不能为空!");
            return false;
        } else if (apply_reason.value == "") {
            alert("申请理由不能为空!");
            return false;
        } else {
            document.getElementById("frm").submit();
            alert("信息已提交!如果提交申请已大于三条，则无法正常提交！");
            if (request.getAttribute("flag") != null){
                if (request.getAttribute("flag").equals(1)) {
                    alert("信息提交成功！");
                } else {
                    alert("信息提交失败！");
                }
            }
        }
    }
</script>
</html>