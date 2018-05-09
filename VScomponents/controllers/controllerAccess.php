<?php
	session_start();
    $username = $password = "";

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
		$username = test_input($_POST["username"]);
		$password = test_input($_POST["password"]);

        $username = preg_replace('([^A-Za-z0-9_])', '', $username); // Elimina caracteres especiales
        $password = sha1($password); // Ciframos la contraseña en sha1 para su posterior comprobación

		if (isset($username) && $username != ""
        && isset($password) && $password != "")
		{
			require("../model/model.php");

			$stmt = $DB->controlAccess($username);

	    /* Store the result (to get properties) */
	    $stmt->store_result();
	    /* Get the number of rows */
	    $num_of_rows = $stmt->num_rows;

	    if($num_of_rows > 0)
	    {
	        /* vincular variables a la sentencia preparada */
	        $stmt->bind_result($bd_username, $bd_password);

	        /* obtener valores */
	        while ($stmt->fetch())
	        {
	            if(($username == $bd_username) && ($password == $bd_password))
	            {
	                setcookie("username", $username, time() + (86400 * 30), "/");
	                setcookie("password", $password, time() + (86400 * 30), "/");

	                // Mensaje ajax para comprobar que se ha iniciado sesión correctamente
	                echo "success";
	            }
	            else
	            {
	                require("../views/viewWrongCredentials.html");
	            }
	        }
	    }
	    else
	    {
	        require("../views/viewWrongCredentials.html");
	    }
		    $stmt->close();
		    $DB->closeConnectionDB();
		}
		else
		{
			require("../views/viewWrongCredentials.html");
		}
	}
	else
	{
		require("../views/viewForbidden.html");
	}
?>
