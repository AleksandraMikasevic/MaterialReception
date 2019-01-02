<%-- 
    Document   : all_materials_graph
    Created on : Nov 23, 2018, 12:25:52 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="box-body" style="height: 85%">

    <div class="chart-container" style="height: 100%">
        <div class="prviChart" style="width: 50%; float:left">
            <br />
            <div class="list-group" style="width:90%; margin:0 auto;">
                <div class="list-group-item">
                    Kupovina materijala
                </div>
            </div>
            <canvas id="chart" style="width:50%; height:300px"></canvas>
        </div>
        <div class="drugiChart" style="width: 50%; float: right">
            <br />
            <div class="list-group" style="width:90%; margin:0 auto;">
                <div class="list-group-item">
                    Broj narudzbina po dobavljacima
                </div>
            </div>           
            <canvas id="chart1" style="width:50%; height:300px"></canvas>
        </div>
    </div>
</div>
<script type="text/javascript">
    $.ajax({
        "url": "/NJProjekatFED/material/all_materials_json_graph",
        "type": "GET",
        "datatype": "json"
    }).done(
            function (data1) {
                var chartName = "chart";
                var ctx = document.getElementById(chartName).getContext('2d');
                var data = {
                    labels: data1['materijali'],
                    datasets: [{
                            label: "Kupljena kolicina",
                            backgroundColor: [
                                'rgba(54, 162, 235, 0.2)',
                                'rgba(54, 162, 235, 0.2)',
                                'rgba(54, 162, 235, 0.2)',
                                'rgba(54, 162, 235, 0.2)',
                                'rgba(54, 162, 235, 0.2)'
                            ],
                            borderColor: [
                                'rgba(54, 162, 235, 1)',
                                'rgba(54, 162, 235, 1)',
                                'rgba(54, 162, 235, 1)',
                                'rgba(54, 162, 235, 1)',
                                'rgba(54, 162, 235, 1)'
                            ],
                            borderWidth: 1,
                            data: data1['kolicine']
                        }]
                };
                var options = {
                    maintainAspectRatio: false,
                    scales: {
                        yAxes: [{
                                ticks: {
                                    min: 0,
                                    beginAtZero: true
                                },
                                gridLines: {
                                    display: true,
                                    color: "rgba(255,99,164,0.2)"
                                }
                            }],
                        xAxes: [{
                                ticks: {
                                    min: 0,
                                    beginAtZero: true
                                },
                                gridLines: {
                                    display: false
                                }
                            }]
                    }
                };
                var myChart = new Chart(ctx, {
                    options: options,
                    data: data,
                    type: 'bar'
                });
            }
    );
</script> 

<script type="text/javascript">
    $.ajax({
        "url": "/NJProjekatFED/supplier/all_suppliers_json_graph",
        "type": "GET",
        "datatype": "json"
    }).done(
            function (data1) {
                var chartName = "chart1";
                var ctx = document.getElementById(chartName).getContext('2d');
                var data = {
                    labels: data1['dobavljaci'],
                    datasets: [{
                            label: "Broj narudzbina",
                            backgroundColor: [
                                'rgba(54, 162, 235, 0.2)',
                                "rgba(255,99,132,0.2)",
                                "rgba(255,221,50,0.2)",
                                "rgba(0,0,0,0.2)",
                                "rgba(193,46,12,0.2)"
                            ],
                            borderColor: [
                                'rgba(54, 162, 235, 1)',
                                "rgba(255,99,132,1)",
                                "rgba(255,221,50,1)",
                                "#000",
                                "rgba(193,46,12,1)"
                            ],
                            borderWidth: 1,
                            data: data1['narudzbine']
                        }]
                };
                var options = {
                    maintainAspectRatio: false
                };
                var myChart = new Chart(ctx, {
                    options: options,
                    data: data,
                    type: 'pie'
                });
            }
    );
</script> 