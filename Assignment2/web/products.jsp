<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Product Maintenance</title>
        <link rel="stylesheet" href="<c:url value='/styles/main.css'/> ">
    </head>
    <body>
        <h1>Products</h1>
        <table>
            <tr>
                <th>Code</th>
                <th>Description</th>
                <th class="right">Price</th>
                <th></th>
                <th></th>
            </tr>
         <!-- Hint! Remember to code for the 'Edit' and 'Delete' links --> 

         <c:forEach items="${requestScope.products}" var="p">
             <tr>
                 <td>
                     ${p.getCode()}
                 </td>
                 <td>
                     ${p.getDescription()}
                 </td>
                 <td>
                     ${p.getPriceCurrencyFormat()}
                 </td>
                 <td>
                     <a href="<c:url value="updateProduct?code=${p.getCode()}" />" > Update Product</a>
                 </td>
                 <td>
                     <a href="<c:url value="deleteProduct?code=${p.getCode()}" />" > Delete Product </a>
                 </td>
             </tr>
             
         </c:forEach>
        </table>
        <!-- Hint! You need to code a form for the 'Add Product' button -->
        <a href="<c:url value="addProduct" />">Add a new Product</a>
    </body>
</html>