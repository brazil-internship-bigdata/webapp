<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
		<title>Admin</title>
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
    	
			        <p>You have access at the administration page.</p>
			       
			        <p>Number of company : <strong> ${sessionScope.nbCompany}</strong></p>
			        <p>Number of file</p>
			        <p>quantite de donnée</p>
			        
			        <span class="glyphicon glyphicon-log-out" aria-hidden="true"> </span><a href='<c:url value="logout"/>'> Logout</a>
			       
    			</div>
    	
		        <h2>Company list</h2>
		        <table class="table table-striped table-condensed">
		                <tr>
		                    <th>Company name</th>
		                    <th>Company full name</th>
		                    <th>Responsible 1 name</th>
		                    <th>Responsible 1 email</th>
		                  	<th>Responsible 1 phone</th>       
		                  	<th>Number of data file upload</th>
		                  	<th>Quantity of data</th>    
		                </tr>
		                <c:forEach items="${ sessionScope.companies }" var="company" varStatus="boucle">
		                <tr>
		                    <td><c:out value="${ company.companyName }"/></td>
		                    <td><c:out value="${ company.companyFullName }"/></td>
		                    <td><c:out value="${ company.responsible1Name }"/></td>
		                    <td><c:out value="${ company.responsible1Email}"/></td>
		                    <td><c:out value="${ company.responsible1Phone}"/></td>
		                    <td><c:out value="${ company.dataFileNumber}"/></td>
		                    <td><c:out value="${ company.dataQuantity}"/></td>
		                </tr>
		                </c:forEach>
		          </table>
	    	</div>
    	</div>
          
        <script src="https://code.jquery.com/jquery-1.11.2.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
        
    </body>
</html>