<%-- 
    Document   : addPurchaseOrder
    Created on : Dec 5, 2016, 5:29:41 PM
    Author     : wrosmon
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="base/header.jsp" %>
    </head>
    <body>
        <div class="navigation">
            <%@include file="/./base/navigation.jsp" %>
        </div>
        <div>
            <form action="" method="post">
                Vendor Id:
                <input type="text" id="POvendorId" name="vendorId" />
                <br/>
                Amount: 
                <input type="number" id="issueAmount" name="amount" />
                <br />
                <input type="submit"  name="addPO" />
            </form>
        </div>
        <br/><br/><br/>
    </body>
    <div class="footer" >
        <%@include file="base/footer.jsp" %>
    </div>
</html>
