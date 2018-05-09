<?php
  error_reporting(0);
  $messageError = '';
	$catgID = $_GET['catgid'];

    if(is_numeric($catgID))
    {
        require("model/model.php");
        $result_subcategory = $DB->sqlSubCategory();

        // Cálculo de las subcategorias para el select dinámico
        if($result_subcategory->num_rows > 0)
        {
            $subcategories = [];
            while($row_s = $result_subcategory->fetch_assoc())
            {
                if($row_s['category_id'] == $catgID)
                {
                    $subcategories[] = $row_s;
                }
            }
            $DB->closeConnectionDB();
            require("views/admin/viewCreateTable.php");
        }
        else
        {
            $messageError = '0 results';
        }
    }
    else
    {
        require("views/viewForbidden.html");
    }
?>
