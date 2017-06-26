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
        
        <table>
                <tr>
                    <th>Filename</th>
                    <th>File type</th>
                    <th>Upload date</th>
                    <th>Size</th>               
                </tr>
                <%-- Parcours de la Map des clients en session, et utilisation de l'objet varStatus. --%>
                <c:forEach items="${ sessionScope.files }" var="file" varStatus="boucle">
                <%-- Simple test de parité sur l'index de parcours, pour alterner la couleur de fond de chaque ligne du tableau. --%>
                <tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
                    <%-- Affichage des propriétés du bean Client, qui est stocké en tant que valeur de l'entrée courante de la map --%>
                    <td><c:out value="${ file.filename }"/></td>
                    <td><c:out value="${ file.fileType }"/></td>
                    <td><c:out value="${ file.dateUpload }"/></td>
                    <td><c:out value="${ file.size_file }"/></td>
                </tr>
                </c:forEach>
            </table>
        
    </body>
</html>