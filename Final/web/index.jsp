<%-- 
    Document   : index
    Created on : Dec 1, 2016, 5:47:43 PM
    Author     : wrosmon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Purchase Manager</title>
    </head>
    <%@include file="base/header.jsp" %>
    <body>
        <div class="navigation">
            <%@include file="base/navigation.jsp" %>
        </div>
        
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
    </body>
    <%@include file="base/footer.jsp" %>
</html>
