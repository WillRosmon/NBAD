<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="mma" uri="/WEB-INF/tlds/RequirementsCustomTags.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Add a Product</title>
        <link rel="stylesheet" href="<c:url value='/styles/main.css'/> ">
    </head>
    <body>
        <h1>Product</h1>
        <p><i>* indicated a required field</i></p>
        
        <form action="addProduct" method="POST"> <!-- You need to supply the required attributes -->
            
            <label class="pad_top">Code:</label>
            <!-- You need to supply all required input types, including the 'Update Product' button -->
            <input type="text" 
                   name="product_code"
                   value="${product.getCode()}"/> 
            <mma:ifEmptyMark field="${product.getCode()}"/>
            <br />

            <label class="pad_top">Description:</label>
            <input type="text" 
                   name="product_description"/> 
            <mma:ifEmptyMark field="${product.getDescription()}"/>
            <br />

            <label class="pad_top">Price:</label>
            <input type="number" 
                   step=".01"
                   name="product_price"/> 
            <mma:ifEmptyMark field="${product.getPrice()}"/>
            <br />


            <label class="pad_top">&nbsp;</label>
            <input type="submit" value="Add" />
        </form>

        <!-- Hint! You need to code a form for the 'View Product' button -->
        <form action="getProducts" method="GET">
            <input type="submit" value="View Products" />
        </form>
    </body>
</html>