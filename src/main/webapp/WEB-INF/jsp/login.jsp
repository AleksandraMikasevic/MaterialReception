<%-- 
    Document   : index
    Created on : Aug 29, 2018, 2:38:24 PM
    Author     : User
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<container onload='document.f.j_username.focus();'>
    <div class="forma">
        <div class="main-div">
            <div class="panel">
            <h2>Prijava</h2>
            <p>Unesite korisnicko ime i lozinku</p>
                <div class="errorblock">
                    <c:if test="${not empty error}">
                        Your login was unsuccessful.<br/>
                        Caused: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message};
                    </c:if>                </div>
            </div>
            <form action="j_spring_security_check" name='f' method="post">
                <div class="form-group">
                    <label>Korisnicko ime:</label>
                    <input type ='text' name='j_username' class="form-control" value=''/>
                </div>
                <div class="form-group">
                    <label>Lozinka</label>
                    <input type ='password' name='j_password' class="form-control" value=''/>
                </div>
                <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                <button type="submit" name ='Submit' class="btn btn-primary"><i class="fa fa-check"></i></button>
            </form>
        </div>
    </div>
</container>
