<?php
	session_start();
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
        $email    = test_input($_POST["email"]);
        $phone    = test_input($_POST["phone"]);
        $street   = test_input($_POST["street"]);
        $city     = test_input($_POST["city"]);
        $postal   = test_input($_POST["postal"]);
        $card     = test_input($_POST["card"]);

        if(isset($_COOKIE['username']) && isset($_COOKIE['password']))
        {
            $username = preg_replace('([^A-Za-z0-9_])', '', $username); // Elimina caracteres especiales
            $email    = filter_var($email, FILTER_VALIDATE_EMAIL);
            $phone    = filter_var($phone, FILTER_VALIDATE_INT);
            $postal   = filter_var($postal, FILTER_VALIDATE_INT);

            if(preg_match("/^[a-zA-Z]+$/", $name) && $username && $password && $email && $phone && preg_match("/^[a-zA-Z]+$/", $street) && preg_match("/^[a-zA-Z]+$/", $city) && $postal && preg_match("/^[0-9]+$/", $card))
            {
                $password = sha1($password);
                require("model/model.php");

                $bd_id = $DB->getUsernameID($_COOKIE['username']);
                $DB->updateUserData($name, $username, $password, $email, $street, $city, $phone, $postal, $card, $bd_id);


                $DB->closeConnectionDB();
                $_COOKIE['username'] = $username;
                $_COOKIE['password'] = $password;

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
    }
    else
    {
        require("views/viewForbidden.html");
    }
?>
