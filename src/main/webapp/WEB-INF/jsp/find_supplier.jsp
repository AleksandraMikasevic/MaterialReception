<%-- 
    Document   : find_supplier
    Created on : Nov 22, 2018, 5:09:18 PM
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
                <h2>Prikaz dobavljaca</h2>
                <p>Informacije o dobavljacu</p>
                <div class="errorblock">
                    <c:if test="${not empty error}">${error}</c:if>
                    </div>
                </div>
            <form:form modelAttribute="supplier">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <form:label path="pib">PIB</form:label>
                        <form:input disabled="true" path="pib" class="form-control" id="pib" placeholder="PIB" value= "${supplier.pib}"/>
                    </div>
                    <div class="form-group col-md-6">
                        <form:label path="sediste">Sediste</form:label>
                        <form:input disabled="true" path="sediste" class="form-control" id="sediste" placeholder="Sediste" value= "${supplier.sediste}"/>
                    </div>
                </div>
                <div class="form-group">
                    <form:label path="naziv">Naziv</form:label>
                    <form:input disabled="true" path="naziv" class="form-control" id="naziv" placeholder="Naziv" value= "${supplier.naziv}"/>
                </div>
                <div class="form-group">
                    <a href="<c:url value='/supplier/all_suppliers'/>" class="btn btn-primary"><i class="fa fa-reply"></i></a>
                </div>
            </form:form>
        </div>
    </div>
</container>