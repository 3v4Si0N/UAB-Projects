<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User Panel</title>
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
                  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse" aria-expanded="false">
                  <!-- aria-expanded indica el estado de los elementos collapsed -->
                    <!-- iconos de barra dentro del boton -->
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                  </button>
                  <!-- este enlace no se mete dentro del toogle navigation al entrar en una pantalla movil -->
                  <a href="/VScomponents/" name="url" onload="getURL();" class="navbar-brand hidden-sm hidden-md">VS-components</a>
                </div>
               <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="navbar-collapse">
                  <ul class="nav navbar-nav">

                    <li>
                        <button type="submit" class="btn btnLogin" onclick="window.location.href='/VScomponents/logout'"><span class="glyphicon glyphicon-log-out"></span>&nbsp;Log out</button>
                    </li>

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
                  </ul>
                </div><!-- /.navbar-collapse -->
            </nav>
        </div>
    </header>

	<section class="container-fluid">
		<div class="row">
		    <div class="col-sm-12 container-user">
		        <form method="POST" id="formUserData" class="form-horizontal col-xs-offset-1 col-xs-10 col-sm-offset-1 col-sm-5 col-md-offset-1 col-md-5 col-lg-offset-1 col-lg-5 thumbnail">
                    <h4 class="col-xs-offset-2 col-xs-8 col-sm-offset-2 col-sm-8 col-md-offset-2 col-md-8 col-lg-offset-2 col-lg-8 text-center h4-register">User Data</h4>
                    <div class="form-group">
                        <div class="col-xs-offset-2 col-xs-8 col-sm-offset-2 col-sm-8 col-md-offset-2 col-md-8 col-lg-offset-2 col-lg-8">
                            <input type="text" class="form-control" name="name" id="name" value="<?php echo $name; ?>" required="true">
                        </div>
                    </div>


                   <div id="confirmUsername">
                       <div class="form-group">
                            <div class="col-xs-offset-2 col-xs-8 col-sm-offset-2 col-sm-8 col-md-offset-2 col-md-8 col-lg-offset-2 col-lg-8">
                                <input type="text" class="form-control" name="username" id="username" placeholder="Username" onblur="validateInputField('username', this.value, 'confirmUsername')" value="<?php echo $username; ?>" required="true">
                            </div>
                        </div>
                   </div>

                    <div class="form-group">
                        <div class="col-xs-offset-2 col-xs-8 col-sm-offset-2 col-sm-8 col-md-offset-2 col-md-8 col-lg-offset-2 col-lg-8">
                            <input type="password" class="form-control" name="password" id="password" placeholder="password" minlength="8" required="true">
                        </div>
                    </div>


                    <div id="confirmEmail">
                        <div class="form-group">
                            <div class="col-xs-offset-2 col-xs-8 col-sm-offset-2 col-sm-8 col-md-offset-2 col-md-8 col-lg-offset-2 col-lg-8">
                                <input type="email" class="form-control" name="email" id="email" placeholder="Email" onblur="validateInputField('email', this.value, 'confirmEmail')" value="<?php echo $email; ?>" required="true">
                            </div>
                        </div>
                   </div>

                    <div class="form-group">
                        <div class="col-xs-offset-2 col-xs-8 col-sm-offset-2 col-sm-8 col-md-offset-2 col-md-8 col-lg-offset-2 col-lg-8">
                            <input type="tel" class="form-control" name="phone" id="phone" value="<?php echo $phone; ?>" maxlength="9" minlength="9" required="true">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-2 col-xs-8 col-sm-offset-2 col-sm-8 col-md-offset-2 col-md-8 col-lg-offset-2 col-lg-8">
                            <input type="text" class="form-control" name="street" id="street" value="<?php echo $street; ?>" required="true">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-2 col-xs-8 col-sm-offset-2 col-sm-8 col-md-offset-2 col-md-8 col-lg-offset-2 col-lg-8">
                            <input type="text" class="form-control" name="city" id="city" value="<?php echo $city; ?>" required="true">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-2 col-xs-8 col-sm-offset-2 col-sm-8 col-md-offset-2 col-md-8 col-lg-offset-2 col-lg-8">
                            <input type="text" class="form-control" name="postal" id="postal" value="<?php echo $postal; ?>" maxlength="5" minlength="5" required="true">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-2 col-xs-8 col-sm-offset-2 col-sm-8 col-md-offset-2 col-md-8 col-lg-offset-2 col-lg-8">
                            <input type="text" class="form-control" name="card" id="card" value="<?php echo $card; ?>" maxlength="16" minlength="16" required="true">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-2 col-xs-10 col-sm-offset-2 col-sm-10">
                            <input type="button" class="btn btn-success" onclick="validateUpdateData()" value="Update user data">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-1 col-xs-10" id="confirmation">

                        </div>
                    </div>
                </form>
								<!-- USER PURCHASES -->
                <aside class="col-xs-offset-1 col-xs-10 col-sm-offset-1 col-sm-4 col-md-offset-1 col-md-4 col-lg-offset-1 col-lg-4 purchases">
                    <h4 class="col-xs-offset-2 col-xs-8 col-sm-offset-2 col-sm-8 col-md-offset-2 col-md-8 col-lg-offset-2 col-lg-8 text-center h4-register">Your Purchases</h4>
<?php
                       if($salesUserID->num_rows > 0)
                        {
?>
                            <div id="accordion" class="col-xs-offset-2 col-xs-8 col-sm-offset-2 col-sm-8 col-md-offset-2 col-md-8 col-lg-offset-2 col-lg-8 acordeon">
<?php
                            foreach($salesUserArray as $rowSales)
                            {
?>
                                <h4><?php echo "Order ID: ".$rowSales['reference_id']; echo " Date: ".$rowSales['date']; echo " Price: ".$rowSales['price']; ?></h4>
                                <div>
                                    <ul>
<?php
                                foreach($userPurchasesArray[$rowSales['reference_id']] as $row)
                                {
?>
                                        <li><?php echo $row['Product_name']; ?></li>
                                        <ul>
                                            <li><?php echo "Price:".$row['Product_price']; ?>€</li>
                                            <li><?php echo "Quantity: ".$row['Quantity']; ?></li>
                                        </ul>
<?php
                                }
?>
                                    </ul>
                                </div>
<?php
                            }
?>
                        </div>
<?php
                        }
                        else
                        {
?>
                           <div class="col-xs-offset-2 col-xs-8 col-sm-offset-2 col-sm-8 col-md-offset-2 col-md-8 col-lg-offset-2 col-lg-8">
                               <h5 class="text-center alert alert-warning"><?php echo $message; ?></h5>
                           </div>
<?php
                        }
?>
                </aside>

            </div>
        </div>
	</section>
</body>
</html>
