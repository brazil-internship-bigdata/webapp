<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Sign up</title>
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
	                    <div class="panel-title">Sign Up</div>
	                </div>     
	
	                <div style="padding-top:30px" class="panel-body" >
	
	                    <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
	                        
	                    <form id="loginform" class="form-horizontal" role="form" method="post" action="signup">
	                    
	                       <div class="form-group required">
	                            <label for="company_name" class="control-label col-md-4  requiredField"> Company name<span class="asteriskField">*</span> </label>
	                            <div class="controls col-md-8 ">
	                                <input class="input-md  textinput textInput form-control" id="company_name" maxlength="30" name=company_name placeholder="Company name" style="margin-bottom: 10px" type="text" />
	                            </div>
	                        </div>
	                        
	                        <div class="form-group required">
	                            <label for="company_full_name" class="control-label col-md-4  requiredField"> Company full name<span class="asteriskField">*</span> </label>
	                            <div class="controls col-md-8 ">
	                                <input class="input-md  textinput textInput form-control" id="company_full_name" maxlength="30" name=company_full_name placeholder="Company full name" style="margin-bottom: 10px" type="text" />
	                            </div>
	                        </div>
    
    						<div class="form-group required">
	                            <label for="password_company" class="control-label col-md-4  requiredField">Password<span class="asteriskField">*</span> </label>
	                            <div class="controls col-md-8 "> 
	                                <input class="input-md textinput textInput form-control" id="password_company" name="password_company" placeholder="Create a password" style="margin-bottom: 10px" type="password" />
	                            </div>
	                        </div>
	                        
	                        <div class="form-group required">
	                            <label for="password_confirmation" class="control-label col-md-4  requiredField">Re-Password<span class="asteriskField">*</span> </label>
	                            <div class="controls col-md-8 "> 
	                                <input class="input-md textinput textInput form-control" id="password_confirmation" name="password_confirmation" placeholder="Confirm password" style="margin-bottom: 10px" type="password" />
	                            </div>
	                        </div>
	                        
	                        <div class="form-group required"> 
	                            <label for="responsible_1_name" class="control-label col-md-4  requiredField">Responsible 1 name<span class="asteriskField">*</span> </label>
	                            <div class="controls col-md-8 "> 
	                                 <input class="input-md textinput textInput form-control" id="responsible_1_name" name="responsible_1_name" placeholder="Responsible 1 name" style="margin-bottom: 10px" type="text" />
	                            </div>
	                        </div> 
	                        
	                        <div class="form-group required"> 
	                            <label for="responsible_1_phone" class="control-label col-md-4  requiredField">Responsible 1 phone<span class="asteriskField">*</span> </label>
	                            <div class="controls col-md-8 "> 
	                                 <input class="input-md textinput textInput form-control" id="responsible_1_phone" name="responsible_1_phone" placeholder="Responsible 1 phone" style="margin-bottom: 10px" type="text" />
	                            </div>
	                        </div> 
	                        
	                        <div class="form-group required"> 
	                            <label for="responsible_1_email" class="control-label col-md-4  requiredField">Responsible 1 email<span class="asteriskField">*</span> </label>
	                            <div class="controls col-md-8 "> 
	                                 <input class="input-md textinput textInput form-control" id="responsible_1_email" name="responsible_1_email" placeholder="Responsible 1 email" style="margin-bottom: 10px" type="email" />
	                            </div>
	                        </div> 
	                        
	                        <div class="form-group "> 
	                            <label for="responsible_2_name" class="control-label col-md-4  ">Responsible 2 name</label>
	                            <div class="controls col-md-8 "> 
	                                 <input class="input-md textinput textInput form-control" id="responsible_2_name" name="responsible_2_name" placeholder="Responsible 2 name" style="margin-bottom: 10px" type="text" />
	                            </div>
	                        </div> 
	                        
	                        <div class="form-group "> 
	                            <label for="responsible_2_phone" class="control-label col-md-4  ">Responsible 2 phone </label>
	                            <div class="controls col-md-8 "> 
	                                 <input class="input-md textinput textInput form-control" id="responsible_2_phone" name="responsible_2_phone" placeholder="Responsible 2 phone" style="margin-bottom: 10px" type="text" />
	                            </div>
	                        </div> 
	                        
	                        <div class="form-group"> 
	                            <label for="responsible_2_email" class="control-label col-md-4 ">Responsible 2 email</label>
	                            <div class="controls col-md-8 "> 
	                                 <input class="input-md textinput textInput form-control" id="responsible_2_email" name="responsible_2_email" placeholder="Responsible 2 email" style="margin-bottom: 10px" type="email" />
	                            </div>
	                        </div> 
	                        
	    				   <div class="form-group required"> 
	                            <label for="project_responsible" class="control-label col-md-4  requiredField">Project responsible name<span class="asteriskField">*</span> </label>
	                            <div class="controls col-md-8 "> 
	                                 <input class="input-md textinput textInput form-control" id="project_responsible" name="project_responsible" placeholder="Project responsible name" style="margin-bottom: 10px" type="text" />
	                            </div>
	                        </div> 
	                        
	                        <div class="form-group required"> 
	                            <label for="submission_type" class="control-label col-md-4  requiredField">Submission type<span class="asteriskField">*</span> </label>
	                            <div class="controls col-md-8 "> 
	                            	<select name="submission_type" id="submission_type" class="form-control">
									    <option value="webservice" selected="selected">Webservice</option>
									    <option value="email">E-mail</option>
									</select>            
	                            </div>
	                        </div> 
	                        
	                        <div class="form-group required"> 
	                            <label for="file_type" class="control-label col-md-4  requiredField">File type<span class="asteriskField">*</span> </label>
	                            <div class="controls col-md-8 "> 
	                            	<select name="file_type" id="file_type" class="form-control">
									    <option value="csv" selected="selected">csv</option>
				   						 <option value="excel">excel</option>
									</select>            
	                            </div>
	                        </div> 
        	
        					<div class="form-group required"> 
	                            <label for="data_description" class="control-label col-md-4  requiredField">Data description<span class="asteriskField">*</span> </label>
	                            <div class="controls col-md-8 "> 
	                            	 <textarea class="form-control" id="data_description" name="data_description"></textarea>
	                            </div>
	                        </div> 
                

             
                  			 <div style="margin-top:10px" class="form-group">
	                            <!-- Button -->
	                            <div class="col-sm-12 controls">
	                                <input type="submit" class="btn btn-info" value="Sign up">
	                            </div>
	                        </div>
	                        
	                         <div class="form-group">
	                            <div class="col-md-12 control">
	                                <div style="border-top: 1px solid#888; padding-top:15px; font-size:85%" >
	                                   You have an account ? 
	                                <a href="login">
	                                    Login Here
	                                </a>
	                                </div>
	                            </div>
	                        </div>   
                	 	</form>     
            
                			<p class="${empty form.errors ? 'succes' : 'error'}">${form.results}</p>
                	</div>                     
	            </div>  
	        </div>
	    </div>

       
        <script src="https://code.jquery.com/jquery-1.11.2.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
    </body>
</html>