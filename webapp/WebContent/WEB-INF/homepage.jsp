<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
		<title>Homepage</title>
		<link type="text/css" rel="stylesheet" href='<c:url value="/inc/style.css"/>'>
		<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>
    
    
    	<div class="container ">
    	
    		<div class="receipt-main">
    		
    			<div class="text-center">
    				<h1>Welcome  ${sessionScope.companySession.companyName}</h1>
    	
			        <p>You have access at your homepage.</p>
			        <p>You have upload <strong>${sessionScope.numberResourceFile}</strong> resource files and <strong>${sessionScope.numberDataFile}</strong> data files for <strong>${sessionScope.quantityData}</strong> of data.</p>
			        
			        <span class="glyphicon glyphicon-log-out" aria-hidden="true"> </span><a href='<c:url value="logout"/>'> Logout</a>
			       
    			</div>
    	
		        <h2>Data files</h2>
		        <table class="table table-striped table-condensed">
		                <tr>
		                    <th>Filename</th>
		                    <th>File type</th>
		                    <th>Upload date</th>
		                    <th>Size</th>               
		                </tr>
		                <c:forEach items="${ sessionScope.dataFiles }" var="file" varStatus="boucle">
		                <tr>
		                    <td><c:out value="${ file.filename }"/></td>
		                    <td><c:out value="${ file.fileType }"/></td>
		                    <td><c:out value="${ file.dateUpload }"/></td>
		                    <td><c:out value="${ file.size_file }"/></td>
		                </tr>
		                </c:forEach>
		          </table>
		        
		         <h2>Resource files</h2>  
		        <table class="table table-striped table-condensed">
		                <tr>
		                    <th>Filename</th>
		                    <th>File type</th>
		                    <th>Upload date</th>
		                    <th>Size</th>               
		                </tr>
		                <c:forEach items="${ sessionScope.resourceFiles }" var="file" varStatus="boucle">
		                <tr>
		                    <td><c:out value="${ file.filename }"/></td>
		                    <td><c:out value="${ file.fileType }"/></td>
		                    <td><c:out value="${ file.dateUpload }"/></td>
		                    <td><c:out value="${ file.size_file }"/></td>
		                </tr>
		                </c:forEach>
		    	</table>
	    	</div>
    	</div>
          
        <script src="https://code.jquery.com/jquery-1.11.2.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
        
    </body>
</html>