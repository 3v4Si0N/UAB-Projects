<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Search Engine</title>
        <link rel="stylesheet" href="/VScomponents/css/bootstrap.min.css">
        <link rel="stylesheet" href="/VScomponents/css/styles.css">

        <script src="/VScomponents/js/jquery.js"></script>
        <script src="/VScomponents/js/bootstrap.min.js"></script>
        <script src="/VScomponents/js/functions.js"></script>
        
        <!-- Alertity -->
        <link rel="stylesheet" href="/VScomponents/js/alertify/themes/alertify.core.css">
        <link rel="stylesheet" href="/VScomponents/js/alertify/themes/alertify.bootstrap.css" id="toggleCSS">
        <script src="/VScomponents/js/alertify/lib/alertify.min.js"></script>
    </head>

    <body>

        <header class="container-fluid">
            <div class="row">
                <a href="/VScomponents/" name="url" onload="getURL();" class="col-xs-3 col-sm-3 col-md-2 col-lg-2"><img src="/VScomponents/images/logo.png" alt="" class="img-responsive img-rounded"></a>
                <img src="/VScomponents/images/logo_entregas_home.png" alt="" class="img-responsive img-rounded col-xs-offset-1 col-xs-3 col-sm-offset-1 col-sm-3 col-md-offset-1 col-md-2 col-lg-offset-1 col-lg-2 img-entregas">
                <img src="/VScomponents/images/logo_envios_home.png" alt="" class="img-responsive img-rounded col-xs-offset-2 col-xs-3 col-sm-offset-2 col-sm-3  col-md-offset-2 col-md-2 col-lg-offset-2 col-lg-2 img-envios">
            </div>

            <div class="row">
                <nav class="navbar-default navPrincipal">
                    <!-- Toogle navitation sirve para tener un boton con menu desplegable al encontrarnos en una pantalla de movil-->
                    <div class="navbar-header">
                      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <!-- iconos de barra dentro del boton -->
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                      </button>
                      <!-- este enlace no se mete dentro del toogle navigation al entrar en una pantalla movil -->
                      <a href="/VScomponents/" name="url" onload="getURL();" class="navbar-brand">VS-components</a>
                    </div>

                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                      <ul class="nav navbar-nav">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown">Components</a>
                            <ul class="dropdown-menu">
                                <li><a href="/components/placas-base">Placas Base</a></li>

                                <li><a href="/components/procesadores">Procesadores</a></li>

                                <li><a href="/components/discos-duros">Discos Duros</a></li>

                                <li><a href="/components/tarjetas-graficas">Tarjetas Gráficas</a></li>

                                <li><a href="/components/memorias-ram">Memoria RAM</a></li>

                                <li><a href="/components/grabadoras-dvd-blu-ray">Grabadoras DVD/Blu-Ray</a></li>

                                <li><a href="/components/disqueteras">Disqueteras</a></li>

                                <li><a href="/components/tarjetas-sonido">Tarjetas de Sonido</a></li>

                                <li><a href="/components/torres">Torres/Cajas/Carcasas</a></li>

                                <li><a href="/components/ventiladores">Ventiladores</a></li>

                                <li><a href="/components/fuentes-alimentacion">Fuentes Alimentación</a></li>
                            </ul>
                        </li>

                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown">Accesories</a>
                            <ul class="dropdown-menu">
                                <li><a href="/accesories/gamepads">Gamepads</a></li>

                                <li><a href="/accesories/joysticks">Joysticks</a></li>

                                <li><a href="/accesories/capturadoras">Capturadoras</a></li>

                                <li><a href="/accesories/altavoces">Altavoces</a></li>

                                <li><a href="/accesories/microfonos">Micrófonos</a></li>
                            </ul>
                        </li>

                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown">Laptops</a>
                            <ul class="dropdown-menu">
                                <li><a href="/laptops/portatiles">Portátiles</a></li>

                                <li><a href="/laptos/ultrabooks">Ultrabooks</a></li>

                                <li><a href="/laptops/sobremesa">Sobremesa</a></li>

                                <li><a href="/laptops/todo-en-1">Ordenadores todo en uno</a></li>
                            </ul>
                        </li>

                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown">Peripherics</a>
                            <ul class="dropdown-menu">
                                <li><a href="peripherics/monitores-pc">Monitores</a></li>

                                <li><a href="peripherics/impresoras">Impresoras</a></li>

                                <li><a href="peripherics/impresoras-3d">Impresoras 3D</a></li>

                                <li><a href="peripherics/altavoces">Altavoces</a></li>

                                <li><a href="peripherics/teclados">Teclados</a></li>

                                <li><a href="peripherics/ratones">Ratones</a></li>

                                <li><a href="peripherics/auriculares">Auriculares</a></li>

                                <li><a href="peripherics/webcam">Webcams</a></li>

                                <li><a href="peripherics/gamepads-joysticks">Gamespads/Joysticks</a></li>

                                <li><a href="peripherics/microfonos">Micrófonos</a></li>

                                <li><a href="peripherics/alfombrillas">Alfombrillas</a></li>

                                <li><a href="peripherics/gadgets">Gadgets</a></li>
                            </ul>
                        </li>

                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown">Smartphones</a>
                            <ul class="dropdown-menu">
                                <li><a href="/smartphone-moviles/acer">Acer</a></li>

                                <li><a href="/smartphone-moviles/apple">Apple</a></li>
                                <li><a href="/smartphone-moviles/asus">Asus</a></li>
                                <li><a href="/smartphone-moviles/bq">Bq</a></li>
                                <li><a href="/smartphone-moviles/honor">Honor</a></li>
                                <li><a href="/smartphone-moviles/htc">HTC</a></li>
                                <li><a href="/smartphone-moviles/huawei">Huawei</a></li>
                                <li><a href="/smartphone-moviles/lg">LG</a></li>
                                <li><a href="/smartphone-moviles/meizu">Meizu</a></li>
                                <li><a href="/smartphone-moviles/motorola">Motorola</a></li>
                                <li><a href="/smartphone-moviles/nokia">Nokia</a></li>
                                <li><a href="/smartphone-moviles/samsung">Samsung</a></li>
                                <li><a href="/smartphone-moviles/sony">Sony</a>
                                <li><a href="/smartphone-moviles/xiaomi">Xiaomi</a></li>
                                <li><a href="/smartphone-moviles/zte">ZTE</a></li>
                            </ul>
                        </li>

<?php
                    if(!isset($_COOKIE["username"]) && !isset($_COOKIE["password"]))
                    {
?>
                        <li>
                            <button type="submit" class="btn btnLogin" onclick="window.location.href='/VScomponents/login'"><span class="glyphicon glyphicon-user"></span>&nbsp;Log in</button>
                        </li>
<?php
                    }
?>
                        <li>
                            <form method="GET" action="/VScomponents/search" class="navbar-form navbar-left">
                                <div class="form-group">
                                  <input type="text" name="q" class="form-control search" value="<?php echo $search; ?>">
                                </div>
                            </form>
                        </li>

                        <li id="viewCart" class="li-dropdown">
                            <?php require("controllers/controllerCart.php"); ?>
                        </li>
<?php
                    if(isset($_COOKIE["username"]) && isset($_COOKIE["password"]))
                    {
?>
                        <li class="li-dropdown">
                            <a href="#"><span class="glyphicon glyphicon-user"></span>&nbsp;<?php echo $_COOKIE["username"]; ?></a>
                            <ul class="ul-dropdown-content">
                                <li><a href="/VScomponents/logout"><span class="glyphicon glyphicon-log-out"></span>&nbsp;Log out</a></li>
                                <li><a href="/VScomponents/users/panel/data"><span class="glyphicon glyphicon-cog"></span>&nbsp;User panel</a></li>
                            </ul>
                        </li>
<?php
                    }
?>
                      </ul>
                    </div><!-- /.navbar-collapse -->
                </nav>
            </div>
        </header>

        <section class="container-fluid">
            <div class="row">
                <div class="col-lg-12 search-section">
                    <div class="row">
                        <div class="col-lg-offset-3 col-lg-6">
                            <table class="table table-condensed">
<?php
                                if(isset($result))
                                {
?>
                                    <tr>
                                        <td class="alert alert-danger text-center"><?php echo $result; ?></td>
                                    </tr>
<?php
                                }
                                else
                                {
                                    foreach($products as $row)
                                    {
?>
                                        <tr>
                                            <td>
                                                <a href="#"><img src="<?php echo "/VScomponents/".$row["product_image"];?>" alt="<?php echo $row["id"]; ?>" class="img-responsive" onclick="loadProduct(this.alt)" width="120" height="120"></a>
                                            </td>
                                            <td class="text-center">
                                                <h4><?php echo $row["name"];?></h4>
                                            </td>

                                            <td class="text-center">
                                                <p><?php echo $row["price"]." €";?></p>
                                            </td>
                                            <td>
                                                <center>
                                                    <button class="btn btn-success btn-lg btn-agregar-producto" value="<?php echo $row["id"]; ?>" onclick="productToCart(1,this.value)">Add to cart</button>
                                                </center>
                                            </td>
                                        </tr>
<?php
                                    }
                                }
?>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
