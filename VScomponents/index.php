<?php

    if(!isset($_GET["url"]))
    {
        require_once("controllers/controllerIndex.php");
    }
    else
    {
        $url = explode("/", $_GET["url"]);

        $resource = $url[0]; //index, user, admin, products, etc.
        
        switch($resource)
        {
            case "4dM1n1sTr4t0R":
                $action = $url[1];
                switch($action)
                {
                    case "show":
                        $adminTable = $url[2];
                        $_GET["table"] = $adminTable;
                        require("controllers/admin/controllerTables.php");
                        break;
                    case "panel":
                        require("controllers/admin/controllerAdminPanel.php");
                        break;
                    case "login":
                        require("controllers/admin/controllerAdminLogin.php");
                        break;
                    case "access":
                        require("controllers/admin/controllerAccessAdminPanel.php");
                        break;
                    case "read":
                        $action2 = $url[2];
                        switch($action2)
                        {
                            case "category":
                                $categoryID = $url[3];
                                $_GET["catgid"] = $categoryID;
                                require("controllers/admin/controllerGetSubcategory.php");
                                break;
                                
                            case "subcategory":
                                $subcategory = $url[3];
                                $_GET["subcategory"] = $subcategory;
                                require("controllers/admin/controllerGetProducts.php");
                                break;
                                
                            case "product":
                                $product = $url[3];
                                $_GET["product"] = $product;
                                require("controllers/admin/controllerDataProduct.php");
                                break;
                        }
                        
                        break;
                    case "create":
                        require("controllers/admin/controllerCreateProduct.php");
                        break;
                        
                    case "edit":
                        require("controllers/admin/controllerEditProduct.php");
                        break;
                        
                    case "delete":
                        require("controllers/admin/controllerDeleteProduct.php");
                        break;
                }
                break;

            case "index":
                $action = $url[1];
                switch($action)
                {
                    case "read":
                        $pageNumber = $url[2];
                        require("controllers/controllerIndex.php");

                        break;
                }
                break;
                
            case "login":
                require("controllers/controllerLogin.php");
                break;
                
            case "users":
                $action = $url[1];
                switch($action)
                {
                    case "panel":
                        $userZone = $url[2];
                        switch($userZone)
                        {
                            case "data":
                                require("controllers/controllerUserPanel.php");
                                break;
                                
                            case "update-data":
                                require("controllers/controllerUpdateUserData.php");
                                break;
                        }
                        break;
                        
                    case "validate":
                        $field = $url[2];
                        $query = $url[3];
                        $_GET["field"] = $field;
                        $_GET["query"] = $query;
                        require("controllers/controllerValidationInput.php");
                        break;
                        
                    case "register":
                        require("controllers/controllerRegister.php");
                        break;
                        
                    case "access":
                        require("controllers/controllerAccess.php");
                        break;
                        
                    case "update":
                        require("controllers/controllerUpdateUserData.php");
                        break;
                        
                }
                break;
            
            case "logout":
                require("controllers/controllerLogout.php");
                break;
                
            case "search":
                require("controllers/controllerSearch.php");
                break;
                
            case "products":
                $action = $url[1];
                switch($action)
                {
                    case "read":
                        $subcategory = $url[2];
                        $_GET["product"] = $subcategory; require("controllers/controllerCategoryProducts.php");

                        break;
                        
                    case "load":
                        $product = $url[2];
                        $_GET["productID"] = $product;
                        require("controllers/controllerProduct.php");
                        break;
                }
                break; 
            
            case "cart":
                $action = $url[1];
                switch($action)
                {
                    case "to":
                        $optionCart = $url[2];
                        if($optionCart == 1 || $optionCart == 2)
                        {
                            $valueCart  = $url[3];
                            $_GET["option"] = $optionCart;
                            $_GET["value"]  = $valueCart;
                            require("controllers/controllerCart.php");
                        }
                        break;
                        
                    case "cancel":
                        $optionCart = $url[2];
                        if($optionCart == 3)
                        {
                            $_GET["option"] = $optionCart;
                            require("controllers/controllerCart.php");
                        }
                        break;
                        
                    case "update":
                        $optionCart = $url[2];
                        if($optionCart == 4)
                        {
                            $quantity = $url[3];
                            $idProductCart = $url[4];
                            
                            $_GET["option"] = $optionCart;
                            $_GET["quantity"] = $quantity;
                            $_GET["idProductCart"] = $idProductCart;
                            require("controllers/controllerCart.php");
                        }
                        break;
                }
                break;
                
            case "buy":
                require("controllers/controllerPurchase.php");
                break;
        }
    }

?>




<?php

/*if (!isset($_GET["url"])) { //URL: deic-dc0/~tdiw-b12

    require "controller/products/listProducts.php";
} else {   //URL: deic-dc0/~tdiw-b12/products/list
    // Parse *url* parameter (/<resource>/<action>/<parameters>
    // p.e. "products/list
    // p.e. "products/read/1
    //name='prod1'&price='300'"
    $url = explode("/", $_GET["url"]);

    $resource = $url[0]; //products
    $action = $url[1]; // list o read

    switch($resource) {

        case "products":

            switch($action) {
                case "list":

                    require "controller/products/listProducts.php";

                    break;
                case "read":
                    //1
                    $id = $url[2]; //?name='prod1'&price='300'
                
                    require "controller/products/showDetails.php";

                    break;
            }
            break;
    }
}*/

?>