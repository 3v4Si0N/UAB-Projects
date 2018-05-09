<?php

	session_start();
    error_reporting(0);

	if(isset($_COOKIE['username']) && isset($_COOKIE['password']))
	{
		require("model/model.php");
		$stmt = $DB->getUserData($_COOKIE['username'], $_COOKIE['password']);

		/* vincular variables a la sentencia preparada */
        $stmt->bind_result($name, $username, $password, $email, $street, $city, $phone, $postal, $card);

        //Obtención de datos
        while($stmt->fetch())
        {
            $name;
            $username;
            $password;
            $email;
            $street;
            $city;
            $phone;
            $postal;
            $card;
        }

        $stmt->close();

        $userID             = $DB->getUsernameID($_COOKIE['username']);
        $salesUserID        = $DB->getUserPurchases($userID);
        
        $salesUserArray     = [];
        $userPurchasesArray = [];
        
        if($salesUserID->num_rows > 0)
        {
            while($rowSales = $salesUserID->fetch_assoc())
            {
                $salesUserArray[] = $rowSales;
            }

            foreach ($salesUserArray as $rowSalesID)
            {
                $productsPurchases = $DB->getUserProductsPurchases($userID, $rowSalesID['reference_id']);
                while($rowProductsPurchases = $productsPurchases->fetch_assoc())
                {
                    $userPurchasesArray[$rowSalesID['reference_id']][] = $rowProductsPurchases;

                }
            }
        }
        else
        {
            $message = "You have not made any purchases";
        }

        $DB->closeConnectionDB();
        $sessionDetalle = $_SESSION["detalle"];

        require("views/viewUserPanel.php");
	}
	else
	{
		require("views/viewForbidden.html");
	}
?>
