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
        <h1>Are you sure you want to delete this product?</h1>

        <form action="deleteProduct?code=${product.getCode()}" method="POST" >
        <label>Code:</label>
        <label>${product.getCode()}</label> <br />
        <label>Description:</label>
        <label>${product.getDescription()}</label> <br />
        <label>Price:</label>
        <label>${product.getPrice()}</label> <br />
     <!-- Hint! You need to code a form for the 'Yes' button -->
            <input type="submit" value="Yes" />
        </form>
    <!-- Hint! You need to code a form for the 'No Product' button -->
       <form action="getProducts" method="GET">
           <input type="submit" value="No" />
       </form>
            
    </body>
</html>