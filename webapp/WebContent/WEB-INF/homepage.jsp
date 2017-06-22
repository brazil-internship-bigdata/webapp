<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Accès restreint</title>
         <link type="text/css" rel="stylesheet" href='<c:url value="/inc/style.css"/>'>
    </head>
    <body>
        <p>Vous êtes connecté(e) avec  ${sessionScope.companySession.companyName}, vous avez bien accès à l'espace restreint.</p>
        
        <p><a href='<c:url value="logout"/>'>Deconnexion</a></p>
    </body>
</html>