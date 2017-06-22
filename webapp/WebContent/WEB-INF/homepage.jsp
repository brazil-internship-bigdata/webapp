<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Homepage</title>
         <link type="text/css" rel="stylesheet" href='<c:url value="/inc/style.css"/>'>
    </head>
    <body>
        <p>You are connected with  ${sessionScope.companySession.companyName}, you have access to the private space.</p>
        
        <p><a href='<c:url value="logout"/>'>Logout</a></p>
    </body>
</html>