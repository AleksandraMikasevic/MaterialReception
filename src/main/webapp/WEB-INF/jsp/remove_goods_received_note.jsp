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
                <h2>Brisanje prijemnice</h2>
                <p>Informacije o prijemnici</p>
                <div class="errorblock">
                    <c:if test="${not empty error}">${error}</c:if>
                    </div>
                </div>
            <form:form action="/NJProjekatFED/goods_received_note/remove_goods_received_note/${grcn.brojPrijemnice}" method="post" modelAttribute="grcn">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <form:label path="brojPrijemnice">Broj prijemnice</form:label>
                        <form:input disabled="true" path="brojPrijemnice" class="form-control" id="brojPrijemnice" placeholder="brojPrijemnice"/>
                    </div>
                    <div class="form-group col-md-6">
                        <form:label path="datum">Datum</form:label>
                        <form:input disabled="true" path="datum" class="form-control" id="datum" placeholder="datum"/>
                    </div>
                </div>
                <div class="form-group">
                    <form:label path="ukupno">Ukupno</form:label>
                    <form:input disabled="true" path="ukupno" class="form-control" id="ukupno" placeholder="ukupno"/>
                </div>
                <div class="form-group">
                    <form:label path="jmbg">Zaposleni</form:label>
                    <form:input disabled="true" path="jmbg" class="form-control" id="jmbg" placeholder="jmbg"/>
                </div>
                <div class="form-group">
                    <form:label path="brojVagarskePotvrde">Vagarska potvrda</form:label>
                    <form:input disabled="true" path="brojVagarskePotvrde" class="form-control" id="brojVagarskePotvrde" placeholder="brojVagarskePotvrde"/>
                    <label>Stavke prijemnice</label>
                    <br>
                    <table  id="stavke" class="table table-hover table-striped table-bordered">
                        <thead>
                            <tr>
                                <th scope="col">Redni broj</th>
                                <th scope="col">Kolicina</th>
                                <th scope="col">Sifra materijala</th>
                                <th scope="col">Cena</th>
                                <th scope="col">Vrednost</th>
                                <th scope="col">PDV</th>
                                <th scope="col">Vrednost sa PDV-om</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${grcn.stavkaprijemniceCollection}" var="item">
                                <tr>
                                    <th scope="row">${item.redniBroj}</th>
                                    <td>${item.kolicina}</td>
                                    <td>${item.sifraMaterijala.nazivMaterijala}</td>
                                    <td>${item.sifraMaterijala.cena}</td>
                                    <td>${item.sifraMaterijala.cena*item.kolicina}</td>
                                    <td>${item.sifraMaterijala.pdv}%</td>
                                    <td>${item.sifraMaterijala.cena*item.kolicina*(item.sifraMaterijala.pdv+100)/100}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <button type="submit" class="btn btn-primary"><i class="fa fa-remove"></i></button>
                        </div>
                        <div class="form-group col-md-6">
                            <a href="<c:url value='/goods_received_note/all_goods_received_notes'/>" class="btn btn-primary"><i class="fa fa-reply"></i></a>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
</container>

