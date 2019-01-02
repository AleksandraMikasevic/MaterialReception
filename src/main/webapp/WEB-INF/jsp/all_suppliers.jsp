<%-- 
    Document   : all_suppliers
    Created on : Nov 22, 2018, 5:08:22 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<body>
    <div class="container-fluid">
        <br>
        <div class="row">
            <div class="col-xl-4 col-lg-8 col-md-12 col-sm-12 col-12">
                <a href="<c:url value='/supplier/add_supplier'/>" class="btn btn-primary" style="font-size:24px;"><i class="fa fa-plus-square-o"></i></a>
            </div>
            <table  id="dobavljaci" class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th scope="col">PIB</th>
                        <th scope="col">Sediste</th>
                        <th scope="col">Naziv</th>
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
            var tabela = $("#dobavljaci").DataTable({
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
                    "zeroRecords": "Sistem ne moze da nadje dobavljaca po zadatoj vrednosti.",
                    "info": "Prikazivanje _START_ do _END_ od _TOTAL_ dobavljaca",
                    "lengthMenu": "Prikaži _MENU_  dobavljaca"

                },
                "destroy": true,
                "processing": true, // for show progress bar
                "filter": true, // this is for disable filter (search box)
                "orderMulti": false, // for disable multiple column at once
                ajax: {
                    "url": "/NJProjekatFED/supplier/all_suppliers_json",
                    "type": "GET",
                    "datatype": "json",
                    dataSrc: ''
                },
                "columns": [
                    {"data": "pib", "name": "PIB", "autoWidth": true},
                    {"data": "sediste", "name": "sediste", "autoWidth": true},
                    {"data": "naziv", "name": "naziv", "autoWidth": true},
                    {
                        "render": function (data, type, full, meta) {
                            return '<a class="fa fa-info-circle btn btn-info" href="/NJProjekatFED/supplier/find_supplier/' + full.pib + '"></a>';
                        }
                    },
                    {
                        "render": function (data, type, full, meta) {
                            return '<a class="fa fa-cogs btn btn-info" href="/NJProjekatFED/supplier/update_supplier/' + full.pib + '"></a>';
                        }
                    },
                    {
                        "render": function (data, type, full, meta) {
                            return '<a class="fa fa-remove btn btn-info" href="/NJProjekatFED/supplier/remove_supplier/' + full.pib + '"></a>';
                        }
                    }
                ]
            });
        });
    </script>               
</body>

