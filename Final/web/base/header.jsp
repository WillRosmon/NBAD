<%-- 
    Document   : header
    Created on : Dec 1, 2016, 5:12:06 PM
    Author     : wrosmon
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value='/./styles/main.css'/> ">
        <title>My Inventory Manager</title>
    </head>
    <body>
        <h1 id="title">My Inventory Manager</h1>
        <div id="login">
            <form action="login" method="post">
                Username: 
                <input type="text" name="username" id="loginUsername" />
                <br />
                Password: 
                <input type="password" name="password" id="loginPassword" />
                <br />
                <input type="submit" value="login" />
            </form>
        </div>
        <br />
    </body>
</html>
