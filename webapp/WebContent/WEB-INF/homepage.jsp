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
    	<h1>Welcome  ${sessionScope.companySession.companyName}</h1>
    	
        <p>You have access at your homepage.</p>
        <p>You have upload ${sessionScope.numberResourceFile} resource files and ${sessionScope.numberDataFile} for ${sessionScope.quantityData} of data.</p>
        
        <p><a href='<c:url value="logout"/>'>Logout</a></p>
        
        <h2>Data files</h2>
        
        <table>
                <tr>
                    <th>Filename</th>
                    <th>File type</th>
                    <th>Upload date</th>
                    <th>Size</th>               
                </tr>
                <%-- Parcours de la Map des clients en session, et utilisation de l'objet varStatus. --%>
                <c:forEach items="${ sessionScope.dataFiles }" var="file" varStatus="boucle">
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
        
         <h2>Resource files</h2>  
            
        <table>
                <tr>
                    <th>Filename</th>
                    <th>File type</th>
                    <th>Upload date</th>
                    <th>Size</th>               
                </tr>
                <%-- Parcours de la Map des clients en session, et utilisation de l'objet varStatus. --%>
                <c:forEach items="${ sessionScope.resourceFiles }" var="file" varStatus="boucle">
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