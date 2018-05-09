<?php error_reporting(0); ?>
<a href="#"><span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;My cart <span class="product-count"><?php echo count($_SESSION["detalle"]); ?></span></a>
<div class="ul-dropdown-content">
	<?php require("viewCart.php"); ?>
</div>