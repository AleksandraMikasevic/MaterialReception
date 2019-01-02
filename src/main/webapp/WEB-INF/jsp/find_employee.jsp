<%-- 
    Document   : find_employee
    Created on : Nov 22, 2018, 3:09:33 PM
    Author     : User
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<container>
    <div class="forma">
        <div class="main-div">
            <div class="panel">
                <h2>Prikaz prijavljenog zaposlenog</h2>
                <p>Informacije o zapposlenom</p>
                <div class="errorblock">
                    <c:if test="${not empty error}">${error}</c:if>
                    </div>
                </div>
            <form:form modelAttribute="employee">
                <div class="form-group">
                    <form:label path="korisnickoIme">Korisnicko ime</form:label>
                    <form:input disabled="true" path="korisnickoIme" class="form-control" id="korisnickoIme" placeholder="korisnickoIme" value= "${employee.korisnickoIme}"/>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <form:label path="prezime">Prezime zaposlenog</form:label>
                        <form:input disabled="true" path="prezime" class="form-control" id="prezime" placeholder="prezime" value= "${employee.prezime}"/>
                    </div>
                    <div class="form-group col-md-6">
                        <form:label path="ime">Ime zaposlenog</form:label>
                        <form:input disabled="true" path="ime" class="form-control" id="ime" placeholder="Ime" value= "${employee.ime}"/>
                    </div>
                </div>
                <div class="form-group">
                    <form:label path="jmbg">JMBG</form:label>
                    <form:input disabled="true" path="jmbg" class="form-control" id="jmbg" placeholder="JMBG" value= "${employee.jmbg}"/>
                </div>

            </form:form>
        </div>
    </div>
</container>