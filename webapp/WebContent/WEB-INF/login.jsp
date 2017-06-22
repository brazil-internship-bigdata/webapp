<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Login</title>
        <link type="text/css" rel="stylesheet" href='<c:url value="/inc/style.css"/>'>
    </head>
    <body>
        <form method="post" action="login">
            <fieldset>
                <legend>Login</legend>

                <label for="company_name">Company name <span class="require">*</span></label>
                <input type="text" id="company_name" name="company_name" value="<c:out value="${company.companyName}"/>" size="20" maxlength="60" />
                <span class="error">${form.errors['company_name']}</span>
                <br />

                 <label for="password_company">Password <span class="require">*</span></label>
                <input type="password" id="password_company" name="password_company" value="" size="20" maxlength="20" />
                <span class="error">${form.errors['password_company']}</span>
                <br />
                
                <input type="submit" value="Connexion" class="sansLabel" />
                <br />
                
                <p class="${empty form.errors ? 'succes' : 'error'}">${form.results}</p>
            </fieldset>
        </form>
        
          <p><a href='<c:url value="/signup"/>'>Sign up</a></p>
    </body>
</html>