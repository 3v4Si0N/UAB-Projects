<?php
  error_reporting(0); // Desactivar notificaciones de error (en este caso un notice)
  $messageError = '';
	$subID = $_GET['subcategory'];

	if(is_numeric($subID)) // Anti SQLi
	{
		require("model/model.php");
		$result = $DB->sqlallProducts($subID);
		$DB->closeConnectionDB();
		$allProducts = [];

		if ($result->num_rows > 0)
		{
			$num = 0;
			while($row_allProducts = $result->fetch_assoc())
		  	{
		  		$allProducts[] = $row_allProducts;
		  	}
		}
		else
		{
			$messageError = '0 results';
		}

		require("views/admin/viewEditTable.php");
	}
    else
    {
        require("views/viewForbidden.html");
    }

?>
