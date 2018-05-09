<?php
    session_start();
    error_reporting(0);
    if(isset($_GET["productID"]) && $_GET["productID"] != "" && is_numeric($_GET["productID"]))
    {
        require("model/model.php");
        $stmt = $DB->getProductData($_GET["productID"]);

        /* vincular variables a la sentencia preparada */
	    $stmt->bind_result($name, $specs, $characteristics, $stock, $price, $product_image, $brand_image, $brand);

	    //Obtención de datos
	    while($stmt->fetch())
	    {
            $name;
            $specs;
            $characteristics;
            $stock;
            $price;
            $product_image;
            $brand_image;
            $brand;
	    }

        $productID = $_GET["productID"];
	    $stmt->close();
	    $DB->closeConnectionDB();

        $sessionDetalle = $_SESSION["detalle"];
        $cookieUsername = $_COOKIE["username"];
        $cookiePassword = $_COOKIE["password"];

        require("views/viewProduct.php");
    }
    else
    {
        require("views/viewForbidden.html");
    }
?>
