<%-- 
    Document   : add_material
    Created on : Aug 29, 2018, 8:36:14 PM
    Author     : User
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<container>
    <div class="forma">
        <div class="main-div" style="max-width: 50%">
            <div class="panel">
                <h2>Prikaz vagarske potvrde</h2>
                <p>Informacije o vagarskoj potvrdi</p>
                <div class="errorblock">
                    <c:if test="${not empty error}">${error}</c:if>
                    </div>
                </div>
            <form:form modelAttribute="cw">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <form:label path="brojVagarskePotvrde">Broj vagarske potvrde</form:label>
                        <form:input disabled="true" path="brojVagarskePotvrde" class="form-control" id="brojVagarskePotvrde" placeholder="brojVagarskePotvrde"/>
                    </div>
                    <div class="form-group col-md-6">
                        <form:label path="datum">Datum</form:label>
                        <form:input disabled="true" path="datum" class="form-control" id="datum" placeholder="datum"/>
                    </div>
                    <div class="form-group col-md-12">
                        <form:label path="ukupnaMasa">Ukupna masa</form:label>
                        <form:input disabled="true" path="ukupnaMasa" class="form-control" id="ukupnaMasa" placeholder="ukupnaMasa"/>
                    </div>
                </div>
                <div class="form-group">
                    <form:label path="jmbg">Zaposleni</form:label>
                    <form:input disabled="true" path="jmbg" class="form-control" id="jmbg" placeholder="jmbg"/>
                </div>
                <label>Stavke vagarske potvrde</label>
                <br>
                <table  id="stavke" class="table table-hover table-striped table-bordered">
                    <thead>
                        <tr>
                            <th scope="col">Redni broj</th>
                            <th scope="col">Bruto masa</th>
                            <th scope="col">Neto masa</th>
                            <th scope="col">Registracija vozila</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${cw.stavkavagarskepotvrdeCollection}" var="item">
                            <tr>
                                <th scope="row">${item.redniBroj}</th>
                                <td>${item.brutoMasa}</td>
                                <td>${item.netoMasa}</td>
                                <td>${item.registarskiBrojVozila}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <br>
                <div class="form-row">
                    <div class="form-group col-md-6">
                    <a href="<c:url value='/weight_certificate/all_weight_certificate'/>" class="btn btn-primary"><i class="fa fa-reply"></i></a>
                    </div>
                    <div class="form-group col-md-6">
                        <c:if test="${not empty brojPrijemnice}">
                        <a href="<c:url value='/goods_received_note/find_goods_received_note/${brojPrijemnice}'/>" class="btn btn-primary">Prijemnica</a>
                    </c:if>
                    <c:if test="${empty brojPrijemnice}">
                        <a href="<c:url value='/goods_received_note/add_goods_received_note/${cw.brojVagarskePotvrde}'/>" class="btn btn-primary">Prijemnica</a>
                    </c:if>
                    </div>
                </div>
            </form:form>   
        </div>
    </div>
</container>
