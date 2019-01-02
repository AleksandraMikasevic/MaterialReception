<%-- 
    Document   : remove_supplier
    Created on : Nov 22, 2018, 5:08:46 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<container>
    <div class="forma">
        <div class="main-div">
            <div class="panel">
                <h2>Brisanje dobavljaca</h2>
                <p>Informacije o dobavljacu</p>
                <div class="errorblock">
                    <c:if test="${not empty error}">${error}</c:if>
                </div>
            </div>
            <form:form action="/NJProjekatFED/supplier/remove_supplier/${supplier.pib}" method="post" modelAttribute="supplier">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <form:label path="pib">PIB</form:label>
                        <form:input disabled="true" path="pib" class="form-control" id="PIB" placeholder="pib"/>
                    </div>
                    <div class="form-group col-md-6">
                        <form:label path="sediste">Sediste</form:label>
                        <form:input disabled="true" path="sediste" class="form-control" id="Sediste" placeholder="sediste"/>
                    </div>
                </div>
                <div class="form-group">
                    <form:label path="naziv">Naziv</form:label>
                    <form:input disabled="true" path="naziv" class="form-control" id="Naziv" placeholder="naziv"/>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <button type="submit" class="btn btn-primary"><i class="fa fa-remove"></i></button>
                    </div>
                    <div class="form-group col-md-6">
                        <a href="/NJProjekatFED/supplier/all_suppliers" class="btn btn-primary"><i class="fa fa-reply"></i></a>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</container>
