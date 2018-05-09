<?php
  //error_reporting(0); // Desactivar notificaciones de error (en este caso un notice)
	$product      = $_GET['product'];
  $messageError = '';
	if(is_numeric($product)) // Anti SQLi
	{
		require("model/model.php");
		$result = $DB->sqlCategoryProducts($product);
		$DB->closeConnectionDB();
		$categoryProducts = [];

		if ($result->num_rows > 0)
		{
			$num = 0;
			while($row_categoryProducts = $result->fetch_assoc())
		  	{
		  		$categoryProducts[] = $row_categoryProducts;
		  	}
		}
		else
		{
			$messageError = '0 results';
		}

		require("views/viewCategoryProducts.php");
	}

?>
