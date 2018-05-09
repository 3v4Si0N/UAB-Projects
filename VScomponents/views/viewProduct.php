<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Producto</title>
    <link rel="stylesheet" href="/VScomponents/css/jquery-ui.css">
		<link rel="stylesheet" href="/VScomponents/css/bootstrap.min.css">
    <link rel="stylesheet" href="/VScomponents/css/styles.css">

    <script src="/VScomponents/js/jquery.js"></script>
    <script src="/VScomponents/js/jquery-ui.js"></script>
    <script src="/VScomponents/js/bootstrap.min.js"></script>
    <script src="/VScomponents/js/functions.js"></script>
    
    <!-- Alertity -->
    <link rel="stylesheet" href="/VScomponents/js/alertify/themes/alertify.core.css">
    <link rel="stylesheet" href="/VScomponents/js/alertify/themes/alertify.bootstrap.css" id="toggleCSS">
    <script src="/VScomponents/js/alertify/lib/alertify.min.js"></script>
        
    <script>
        $(function()
        {
            $("#accordion").accordion(
            {
                heightStyle: "content" // Se ajusta al contenido del contenedor
            });
        });
    </script>
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
                            <li><a href="#">Placas Base</a></li>

                            <li><a href="#">Procesadores</a></li>

                            <li><a href="#">Discos Duros</a></li>

                            <li><a href="#">Tarjetas Gráficas</a></li>

                            <li><a href="#">Memoria RAM</a></li>

                            <li><a href="#">Grabadoras DVD/Blu-Ray</a></li>

                            <li><a href="#">Disqueteras</a></li>

                            <li><a href="#">Tarjetas de Sonido</a></li>

                            <li><a href="#">Torres/Cajas/Carcasas</a></li>

                            <li><a href="#">Ventiladores</a></li>

                            <li><a href="#">Fuentes Alimentación</a></li>
                        </ul>
                    </li>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown">Accesories</a>
                        <ul class="dropdown-menu">
                            <li><a href="">Gamepads</a></li>

                            <li><a href="#">Joysticks</a></li>

                            <li><a href="#">Capturadoras</a></li>

                            <li><a href="#">Altavoces</a></li>

                            <li><a href="#">Micrófonos</a></li>
                        </ul>
                    </li>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown">Laptops</a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Portátiles</a></li>

                            <li><a href="#">Ultrabooks</a></li>

                            <li><a href="#">Sobremesa</a></li>

                            <li><a href="#">Ordenadores todo en uno</a></li>
                        </ul>
                    </li>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown">Peripherics</a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Monitores</a></li>

                            <li><a href="#">Impresoras</a></li>

                            <li><a href="#">Impresoras 3D</a></li>

                            <li><a href="#">Altavoces</a></li>

                            <li><a href="#">Teclados</a></li>

                            <li><a href="#">Ratones</a></li>

                            <li><a href="#">Auriculares</a></li>

                            <li><a href="#">Webcams</a></li>

                            <li><a href="#">Gamespads/Joysticks</a></li>

                            <li><a href="#">Micrófonos</a></li>

                            <li><a href="#">Alfombrillas</a></li>

                            <li><a href="#">Gadgets</a></li>
                        </ul>
                    </li>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown">Smartphones</a>
                        <ul class="dropdown-menu">
                            <li><a href="">Acer</a></li>

                            <li><a href="#">Apple</a></li>
                            <li><a href="#">Asus</a></li>
                            <li><a href="#">Bq</a></li>
                            <li><a href="#">Honor</a></li>
                            <li><a href="#">HTC</a></li>
                            <li><a href="#">Huawei</a></li>
                            <li><a href="#">LG</a></li>
                            <li><a href="#">Meizu</a></li>
                            <li><a href="#">Motorola</a></li>
                            <li><a href="#">Nokia</a></li>
                            <li><a href="#">Samsung</a></li>
                            <li><a href="#">Sony</a>
                            <li><a href="#">Xiaomi</a></li>
                            <li><a href="#">ZTE</a></li>  
                        </ul>
                    </li>
<?php
                    if(!isset($cookieUsername) && !isset($cookiePassword))
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
                              <input type="text" name="q" class="form-control search" placeholder="Search product">
                            </div>
                        </form>
                    </li>

                    <li id="viewCart" class="li-dropdown">
                        <?php require("controllers/controllerCart.php"); ?>
                    </li>

                    <?php
                    if(isset($cookieUsername) && isset($cookiePassword))
                    {
?>
                        <li class="li-dropdown">
                            <a href="#"><span class="glyphicon glyphicon-user"></span>&nbsp;<?php echo $cookieUsername; ?></a>
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

    <section class="container-fluid container-section">
    	<div class="row">
    		<img src="/VScomponents/<?php echo $product_image; ?>" alt="" class="col-xs-8 col-sm-8 col-md-offset-1 col-md-4 col-lg-offset-1 col-lg-3">
    		<article class="col-xs-12 col-sm-12 col-md-offset-1 col-md-10 col-lg-offset-0 col-lg-7 thumbnail">
                <div class="col-xs-4 caption">
                    <h4><?php echo $name; ?></h4>
                    <h3 class="precio"><b><?php echo $price; ?> €</b></h3>
                    <button type="button" class="btn btn-warning btn-lg" onclick="productToCart(1, this.value);" value="<?php echo $productID; ?>">Add to cart</button>
                </div>
                <img src="/VScomponents/<?php echo $brand_image; ?>" alt="" class="col-xs-4 img-responsive">
            </article>

            <div class="col-xs-12 col-sm-12 col-md-offset-1 col-md-10 col-lg-offset-0 col-lg-7 caption thumbnail">
                <ul class="caption product-lists">
                    <li><b>Brand ›› </b><?php echo $brand; ?></li>
                    <li><b>Availability ›› </b><?php echo $stock; ?> in stock</li>
                </ul>
            </div>
        </div>
    </section>

    <section class="container-fluid container-section">
        <div class="row">
            <div id="accordion" class="col-xs-12 col-sm-12 col-md-offset-1 col-md-10 col-lg-offset-1 col-lg-10 acordeon">
                <h4>Characteristics</h4>
                <div>
                    <ul class="product-lists">
                        <?php echo $characteristics; ?>
                    </ul>
                </div>

                <h4>Specifications</h4>
                <div>
                    <ul class="product-lists">
                        <?php echo $specs; ?>
                    </ul>
                </div>
            </div>
        </div>
        <br/><br/>
    </section>


    <footer class="container-fluid">
        <div class="row">
            <section class="col-xs-offset-1 col-xs-5 col-sm-offset-1 col-sm-5 col-md-offset-1 col-md-2 col-lg-offset-1 col-lg-2 navbar">
                <h3 class="text-justify">POR QUÉ COMPRAR</h3>
                <ul class="nav">
                    <li><a href="#">Formas de pago</a></li>
                    <li><a href="#">Gastos de envío</a></li>
                    <li><a href="#">Preguntas frecuentes</a></li>
                </ul>
            </section>

            <section class="col-xs-offset-1 col-xs-5 col-sm-offset-1 col-sm-5 col-md-offset-1 col-md-2 col-lg-offset-1 col-lg-2 navbar">
                <h3 class="text-justify">QUIÉNES SOMOS</h3>
                <ul class="nav">
                    <li><a href="#">Quiénes somos</a></li>
                    <li><a href="#">Condiciones de compra</a></li>
                    <li><a href="#">Garantías</a></li>
                    <li><a href="#">Fabricantes</a></li>
                </ul>
            </section>

            <section class="col-xs-offset-1 col-xs-5 col-sm-offset-1 col-sm-5 col-md-offset-1 col-md-2 col-lg-offset-1 col-lg-2 navbar">
                <h3 class="text-justify">CONTACTAR</h3>
                <ul class="nav">
                    <li><a href="#">Centro de soporte</a></li>
                    <li><a href="#">Aviso legal</a></li>
                    <li><a href="#">Privacidad</a></li>
                    <li><a href="#">Política de cookies</a></li>
                </ul>
            </section>

            <section class="col-xs-offset-1 col-xs-5 col-sm-offset-1 col-sm-5 col-md-offset-1 col-md-2 col-lg-offset-1 col-lg-2 navbar">
                <h3 class="text-justify">OTROS</h3>
                <ul class="nav">
                    <li><a href="#">Tarjetas regalo</a></li>
                    <li><a href="#">Black Friday</a></li>
                </ul>
            </section>
        </div>

        <div class="row container-section">
            <p class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center copyright"><span class="glyphicon glyphicon-copyright-mark"></span> Copyright VScomponents. Hosted by Héctor De Armas</p>
        </div>
    </footer>

</body>
</html>
