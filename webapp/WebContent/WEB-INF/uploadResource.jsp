<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
		<title>Upload Resource</title>
		<link type="text/css" rel="stylesheet" href='<c:url value="/inc/style.css"/>'>
		<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>
    
    
    	 <form action="<c:url value="/uploadResource" />" method="post" enctype="multipart/form-data">
            <fieldset>
                <legend>Upload a resource file for a company</legend>
                
                <select name="companyList" id="companyList">
                   <option value="">Choose a company</option>
                   <%-- Boucle sur la map des clients --%>
                   <c:forEach items="${ sessionScope.companyList }" var="mapCompany">
                   <option value="${ mapCompany.companyName }">${ mapCompany.companyName }</option>
                   </c:forEach>
               </select>

               

                <label for="file">File to upload <span class="requis">*</span></label>
                <input type="file" id="file" name="file" />
                <br />
                
                <input type="submit" value="Send" />
                <br />                
            </fieldset>
        </form>
          
        <script src="https://code.jquery.com/jquery-1.11.2.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
        
    </body>
</html>