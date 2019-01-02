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
            <table  id="prijemnice1" class="table table-hover table-striped table-bordered">
                <thead>
                    <tr>
                        <th scope="col">Broj vagarske potvrde</th>
                        <th scope="col">Datum</th>
                        <th scope="col">Ukupna masa</th>
                        <th scope="col">Prikaz</th>
                    </tr>
                </thead>
            </table>

        </div>
    </div>
    <script>
        $(document).ready(function () {
            var tabela = $("#prijemnice1").DataTable({
                "destroy": true,
                "processing": true, // for show progress bar
                "filter": true, // this is for disable filter (search box)
                "orderMulti": false, // for disable multiple column at once
                ajax: {
                    "url": "/NJProjekatFED/weight_certificate/json",
                    "type": "GET",
                    "datatype": "json",
                    dataSrc: ''
                },
                "columns": [
                    {"data": "brojVagarskePotvrde", "name": "Broj vagarske potvrde", "autoWidth": true},
                    {"data": "datum", "name": "Datum", "autoWidth": true,
                        "render": function (data, type, row) {
                            var date = new Date(data);
                            var day = date.getDate();
                            var month = date.getMonth() + 1;
                            var year = date.getFullYear();
                            return day + "." + month + "." + year;
                        }},
                    {"data": "ukupnaMasa", "name": "Ukupno", "autoWidth": true},
                    {
                        "render": function (data, type, full, meta) {
                            return '<a class="fa fa-info-circle btn btn-info" href="/NJProjekatFED/weight_certificate/find_weight_certificate/' + full.brojVagarskePotvrde + '"></a>';
                        }
                    }
                ]
            });
        });
    </script> 
</body>
