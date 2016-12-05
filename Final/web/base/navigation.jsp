<%-- 
    Document   : navigation
    Created on : Dec 4, 2016, 10:00:16 PM
    Author     : wrosmon
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <h3>Navigation</h3>
        <a href="<c:url value="/./vendors?id=all"/>">Vendors</a> <br/>
        <a href="<c:url value="/./addVendor.jsp"/>">Add a New Vendor</a> <br/>
        <a href="<c:url value="/./purchaseOrders?id=all"/>">Purchase Orders</a> <br/>
        <a href="<c:url value="/./purchases?id=all" />">Purchases</a> <br/>
    </body>
</html>
