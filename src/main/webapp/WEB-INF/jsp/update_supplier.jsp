<%-- 
    Document   : update_supplier
    Created on : Nov 22, 2018, 5:09:04 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<container>
    <div class="forma">
        <div class="main-div">
            <div class="panel">
                <h2>Izmena dobavljaca</h2>
                <p>Unesite nove informacije o dobavljacu</p>
                <div class="errorblock">
                    <c:if test="${not empty error}">${error}</c:if>
                    </div>
                </div>
            <form:form action="/NJProjekatFED/supplier/update_supplier/${supplier.pib}" method="post" modelAttribute="supplier">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <form:label path="pib">PIB</form:label>
                        <form:input disabled="true" path="pib" class="form-control" id="pib" placeholder="PIB" value= "${supplier.pib}"/>
                    </div>
                    <div class="form-group col-md-6">
                        <form:label path="sediste">Sediste</form:label>
                        <form:input path="sediste" class="form-control" id="sediste" placeholder="Sediste" value= "${supplier.sediste}"/>
                    </div>
                </div>
                <div class="form-group">
                    <form:label path="naziv">Naziv</form:label>
                    <form:input path="naziv" class="form-control" id="naziv" placeholder="Naziv" value= "${supplier.naziv}"/>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <button type="submit" class="btn btn-primary"><i class="fa fa-check"></i></button>                    </div>
                    <div class="form-group col-md-6">
                        <a href="/NJProjekatFED/supplier/all_suppliers" class="btn btn-primary"><i class="fa fa-reply"></i></a>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</container>
<script>
    $(document).ready(function () {
        $('form').bootstrapValidator({
            fields: {
                pib : {
                    validators: {
                        notEmpty: {
                            message: 'Unesite PIB dobavljaca'
                        }
                    }
                },
                sediste: {
                    validators: {
                        notEmpty: {
                            message: 'Unesite sediste dobavljaca'
                        }
                    }
                },
                naziv: {
                    validators: {
                        notEmpty: {
                            message: 'Unesite naziv dobavljaca'
                        }
                    }
                }
            }
        });
    });

</script>

