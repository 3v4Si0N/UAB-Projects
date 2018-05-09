<?php
    //error_reporting(0);
    $message = '';
    session_start();
    if(isset($_COOKIE["username"]) && $_COOKIE["username"] != ''
    && isset($_COOKIE["password"]) && $_COOKIE["password"] != '')
    {
        if(count($_SESSION['detalle']) > 0)
        {
            $totalPrice = 0;
            require("model/model.php");

            foreach($_SESSION['detalle'] as $k => $detalle)
            {
                $products[$detalle['id']] = ["productID" => $detalle["productID"], "quantity" => $detalle["quantity"], "name" => $detalle["name"]];
                $totalPrice += $detalle['price'] * $detalle['quantity'];
                $DB->updateProductStock($detalle["productID"], $detalle["quantity"]);
            }

            //$DB->insertUserSale();
            $usernameID = $DB->getUsernameID($_COOKIE["username"]);
            $date       = date("d-m-Y H:i:s");
            $DB->insertSale($totalPrice, $usernameID, $date);
            $stmt = $DB->getReferenceID($date);

            $stmt->bind_result($referenceID);
            while($stmt->fetch())
            {
                $referenceID;
            }

            foreach($products as $product)
            {
                $DB->insertSalesHasProducts($product["productID"], $referenceID, $product["quantity"]);
            }
            $DB->closeConnectionDB();
            unset($_SESSION["detalle"]);
            $message = 'Compra realizada correctamente';
        }
        else
        {
            $message = 'There are no products in your shopping cart.';
        }
        
        require("views/viewPurchase.php");
    }
    else
    {
        header("Refresh:0; url='http://localhost/VScomponents/login'");
    }

?>
