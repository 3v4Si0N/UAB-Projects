<?php

    if($_POST)
    {
        // set parameters
        //$categoryID = $_POST["category"];
        //$sucategoryID = $_POST["subcategory"];
        $productID = $_POST["products"];

        /*$categoryID = filter_var($categoryID, FILTER_VALIDATE_INT);
        $subcategoryID = filter_var($subcategoryID, FILTER_VALIDATE_INT);
        $name = filter_var($category, FILTER_VALIDATE_STRING);
        $specs = filter_var($specs, FILTER_VALIDATE_STRING);
        $characs = filter_var($characs, FILTER_VALIDATE_STRING);
        $stock = filter_var($stock, FILTER_VALIDATE_INT);
        $price = filter_var($price, FILTER_VALIDATE_INT);*/

        if($productID)
        {
            require("model/model.php");

            $DB->deleteProduct($productID);


            $DB->closeConnectionDB();
            $messageToUser = 'The process has been completed <strong>successfully</strong>';
            require("views/viewConfirmation.php");
        }
        else
        {
            require("views/viewWrongField.html");
        }
    }
    else
    {
        require("views/viewForbidden.html");
    }
?>
