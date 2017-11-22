<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 21/11/2017
  Time: 04:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login to Group 7: Task Manager</title>
    <link rel="stylesheet" type="text/css" href="resources/styles/login.css" media="screen"/>
</head>
<body>

    <div class="login">
        <div id="header">Group 7: Task Manager</div>
        <form action="Login" method="post">
            <input type="text" placeholder="Username" id="username" name="uname">
            <input type="password" placeholder="password" id="password" name="password">
            <a href="#" class="forgot">forgot password?</a>
            <input type="submit" value="Login">
        </form>
    </div>
    <div class="shadow"></div>
</body>
</html>
