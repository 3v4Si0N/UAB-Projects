<?php

    $name = $username = $password = $password = $email = $phone = $street = $city = $postal = $card = "";

    //Función anti XSS
    function test_input($data)
    {
        $data = trim($data);
        $data = stripslashes($data);
        $data = htmlspecialchars($data);
        return $data;
    }

	if($_POST)
	{
        // set parameters
        $name     = test_input($_POST["name"]);
        $username = test_input($_POST["username"]);
        $password = test_input($_POST["password"]);
        $password = sha1($password);
        $email    = test_input($_POST["email"]);
        $phone    = test_input($_POST["phone"]);
        $street   = test_input($_POST["street"]);
        $city     = test_input($_POST["city"]);
        $postal   = test_input($_POST["postal"]);
        $card     = test_input($_POST["card"]);

        $username = preg_replace('([^A-Za-z0-9_])', '', $username); // Sólo permite letras mayúsculas, minúsculas y números
        $email    = filter_var($email, FILTER_VALIDATE_EMAIL);
        $phone    = filter_var($phone, FILTER_VALIDATE_INT);
        $postal   = filter_var($postal, FILTER_VALIDATE_INT);


        // "/^[0-9]+$/" --> solo se permiten numeros sin espacios en la cadena
        // "/^[a-zA-Z]+$/" --> solo se permiten letras minusculas y mayusculas sin espacios en la cadena

		if(preg_match("/^[a-zA-Z]+$/", $name) && $username && $password && $email && $phone && preg_match("/^[a-zA-Z]+$/", $street) && preg_match("/^[a-zA-Z]+$/", $city) && $postal && preg_match("/^[0-9]+$/", $card))
		{
			require("model/model.php");
            $countUser = $DB->validUsername($username);

            //Comprobamos que el username que quiere el cliente no exista
            if($countUser->num_rows == 0)
            {
                $stmt = $DB->insertRegisterData($name, $username, $password, $email, $street, $city, $phone, $postal, $card);

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
            require("views/viewWrongField.html");
        }
	}
	else
	{
		require("views/viewForbidden.html");
	}
?>
