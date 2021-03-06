<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Login</title>
      
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
	                        
	                    <form id="loginform" class="form-horizontal" role="form" method="post" action="login">
	                                
	                        <div style="margin-bottom: 25px" class="input-group">
	                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
	                            <input id="company_name" type="text" class="form-control" name="company_name" value="" placeholder="company name">  
	                             <span class="error">${form.errors['company_name']}</span>                                      
	                        </div>
	                            
	                        <div style="margin-bottom: 25px" class="input-group">
	                            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
	                            <input id="password_company" type="password" class="form-control" name="password_company" placeholder="password">
	                             <span class="error">${form.errors['password_company']}</span>
	                        </div>
	
	
	
	                        <div style="margin-top:10px" class="form-group">
	                            <!-- Button -->
	                            <div class="col-sm-12 controls">
	                                <input type="submit" class="btn btn-info" value="Login">
	                            </div>
	                        </div>
	
	
	                        <div class="form-group">
	                            <div class="col-md-12 control">
	                                <div style="border-top: 1px solid#888; padding-top:15px; font-size:85%" >
	                                    Don't have an account! 
	                                <a href="signup">
	                                    Sign Up Here
	                                </a>
	                                </div>
	                            </div>
	                        </div>   
	                        
	                         <p class="${empty form.errors ? 'succes' : 'error'}">${form.results}</p> 
	                    </form>     
	
	
	
	                </div>                     
	            </div>  
	        </div>
	    </div>

        <script src="https://code.jquery.com/jquery-1.11.2.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
        
    </body>
</html>