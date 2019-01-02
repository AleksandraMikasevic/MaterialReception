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
        <div class="main-div">
            <div class="panel">
                <h2>Izmena prijemnice</h2>
                <p>Stavke prijemnice</p>
                <div class="errorblock">
                    <c:if test="${not empty error}">${error}
                    </c:if>
                </div>
            </div>
            <form>
                <div class="form-row">
                    <input type="hidden" name="brojStavke" id = "brojStavke" value ="" class="form-control"><br>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="kolicina">Kolicina</label>
                        <input type="text" name="kolicina" id = "kolicina" class="form-control"><br>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="kolicina">Materijal</label>
                        <select name="materijalID" id ="materijalID" class="form-control">
                            <c:forEach items="${listaMaterijala}" var="materijal">
                                <option value="${materijal.sifraMaterijala}"><c:out value="${materijal.nazivMaterijala}" /></option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <button type="button" class="btn btn-primary" id ="dodajStavkuDugme">Sacuvaj stavku</button>
                    </div>
                    <div class="form-group col-md-3">
                        <button type="button" id ="izbrisiStavkuDugme" class="btn btn-primary" disabled="true"><i class="fa fa-remove"></i></button>
                    </div> 
                    <div class="form-group col-md-3">
                        <button type="button" id ="izmeniStavku" class="btn btn-primary" disabled="true"><i class="fa fa-cogs"></i></button>
                    </div>
                </div>
            </form>
            <br>
            <div class ="row">
                <div class="tabelaCentar">
                    <table  id ="stavke1" class="table table-hover table-striped table-bordered">
                        <thead>
                            <tr>
                                <th scope="col">Redni broj</th>
                                <th scope="col">brojStavke</th>
                                <th scope="col">Kolicina</th>
                                <th scope="col">Sifra materijala</th>
                            </tr>
                        </thead>

                    </table>
                    <form action="/NJProjekatFED/goods_received_note/update_goods_received_note" method="POST">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <button type="sumbit" class="btn btn-primary"><i class="fa fa-check">Sacuvaj prijemnicu</i></button>
                    </form>
                </div>
            </div>
        </div>
    </div>


</container>

<style>
    .ui-datepicker {
        background: black;
        color: #EEE;
    }
</style>
<script>
    $(document).ready(function () {
        var t = $("#stavke1").DataTable({
            dom: "Brtip",
            // for disable multiple column at once
            ajax: {
                "url": "/NJProjekatFED/goods_received_note/items_json/",
                "type": "GET",
                "datatype": "json",
                dataSrc: ''
            }, buttons: [
                'copy', 'excel', 'pdf'
            ],
            "columns": [
                {"data": "redniBroj", "name": "redniBroj", "autoWidth": true},
                {"data": "stavkaprijemnicePK.brojStavke", "name": "stavkaprijemnicePK", "autoWidth": true},
                {"data": "kolicina", "name": "kolicina", "autoWidth": true},
                {"data": "sifraMaterijala.sifraMaterijala", "name": "sifraMaterijala", "autoWidth": true}
            ]
        });
        var counter = 1;

        $('#dodajStavkuDugme').on('click', function () {

            if ($("#brojStavke").val() === "") {
                $.ajax({
                    url: "/NJProjekatFED/goods_received_note/add_item/" + $("#materijalID").val() + "/" + $("#kolicina").val(),
                    type: 'GET',
                    "datatype": "json",
                    success: function (data) {
                        t.ajax.reload();
                        document.getElementById("kolicina").value = "";
                        document.getElementById("materijalID").value = "1";
                        document.getElementById("brojStavke").value = "";
                        $("#izbrisiStavkuDugme").prop('disabled', true);
                        $("#izmeniStavku").prop('disabled', true);
                    },
                    error: function (jqxhr, status, exception) {
                        alert('Exception:', exception.toString());
                    }
                });
            } else {
                $.ajax({
                    url: "/NJProjekatFED/goods_received_note/update_item/" + $("#brojStavke").val() + "/" + $("#materijalID").val() + "/" + $("#kolicina").val(),
                    type: 'GET',
                    "datatype": "json",
                    success: function (data) {
                        t.ajax.reload();
                        document.getElementById("kolicina").value = "";
                        document.getElementById("materijalID").value = "1";
                        document.getElementById("brojStavke").value = "";
                        $("#izbrisiStavkuDugme").prop('disabled', true);
                        $("#izmeniStavku").prop('disabled', true);

                    },
                    error: function (jqxhr, status, exception) {
                        alert('Exception:', exception.toString());
                    }
                });
            }
        });



        $('#stavke1 tbody').on('click', 'tr', function () {
            if ($(this).hasClass('selected')) {
                $(this).removeClass('selected');
                $("#izbrisiStavkuDugme").prop('disabled', true);
                $("#izmeniStavku").prop('disabled', true);
            } else {
                t.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
                $("#izbrisiStavkuDugme").prop('disabled', false);
                $("#izmeniStavku").prop('disabled', false);
            }
        });

        $('#izbrisiStavkuDugme').click(function () {

            var selectedRow = t.cell('.selected', 1).data();
            $.ajax({
                url: "/NJProjekatFED/goods_received_note/remove_item/" + selectedRow,
                type: 'GET',
                success: function (data) {
                    t.ajax.reload();
                    document.getElementById("brojStavke").value = "";
                    $("#izbrisiStavkuDugme").prop('disabled', true);
                    $("#izmeniStavku").prop('disabled', true);
                },
                error: function (jqxhr, status, exception) {
                    alert('Exception:', exception.toString());
                }
            });
        });
        $('#izmeniStavku').click(function () {
            var selectedRowKolicina = t.cell('.selected', 2).data();
            var selectedRowMaterijal = t.cell('.selected', 3).data();
            var selectedRowBrojStavke = t.cell('.selected', 1).data();
            document.getElementById("kolicina").value = selectedRowKolicina;
            document.getElementById("materijalID").value = selectedRowMaterijal;
            document.getElementById("brojStavke").value = selectedRowBrojStavke;
        });
    });
</script>
