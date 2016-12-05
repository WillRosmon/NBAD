<%-- 
    Document   : addVendor
    Created on : Dec 4, 2016, 6:43:48 PM
    Author     : wrosmon
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Vendor</title>
        <%@include file="base/header.jsp" %>
    </head>
    <body>
        <h1>Add a Vendor</h1>
        <div>
            <form action="vendors" method="post">
                Vendor Name:
                <input type="text" id="vendorName" name="vendorName"/> <br />
                <input type="submit" name="addVendor">
            </form>
        </div>
    </body>
    <%@include file="base/footer.jsp" %>
</html>
