<?php

    //error_reporting(0);
    $productID = $_GET['product'];

    if(is_numeric($productID))
	{
		require("model/model.php");
		$stmt = $DB->getProductData($productID);

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
            $brand;
	    }

	    $stmt->close();
	    $DB->closeConnectionDB();

	    require("views/admin/viewProductData.php");
	}
	else
	{
		require("views/viewForbidden.html");
	}

?>
