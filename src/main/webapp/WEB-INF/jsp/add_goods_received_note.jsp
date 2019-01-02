
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<container>
    <div class="forma">
        <div class="main-div">
            <div class="panel">
                <h2>Dodavanje prijemnice</h2>
                <p>Osnovne informacije o prijemnici</p>
                <div class="errorblock">
                    <c:if test="${not empty error}">${error}</c:if>
                    </div>
                </div>
            <form:form action="/NJProjekatFED/goods_received_note/add_goods_received_note_info" method="POST" modelAttribute="grcn">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <form:label path="brojPrijemnice">Broj prijemnice</form:label>
                        <form:input disabled="true" path="brojPrijemnice" class="form-control" id="brojPrijemnice" placeholder="brojPrijemnice"/>
                    </div>
                    <div class="form-group col-md-6">
                        <form:label path="datum">Datum</form:label>
                        <form:input path="datum" class="form-control" id="datepicker" placeholder="datum"/>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <form:label path="jmbg.jmbg">Zaposleni</form:label>
                        <form:select path="jmbg.jmbg" class="form-control">
                            <form:options items="${listaZaposlenih}" itemLabel="ime" itemValue="jmbg" />
                        </form:select>
                    </div>
                    <div class="form-group col-md-6">
                        <form:label path="brojVagarskePotvrde.brojVagarskePotvrde">Vagarska potvrda</form:label>
                        <form:input path="brojVagarskePotvrde.brojVagarskePotvrde" class="form-control" readonly="true" id="brojVagarskePotvrde" placeholder="brojVagarskePotvrde" />
                    </div>
                </div>
                <div class="form-group col-md-12">
                <form:label path="pib.pib">Dobavljac</form:label>
                    <form:select path="pib.pib" class="form-control">
                        <form:options items="${listaDobavljaca}" itemLabel="pib" itemValue="pib" />
                    </form:select>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <button type="submit" class="btn btn-primary"><i class="fa fa-check"></i></button>
                    </div>
                    <div class="form-group col-md-6">
                        <a href="<c:url value='/weight_certificate/all_weight_certificate'/>" class="btn btn-primary"><i class="fa fa-reply"></i></a>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</container>
<script>
    $(document).ready(function () {
        $("#datepicker").datepicker({dateFormat: 'yy-mm-dd'});
    });
    $(document).ready(function () {
        $('form').bootstrapValidator({
            fields: {
                datum: {
                    validators: {
                        notEmpty: {
                            message: 'Izaberite datum prijemnice.'
                        }
                    }
                }
            }
        });
    });
</script>

