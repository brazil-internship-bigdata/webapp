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
    	    <div class="container">    
	        <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
	            <div class="panel panel-info" >
	                <div class="panel-heading">
	                    <div class="panel-title">Sign In</div>
	                </div>     
	
	                <div style="padding-top:30px" class="panel-body" >
	
	                    <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
    
  	 					<form action="uploadResource"  method="post" enctype="multipart/form-data" class="form-horizontal">
         
         					
              				 <div class="form-group required"> 
                            <label for="companyList" class="control-label col-md-4  requiredField">Company<span class="asteriskField">*</span> </label>
                            <div class="controls col-md-8 "> 
                            	 <select name="companyList" id="companyList">
                 						<option value="">...</option>
				                   <%-- Boucle sur la map des clients --%>
				                   <c:forEach items="${ sessionScope.companyList }" var="mapCompany">
				                   <option value="${ mapCompany.companyName }">${ mapCompany.companyName }</option>
				                   </c:forEach>
				               </select>          
                            </div>
                        </div> 
              
              
              				  
    				   <div class="form-group required"> 
                            <label for="file" class="control-label col-md-4  requiredField">File<span class="asteriskField">*</span> </label>
                            <div class="controls col-md-8 "> 
                                  <input type="file" id="file" name="file" />
                            </div>
                        </div> 
                        
                        
                        
                 			 <div style="margin-top:10px" class="form-group">
                            <!-- Button -->
                            <div class="col-sm-12 controls">
                                <input type="submit" class="btn btn-info pull-right" value="Send">
                            </div>
                        </div>
                        
					</form>
				</div>
		    </div>
		</div>
		</div>
         
        <script src="https://code.jquery.com/jquery-1.11.2.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
        
    </body>
</html>