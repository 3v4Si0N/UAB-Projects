<?php

$servername = "localhost";
$userName   = "tdiw-j5";
$passwd     = "";
$dbname     = "tdiw-j5";

class DataBase{
	var $conn;
		function DataBase($servername, $username, $password, $dbname)
	    {
	        // Create connection
			$this->conn = new mysqli($servername, $username, $password, $dbname);
			// Check connection
			if ($this->conn->connect_error)
			{
			    die("Connection failed: " . $this->conn->connect_error);
			}
	        // setting the return characters from the database php queries to  utf8 !!!!!
	        $this->conn->set_charset("utf8");
	    }

		function closeConnectionDB()
		{
	        $this->conn->close();
        }

		function sqlCategory()
		{
			$sql_category = "SELECT id, name FROM Category ORDER BY name";
			$result_category = $this->conn->query($sql_category);
			return $result_category;
		}

		function sqlSubCategory()
		{
			$sql_subcategory = "SELECT id, category_id, name FROM Sub_Category ORDER BY name";
			$result_subcategory = $this->conn->query($sql_subcategory);
			return $result_subcategory;
		}

		function sqlNumProducts()
		{
			$sql_num_products = "SELECT * FROM Products";
			$result_num_Products = $this->conn->query($sql_num_products);
			return $result_num_Products;
		}

		function sqlProducts($pagina, $inicio, $tam_pagina)
		{
			if(is_numeric($pagina))
	        {
	            $sql_products = "SELECT id, sub_category_id, name, product_image, price FROM Products LIMIT ".$inicio.",".$tam_pagina;
	            $result_product = $this->conn->query($sql_products);
	        }
	        else
	        {
	            $result_product = 0;
	        }

	        return $result_product;
		}

		function sqlCategoryProducts($product)
		{
			$sql_categoryProducts = "SELECT id,name,price,product_image FROM Products WHERE sub_category_id=".$product;
			$result = $this->conn->query($sql_categoryProducts);
			return $result;
		}

        function sqlAllProducts($subID)
		{
			$sql_allProducts = "SELECT id, name FROM Products WHERE sub_category_id=".$subID." ORDER BY name";
			$result = $this->conn->query($sql_allProducts);
			return $result;
		}

        function getProductData($id)
        {
            $stmt = $this->conn->prepare("SELECT name, specs, characteristics, stock, price, product_image, brand_image, brand FROM Products WHERE id=?");
			$stmt->bind_param('i', $id);
			$stmt->execute();
			return $stmt;
        }

		function getUsernameID($session)
		{
			$stmt2 = $this->conn->prepare("SELECT id FROM Users WHERE username=?");
			$stmt2->bind_param('s', $session);

			$stmt2->execute();
			$stmt2->bind_result($bd_id);
			while($stmt2->fetch())
		    {
		    	$bd_id;
		    }
		    $stmt2->close();
		    return $bd_id;
		}

        function validUsername($value)
        {
            $stmt = "SELECT username FROM Users WHERE username='".$value."'";
			$result_stmt = $this->conn->query($stmt);

			return $result_stmt;
        }

		function updateUserData($name, $username, $password, $email, $street, $city, $phone, $postal, $card, $bd_id)
		{
			$stmt = $this->conn->prepare("UPDATE Users SET name=?, username=?, password=?, email=?, street=?, city=?, phone=?, postal_code=?, credit_card=? WHERE id=?");
			$stmt->bind_param("ssssssiisi", $name, $username, $password, $email, $street, $city, $phone, $postal, $card, $bd_id);

			$stmt->execute();
			$stmt->close();
		}

		function controlAccess($username)
		{
			//prepare and bind
			$stmt = $this->conn->prepare("SELECT username, password FROM Users WHERE username=?");
			$stmt->bind_param('s', $username);
			$stmt->execute();
		    return $stmt;
		}

		function adminAccess($username)
		{
			//prepare and bind
			$stmt = $this->conn->prepare("SELECT username, password FROM Admin WHERE username=?");
			$stmt->bind_param('s', $username);
			$stmt->execute();
		    return $stmt;
		}

		function getUserData($username, $password)
		{
			$stmt = $this->conn->prepare("SELECT name,username,password,email,street,city,phone,postal_code,credit_card FROM Users WHERE username=? AND password=?");
			$stmt->bind_param('ss', $username, $password);
			$stmt->execute();
			return $stmt;
		}

		/*function getUserPurchases($userID)
	    {
				$userPurchases =
				"SELECT s.reference_id AS ID_compra, s.date AS Date_compra, s.price AS Sale_price, p.name AS Product_name, p.price AS Product_price, shp.quantity AS Quantity
					FROM Sales s, Sales_has_Products shp, Products p
					WHERE s.user_id = ".$userID."
					AND shp.sales_reference_id = s.reference_id
					AND shp.product_id = p.id
					ORDER BY s.date DESC;";

				$result = $this->conn->query($userPurchases);
				return $result;
	    }*/
    
    
        function getUserPurchases($userID)
        {
            $purchasesID = "SELECT reference_id, user_id, price, date FROM Sales WHERE user_id = ".$userID;
            $result = $this->conn->query($purchasesID);
            return $result;
        }
    
    
        function getUserProductsPurchases($userID, $referenceID)
        {
            $productsPurchases = 
            "SELECT s.reference_id AS ID_compra, s.date AS Date_compra, s.price AS Sale_price, p.name AS Product_name, p.price AS Product_price, shp.quantity AS Quantity
                FROM Sales s, Sales_has_Products shp, Products p
                WHERE s.user_id = ".$userID." AND shp.sales_reference_id = ".$referenceID." AND s.reference_id = shp.sales_reference_id AND shp.product_id = p.id
                ORDER BY s.date DESC";
            $result = $this->conn->query($productsPurchases);
            return $result;
        }

		function insertRegisterData($name, $username, $password, $email, $street, $city, $phone, $postal, $card)
		{
			// prepare and bind
			$stmt = $this->conn->prepare("INSERT INTO Users (name, username, password, email, street, city, phone, postal_code, credit_card) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			$stmt->bind_param("ssssssiis", $name, $username, $password, $email, $street, $city, $phone, $postal, $card);

			$stmt->execute();
			$stmt->close();
		}

        function insertNewProduct($subcategory, $name, $specs, $characs, $stock, $price, $productImage, $brandImage, $brandName)
        {
            // prepare and bind
            $stmt = $this->conn->prepare("INSERT INTO Products (sub_category_id, name, specs, characteristics, stock, price, product_image, brand_image, brand) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            $stmt->bind_param("isssiisss", $subcategory, $name, $specs, $characs, $stock, $price, $productImage, $brandImage, $brandName);

            $stmt->execute();
            $stmt->close();
        }

        function updateProductData($productID, $specs, $characs, $stock, $price)
        {
            $stmt = $this->conn->prepare("UPDATE Products SET specs=?, characteristics=?, stock=?, price=? WHERE id=?");
            $stmt->bind_param("ssiii", $specs, $characs, $stock, $price, $productID);

            $stmt->execute();
            $stmt->close();
        }

		function getProductStock($productID)
		{
			$stmt = $this->conn->prepare("SELECT stock FROM Products WHERE id=?");
			$stmt->bind_param('i', $productID);
			$stmt->execute();

			$stmt->bind_result($stock);
			while($stmt->fetch())
			{
					$stock;
			}
			return $stock;
		}

		function updateProductStock($productID, $quantity)
		{
			// get stock
			$stock = $this->getProductStock($productID) - $quantity;

			$stmt = $this->conn->prepare("UPDATE Products SET stock=? WHERE id=?");
			$stmt->bind_param("ii", $stock, $productID);

			$stmt->execute();
			$stmt->close();
		}

        function deleteProduct($productID)
        {

        	$this->saveProductSecondaryTable($this->getProductDataToSave($productID));
        	if($this->productHasBeenBought($productID))
        	{
				$this->deleteSalesHasProducts($productID);
        	}

            $stmt = $this->conn->prepare("DELETE FROM Products WHERE id=?");
			$stmt->bind_param("i", $productID);

			$stmt->execute();
			$stmt->close();
        }

        function productHasBeenBought($productID)
        {
			$result = false;

        	$stmt = $this->conn->prepare("SELECT id FROM Sales_has_Products WHERE product_id=? LIMIT 1");
			$stmt->bind_param("i", $productID);

			$stmt->execute();

			if ($stmt->num_rows > 0)
			{
				$result = true;
			}

			$stmt->close();

			return $result;
        }

        function deleteSalesHasProducts($productID)
        {
        	$stmt = $this->conn->prepare("DELETE FROM Sales_has_Products WHERE product_id=?");
			$stmt->bind_param("i", $productID);

			$stmt->execute();
			$stmt->close();
        }

        function saveProductSecondaryTable($productInfo)
        {
        	$stmt = $this->conn->prepare("INSERT INTO Sold_Products (previous_product_id, name) VALUES (?, ?)");
			$stmt->bind_param("is", $productInfo[0], $productInfo[1]);

			$stmt->execute();
			$stmt->close();
        }

        function getProductDataToSave($productID)
        {
        	$stmt = $this->conn->prepare("SELECT id, name FROM Products WHERE id=?");

            $stmt->bind_param("i", $productID);
            $stmt->execute();
			
			$stmt->bind_result($id, $name);

            while ($stmt->fetch())
            {
            	$name;
            	$id;
            }
			$stmt->close();
            $result = [$id, $name];

            return $result;
        }

        function insertSale($totalPrice, $usernameID, $date)
        {
            // prepare and bind
			$stmt = $this->conn->prepare("INSERT INTO Sales (user_id, price, date) VALUES (?, ?, ?)");
			$stmt->bind_param("iss", $usernameID, $totalPrice, $date);

			$stmt->execute();
			$stmt->close();
        }


        function getReferenceID($date)
        {
            $stmt = $this->conn->prepare("SELECT reference_id FROM Sales WHERE date=?");

            $stmt->bind_param("s", $date);
            $stmt->execute();
            return $stmt;
        }

        function insertSalesHasProducts($productID, $referenceID, $quantity)
        {
            // prepare and bind
			$stmt = $this->conn->prepare("INSERT INTO Sales_has_Products (product_id, sales_reference_id, quantity) VALUES (?, ?, ?)");
			$stmt->bind_param("iii", $productID, $referenceID, $quantity);

			$stmt->execute();
			$stmt->close();
        }

        function getSearchProducts($terms)
        {
            $sql_search = "SELECT id, name, price, product_image FROM Products WHERE ";
            $i = 0;

            //recorre palabra a palabra el array de la BD
            foreach($terms as $each)
            {
                $i++;
                if($i == 1)
                {
                    //solo para 1 palabra
                    $_each = mysql_escape_string($each);
                    $sql_search .= "name LIKE '%$_each%'";
                }
                else
                {
                    //mas de una palabra
                    $_each = mysql_escape_string($each);
                    $sql_search .= "OR name LIKE '%$_each%'";
                }
            }

            $result_search = $this->conn->query($sql_search);
            return $result_search;
        }
	}
	$DB = new DataBase($servername, $userName, $passwd, $dbname);
?>
