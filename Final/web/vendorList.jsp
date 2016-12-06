<%-- 
    Document   : vendorList
    Created on : Dec 3, 2016, 9:05:44 PM
    Author     : wrosmon
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vendor List</title>
        <%@include file="base/header.jsp" %>
    </head>
    <body>
        <div class="navigation">
            <%@include file="base/navigation.jsp" %>
        </div>
        <div id="vendorList">
            <table>
                <tr>
                    <th>
                        Vendor ID
                    </th>
                    <th>
                        Vendor Name
                    </th>
                    <th>
                        Vendor Status
                    </th>
                </tr>
                <h2>Vendor List</h2>
                <c:forEach items="${requestScope.vendorList}" var="v">
                    <tr>
                        <td>
                            <a href="<c:url value="vendors?action=getByVendor&id=${v.getVendorId()}" />">
                            ${v.getVendorId()}
                            </a>
                        </td>
                        <td>
                            ${v.getVendorName()}
                        </td>
                        <td>
                            ${v.getVendorStatus()}
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <br/><br/><br/>
    </body>
    <%@include file="base/footer.jsp" %>
</html>
