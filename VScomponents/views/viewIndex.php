<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>VS-components</title>
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

        <?php echo $messageError; ?>
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
                        <!-- aria-expanded indica el estado de los elementos collapsed -->
                        <!-- iconos de barra dentro del boton -->
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        </button>
                        <!-- este enlace no se mete dentro del toogle navigation al entrar en una pantalla movil -->
                        <a href="/VScomponents/" name="url" onload="getURL();" class="navbar-brand hidden-sm hidden-md">VScomponents</a>
                    </div>
                   <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                       <ul class="nav navbar-nav">
<?php
                            foreach($categories as $row_category)
                            {
?>
                                <li class="li-dropdown">
                                    <a href="#"><?php echo $row_category["name"]; ?></a>
                                    <ul class="ul-dropdown-content">
<?php
                                        foreach($subcategorias as $row_subcategory)
                                        {
                                            if($row_category["id"] == $row_subcategory["category_id"])
                                            {
?>
                                        <li><a href="#" onclick="loadProducts(<?php echo $row_subcategory["id"]; ?>);"><?php echo $row_subcategory["name"]; ?></a></li>
<?php
                                            }
                                        }
?>
                                    </ul>
                                </li>
<?php
                            }

                            // $cookieUsername = $_COOKIE["username"]
                            // $cookiePassword = $_COOKIE["password"]

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

        <section id="sectionProduct" class="container-fluid container-section">
            <div class="row">
                <h3 class="col-xs-8 col-sm-8 col-md-6 col-lg-4 welcome">Welcome to VS-components</h3>
            </div>

            <div>
                <?php
                    if($products != 0)
                    {
                        foreach($products as $row_product)
                        {
                            if($num == 0)
                            {
                ?>
                                <div class="row">
                <?php
                            }
                            if($num < 4)
                            {
                ?>
                                <article class="col-xs-12 col-sm-6 col-md-6 col-lg-3">
                                    <div class="thumbnail">
                                        <a href="#"><img src="/VScomponents/<?php echo $row_product['product_image'];?>" alt="<?php echo $row_product["id"]; ?>" class="img-responsive" onclick="loadProduct(this.alt)" width="220" height="220"></a>
                                        <div class="caption">
                                            <h4><?php echo $row_product["name"];?></h4>
                                            <p><?php echo $row_product["price"]." €";?></p>
                                            <button class="btn btn-success btn-sm btn-agregar-producto" value="<?php echo $row_product["id"]; ?>" onclick="productToCart(1,this.value)">Add</button>
                                        </div>
                                    </div>
                                </article>
                <?php
                            }
                            $num++;
                            if($num == 4)
                            {
                                $num = 0;
                ?>
                                </div>
                <?php
                            }
                        }
                    }
                ?>

            </div>

            <nav class="row">
                <ul class="pager">
                   <!-- http://www.jose-aguilar.com/blog/paginacion-resultados-con-php/ -->
                   <!-- http://php.net/manual/en/pdostatement.bindparam.php -->
        <?php
                    if($pagina == 1)
                    {
        ?>
                        <li class="previous"><button type="button" class="btn btn-info btn-md" disabled="true">‹‹ Previous</button></li>
        <?php
                    }
                    else
                    {
        ?>
                        <li class="previous"><button type="button" class="btn btn-info btn-md" onclick="window.location.href='<?php echo "/VScomponents/index/read/".($pagina-1); ?>'">‹‹ Previous</button></li>
        <?php
                    }
                    if($pagina == $total_paginas)
                    {
        ?>
                        <li class="next"><button type="button" class="btn btn-info btn-md" disabled="true">Next ››</button></li>
        <?php
                    }
                    else
                    {
        ?>
                        <li class="next"><button type="button" class="btn btn-info btn-md" onclick="window.location.href='<?php echo "/VScomponents/index/read/".($pagina+1); ?>'">Next ››</button></li>
        <?php
                    }
        ?>
                </ul>
            </nav>
        </section>


        <footer class="container-fluid">
            <div class="row hidden-xs">
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
                    <h3 class="text-justify">CONTACTARR</h3>
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