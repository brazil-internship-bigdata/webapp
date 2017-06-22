<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Signin</title>
         <link type="text/css" rel="stylesheet" href='<c:url value="/inc/style.css"/>'>
    </head>
    <body>
    	<form method="post" action="signin">
    	  <fieldset>
                <legend>Signup</legend>
                <p>Please sign up with this form </p>
                
                <label for="company_name">Company name <span class="requis">*</span></label>
                <input type="text" id="company_name" name="company_name" value="<c:out value="${company.companyName}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.errors['company_name']}</span>
                <br />
                
                <label for="company_full_name">Company full name <span class="requis">*</span></label>
                <input type="text" id="company_full_name" name="company_full_name" value="<c:out value="${company.companyFullName}"/>" size="20" maxlength="100" />
                <span class="erreur">${form.errors['company_full_name']}</span>
                <br />
                
                <label for="password_company">Password <span class="requis">*</span></label>
                <input type="password" id="password_company" name="password_company" value="" size="20" maxlength="20" />
                <span class="erreur">${form.errors['password_company']}</span>
                <br />
                
                  
                <label for="password_confirmation">Password confirmation <span class="requis">*</span></label>
                <input type="password" id="password_confirmation" name="password_confirmation" value="" size="20" maxlength="20" />
                <span class="erreur">${form.errors['password_confirmation']}</span>
                <br />
                
                
                <label for="responsible_1_name">Responsible 1 name <span class="requis">*</span></label>
                <input type="text" id="responsible_1_name" name="responsible_1_name" value="<c:out value="${company.responsible1Name}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.errors['responsible_1_name']}</span>
                <br />
                
                <label for="responsible_1_phone">Responsible 1 phone <span class="requis">*</span></label>
                <input type="text" id="responsible_1_phone" name="responsible_1_phone" value="<c:out value="${company.responsible1Phone}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.errors['responsible_1_phone']}</span>
                <br />
                
                <label for="responsible_1_email">Responsible 1 email <span class="requis">*</span></label>
                <input type="email" id="responsible_1_email" name="responsible_1_email" value="<c:out value="${company.responsible1Email}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.errors['responsible_1_email']}</span>
                <br />
                
                 
                <label for="responsible_2_name">Responsible 2 name </label>
                <input type="text" id="responsible_2_name" name="responsible_2_name" value="<c:out value="${company.responsible2Name}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.errors['responsible_2_name']}</span>
                <br />
                
                <label for="responsible_2_phone">Responsible 2 phone </label>
                <input type="text" id="responsible_2_phone" name="responsible_2_phone" value="<c:out value="${company.responsible2Phone}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.errors['responsible_2_phone']}</span>
                <br />
                
                <label for="responsible_2_email">Responsible 2 email </label>
                <input type="email" id="responsible_2_email" name="responsible_2_email" value="<c:out value="${company.responsible2Email}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.errors['responsible_2_email']}</span>
                <br />
                
                <label for="project_responsible">Project responsible <span class="requis">*</span></label>
                <input type="text" id="project_responsible" name="responsible_1_name" value="<c:out value="${company.projectResponsible}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.errors['project_responsible']}</span>
                <br />
                
                <label for="submission_type">Submission type <span class="requis">*</span></label>
                <input type="text" id="submission_type" name=submission_type value="<c:out value="${company.submissionType}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.errors['submission_type']}</span>
                <br />
                
                <label for="file_type">File type <span class="requis">*</span></label>
                <input type="text" id="file_type" name="file_type" value="<c:out value="${company.fileType}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.errors['file_type']}</span>
                <br />
                
                   <input type="submit" value="Inscription" class="sansLabel" />
                <br />
                
                <p class="${empty form.errors ? 'succes' : 'erreur'}">${form.results}</p>
            </fieldset>
        </form>
        
       <p><a href='<c:url value="/login"/>'>Connexion</a></p>
    </body>
</html>