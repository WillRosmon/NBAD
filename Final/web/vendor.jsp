<%-- 
    Document   : vendor
    Created on : Dec 5, 2016, 2:24:04 PM
    Author     : wrosmon
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${requestScope.vendor.getVendorName()}</title>
        <%@include file="base/header.jsp" %>
    </head>
    <body>
        <div class="navigation">
            <%@include file="/./base/navigation.jsp" %>
        </div>
        
        <div id="vendorInfo">
            <span class="vendorName"><c:out value="${requestScope.vendor.getVendorName()}" /></span>
            <br/>
            <a href="<c:url value="/purchaseOrders?action=getByVendor&id=${requestScope.vendor.getVendorId()}" />">
                Vendor Purchase Orders
            </a>
            <br />
            <a href="<c:url value="/purchases?vendorId=${requestScope.vendor.getVendorId()}" />">
                Purchases from this vendor
            </a>    
        </div>
                <br/><br/><br/>
    </body>
    <%@include file="/./base/footer.jsp" %>
</html>
