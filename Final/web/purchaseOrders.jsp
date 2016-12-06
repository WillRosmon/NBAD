<%-- 
    Document   : purchaseOrders
    Created on : Dec 5, 2016, 5:28:18 PM
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
        
        <div class="POList">
            <table>
                <tr>
                    <th>PO Number</th>
                    <th>Issue Amount</th>
                    <th>Amount Remaining</th>
                </tr>
                <c:forEach items="${requestScope.poList}" var="p">
                    <tr>
                        <td>
                            <a href="<c:url value="/purchaseOrder?action=getPO&id=${p.getPurchaseOrderId()}" />">
                                <c:out value="${p.getPurchaseOrderId()}" />
                            </a>
                        </td>
                        <td>
                            <c:out value="${p.getIssueAmount()}" />
                        </td>
                        <td>
                            <c:out value="${p.getAmountRemaining()}" />
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <br/><br/><br/>
    </body>
    <%@include file="base/footer.jsp" %>
</html>
