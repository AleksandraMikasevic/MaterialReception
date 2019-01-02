<nav class="navbar navbar-expand-lg navbar-light bg-light" style="background-color: #e3f2fd;">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
        <a class="navbar-brand" href="/NJProjekatFED/">Novkabel</a>
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <li class="nav-item">
                <a class="nav-link" href="/NJProjekatFED/goods_received_note/all_goods_received_notes">Prijemnica</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/NJProjekatFED/material/all_materials">Materijal</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/NJProjekatFED/supplier/all_suppliers">Dobavljac</a>
            </li>    
             <li class="nav-item">
                <a class="nav-link" href="/NJProjekatFED/weight_certificate/all_weight_certificate">Vagarske potvrde</a>
            </li> 
            <li class="nav-item">
                <a class="nav-link" href="/NJProjekatFED/material/all_materials_graph">Graficki prikaz</a>
            </li>        
            <li class="nav-item">
                <a class="nav-link" href="/NJProjekatFED/find_employee"><%=session.getAttribute("zaposleni")%></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/NJProjekatFED/logout">Odjava</a>
            </li>
        </ul>
    </div>
</nav>