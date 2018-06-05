<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}/"/>
<html>
<head>
    <title>data-my-login</title>
    <link rel="stylesheet" type="text/css" href="${ctx}assets/css/login/styles.css">
</head>
<body>

<div class="htmleaf-container">
    <div class="wrapper">
        <div class="container">
            <h2>data-my-login</h2>
            <form class="form" action="/login" method="post">
                <input type="text" name="userName" placeholder="请输入用户名" style="min-width: 300px;" required>
                <input type="password" name="userPwd" placeholder="请输入用户密码" style="min-width: 300px;" required>
                <button type="submit" id="login-button" style="min-width: 300px;">登 陆</button>
            </form>
        </div>
        <ul class="bg-bubbles">
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
        </ul>
    </div>
</div>
<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';color:#000000">
    <h1>数 据 中 心</h1>
</div>
<script src="${ctx}assets/js/common/jquery-2.1.1.min.js" type="text/javascript"></script>
<script>
 /*   $('#login-button').click(function (event) {
        event.preventDefault();
        $('form').fadeOut(500);
        $('form').sub
        $('.wrapper').addClass('form-success');
    });*/
</script>
</body>
</html>
