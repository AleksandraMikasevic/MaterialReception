<%-- 
    Document   : all_materials
    Created on : Aug 29, 2018, 8:36:14 PM
    Author     : User
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.aleksandra.domen.Materijal"%>
<!DOCTYPE html>
<body>
    <div class="container-fluid">
        <br>

        <div class="row">
            <div class="col-xl-4 col-lg-8 col-md-12 col-sm-12 col-12">
                <a href="<c:url value='/material/add_material'/>" class="btn btn-primary" style="font-size:24px;"><i class="fa fa-plus-square-o"></i></a>
            </div>
            <table  id="materijali1" class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th scope="col">Sifra materijala</th>
                        <th scope="col">Naziv materijala</th>
                        <th scope="col">Jedinica mere</th>
                        <th scope="col">PDV</th>
                        <th scope="col">Cena</th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
    <script>
        $(document).ready(function () {
            var tabela = $("#materijali1").DataTable({
                  "language": {
                    "search": "Pretraga",
                    "processing": "Obrada",
                    "orderCellsTop": false,
                    "paginate": {
                        "first": "Prvi",
                        "previous": "Prethodni",
                        "next": "Sledeći",
                        "last": "Poslednji"
                    },
                    "loadingRecords": "Učitavanje podataka",
                    "emptyTable": "Tabela je prazna",
                    "zeroRecords": "Sistem ne moze da nadje materijal po zadatoj vrednosti.",
                    "info": "Prikazivanje _START_ do _END_ od _TOTAL_ materijala",
                    "lengthMenu": "Prikaži _MENU_ materijala"

                },
                "destroy": true,
                "processing": true, // for show progress bar
                "filter": true, // this is for disable filter (search box)
                "orderMulti": false, // for disable multiple column at once
                ajax: {
                    "url": "/NJProjekatFED/material/all_materials_json",
                    "type": "GET",
                    "datatype": "json",
                    dataSrc: ''
                },
                "columns": [
                    {"data": "sifraMaterijala", "name": "Šifra materijala", "autoWidth": true},
                    {"data": "nazivMaterijala", "name": "Naziv materijala", "autoWidth": true},
                    {"data": "jedinicaMere", "name": "Jedinica Mere", "autoWidth": true},
                    {"data": "pdv", "name": "PDV", "autoWidth": true, "render": function (data, type, full, meta) {
                            return data + "%";
                        }},
                    {"data": "cena", "name": "Cena", "autoWidth": true, "render": function (data, type, full, meta) {
                            return data + " din";
                        }},
                    {
                        "render": function (data, type, full, meta) {
                            return '<a class="fa fa-info-circle btn btn-info" href="/NJProjekatFED/material/find_material/' + full.sifraMaterijala + '"></a>';
                        }
                    },
                    {
                        "render": function (data, type, full, meta) {
                            return '<a class="fa fa-cogs btn btn-info" href="/NJProjekatFED/material/update_material/' + full.sifraMaterijala + '"></a>';
                        }
                    },
                    {
                        "render": function (data, type, full, meta) {
                            return '<a class="fa fa-remove btn btn-info" href="/NJProjekatFED/material/remove_material/' + full.sifraMaterijala + '"></a>';
                        }
                    }
                ]
            });
        });
    </script>               
</body>


