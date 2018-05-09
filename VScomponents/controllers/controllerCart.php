<?php
    error_reporting(0);
    session_start();
    $messageError = '';
    if(isset($_GET["option"]) && $_GET["option"] != '' && is_numeric($_GET["option"]))
    {
        $option = $_GET["option"];
        $json = [];
        
        switch($option)
        {
            case 1:
                
                if (isset($_GET["value"]) && $_GET["value"] != '' && is_numeric($_GET["value"]))
                {
                    $productID = $_GET["value"];

                    require("model/model.php");
                    $stmt = $DB->getProductData($productID);
                    /* vincular variables a la sentencia preparada */
                    $stmt->bind_result($name, $specs, $characteristics, $stock, $price, $product_image, $brand_image, $brand);

                    //Obtención de datos
                    while($stmt->fetch())
                    {
                        $name;
                        $price;
                    }

                    $stmt->close();
                    $DB->closeConnectionDB();

                    if(count($_SESSION["detalle"]) > 0)
                    {
                        $ultimo = end($_SESSION["detalle"]);
                        $count  = $ultimo['id'] + 1;
                    }
                    else
                    {
                        $count = count($_SESSION["detalle"]) + 1;
                    }

                    $_SESSION["detalle"][$count] = ["id" => $count, "productID" => $productID, "name" => $name, "price" => $price, "quantity" => 1];
                }
                break;

            case 2:
                
                if (isset($_GET["value"]) && $_GET["value"]!='' && is_numeric($_GET["value"]))
                {
                    unset($_SESSION["detalle"][$_GET["value"]]);
                    
                }
                break;

            case 3:

                unset($_SESSION["detalle"]);
                break;

            case 4:

                if (isset($_GET["quantity"]) && isset($_GET["idProductCart"]) && $_GET["quantity"]!=''
                && $_GET["idProductCart"]!='' && is_numeric($_GET["quantity"]) && $_GET["quantity"] >= 1
                && $_GET["quantity"] <= 10 && is_numeric($_GET["idProductCart"]))
                {
                    $_SESSION["detalle"][$_GET["idProductCart"]]["quantity"] = $_GET["quantity"];
                }
                else
                {
                    $messageError = "Quantity must be between 1 and 10";
                }
                break;
        }
    }
    else
    {
        $option = 0;
    }


    $sessionDetalle = $_SESSION["detalle"];

    $url = explode("/", $_SERVER["REQUEST_URI"]);


    if($option == 4)
    {
        require("views/viewCart.php");
    }
    else
    {
        require("views/viewAllCart.php");
    }

    /*if($option == 4)
    {
        if($url[2] == "controllers")
        {
            require("../views/viewCart.php");
        }
        else
        {
            require("views/viewCart.php");
        }
    }
    else
    {
        if($url[2] == "controllers")
        {
            require("../views/viewAllCart.php");
        }
        else
        {
            require("views/viewAllCart.php");
        }
    }*/
?>
