<?php

    if($_POST)
    {
        // set parameters
        //$categoryID = $_POST["category"];
        //$sucategoryID = $_POST["subcategory"];
        $productID = $_POST["products"];
        $specs     = $_POST["specs"];
        $characs   = $_POST["characs"];
        $stock     = $_POST["stock"];
        $price     = $_POST["price"];

        /*$categoryID = filter_var($categoryID, FILTER_VALIDATE_INT);
        $subcategoryID = filter_var($subcategoryID, FILTER_VALIDATE_INT);
        $name = filter_var($category, FILTER_VALIDATE_STRING);
        $specs = filter_var($specs, FILTER_VALIDATE_STRING);
        $characs = filter_var($characs, FILTER_VALIDATE_STRING);
        $stock = filter_var($stock, FILTER_VALIDATE_INT);
        $price = filter_var($price, FILTER_VALIDATE_INT);*/

        if($stock && $price)
        {
            require("model/model.php");

            $DB->updateProductData($productID, $specs, $characs, $stock, $price);

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
