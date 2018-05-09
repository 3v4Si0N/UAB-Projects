<!DOCTYPE html>
<html lang="en">
<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Admin Panel</title>
    <link rel="stylesheet" href="/VScomponents/css/bootstrap.min.css">
    <link rel="stylesheet" href="/VScomponents/css/styles.css">

    <script src="/VScomponents/js/jquery.js"></script>
    <script src="/VScomponents/js/bootstrap.min.js"></script>
    <script src="/VScomponents/js/functions.js"></script>
</head>
<body>
    <header class="container-fluid">
        <div class="row">
            <nav class="navbar-default navPrincipal">
                <!-- Toogle navitation sirve para tener un boton con menu desplegable al encontrarnos en una pantalla de movil-->
                <div class="navbar-header">
                  <button type="button" class="navbar-toggle collapsed">
                  <!-- aria-expanded indica el estado de los elementos collapsed -->
                    <!-- iconos de barra dentro del boton -->
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                  </button>
                  <!-- este enlace no se mete dentro del toogle navigation al entrar en una pantalla movil -->
                  <a href="http://localhost/VScomponents/" class="navbar-brand hidden-sm hidden-md">VS-components</a>
                </div>
               <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="navbar-collapse">
                  <ul class="nav navbar-nav navbar-right">

                    <li class="form-group">
                        <button type="submit" class="btn btnLogin" onclick="window.location.href='/VScomponents/logout'"><span class="glyphicon glyphicon-log-out"></span>&nbsp;Log out</button>
                    </li>

                    <li class="form-group">
                        <button type="submit" class="btn btnLogin" onclick="showTable(1);"><span class="glyphicon glyphicon-new-window"></span>&nbsp;Create</button>
                    </li>

                    <li class="form-group">
                        <button type="submit" class="btn btnLogin" onclick="showTable(2);"><span class="glyphicon glyphicon-edit"></span>&nbsp;Edit</button>
                    </li>

                    <li class="form-group">
                        <button type="submit" class="btn btnLogin" onclick="showTable(3);"><span class="glyphicon glyphicon-floppy-remove"></span>&nbsp;Delete</button>
                    </li>

                  </ul>
                </div><!-- /.navbar-collapse -->
            </nav>
        </div>
    </header>

    <section class="container-fluid">
        <div class="row">
           <div id="table">
           </div>
        </div>
    </section>
</body>
</html>
