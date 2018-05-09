<?php
    session_start();
    $messageError = '';
    if(isset($_SESSION["adminUser"]) && isset($_SESSION["adminPassword"]))
    {
        require("model/model.php");
        $result_category = $DB->sqlCategory();

        //Cálculo de las categorias de productos de la barra de navegación
        if($result_category->num_rows > 0)
        {
            $categories = [];
            // output data of each row
            while($row_c = $result_category->fetch_assoc())
            {
                $categories[] = $row_c;
            }
        }
        else
        {
            $messageError = '0 results';
        }

        require("views/admin/viewDeleteTable.php");
    }
    else
    {
        require("views/viewForbidden.html");
    }

?>
