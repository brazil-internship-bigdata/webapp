<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Accès public</title>
         <link type="text/css" rel="stylesheet" href='<c:url value="/inc/style.css"/>'>
    </head>
    <body>
        <p>Vous n'avez pas accès à l'espace restreint : vous devez vous <a href="<c:url value="/connexion"/>">Connexion</a> d'abord. </p>
    </body>
</html>