<%-- 
    Document   : all_materials
    Created on : Aug 29, 2018, 8:36:14 PM
    Author     : User
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<body>
    <div class="container-fluid">
        <br>

        <div class="row">
            <div class="col-xl-4 col-lg-8 col-md-12 col-sm-12 col-12">
                <br>
            </div>
            <table  id="prijemnice1" class="table table-hover table-striped table-bordered">
                <thead>
                    <tr>
                        <th scope="col">Broj prijemnice</th>
                        <th scope="col">Datum prijema</th>
                        <th scope="col">Datum unosa</th>
                        <th scope="col">Vrednost</th>
                        <th scope="col">Iznos PDV-a</th>
                        <th scope="col">Vrednost sa PDV-om</th>
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
            var tabela = $("#prijemnice1").DataTable({
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
                    "zeroRecords": "Sistem ne moze da nadje prijemnicu po zadatoj vrednosti.",
                    "info": "Prikazivanje _START_ do _END_ od _TOTAL_ prijemnica",
                    "lengthMenu": "Prikaži _MENU_  prijemnica"

                },
                "destroy": true,
                "processing": true, // for show progress bar
                "filter": true, // this is for disable filter (search box)
                "orderMulti": false, // for disable multiple column at once
                ajax: {
                    "url": "/NJProjekatFED/goods_received_note/json",
                    "type": "GET",
                    "datatype": "json",
                    dataSrc: ''
                },
                "columns": [
                    {"data": "brojPrijemnice", "name": "Broj prijemnice", "autoWidth": true},
                    {"data": "datum", "name": "Datum", "autoWidth": true,
                        "render": function (data, type, row) {
                            var date = new Date(data);
                            var day = date.getDate();
                            var month = date.getMonth() + 1;
                            var year = date.getFullYear();
                            return day + "." + month + "." + year;
                        }},
                    {"data": "datumUnosa", "name": "DatumUnosa", "autoWidth": true,
                        "render": function (data, type, row) {
                            var date = new Date(data);
                            var day = date.getDate();
                            var month = date.getMonth() + 1;
                            var year = date.getFullYear();
                            return day + "." + month + "." + year;
                        }},
                    {"data": "ukupno", "name": "Ukupno", "autoWidth": true},
                    {"data": "ukupanPDV", "name": "ukupanPDV", "autoWidth": true},
                    {"data": "ukupnoSaPDV", "name": "ukupnoSaPDV", "autoWidth": true},
                    {
                        "render": function (data, type, full, meta) {
                            return '<a class="fa fa-info-circle btn btn-info" href="/NJProjekatFED/goods_received_note/find_goods_received_note/' + full.brojPrijemnice + '"></a>';
                        }
                    },
                    {
                        "render": function (data, type, full, meta) {
                            return '<a class="fa fa-cogs btn btn-info" href="/NJProjekatFED/goods_received_note/update_goods_received_note/' + full.brojPrijemnice + '"></a>';
                        }
                    },
                    {
                        "render": function (data, type, full, meta) {
                            return '<a class="fa fa-remove btn btn-info" href="/NJProjekatFED/goods_received_note/remove_goods_received_note/' + full.brojPrijemnice + '"></a>';
                        }
                    }
                ]
            });
        });
    </script> 
</body>
