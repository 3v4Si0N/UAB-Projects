<?php

	session_start();

    setcookie("username", $_COOKIE["username"], time() - (86400 * 30), "/");
    setcookie("password", $_COOKIE["password"], time() - (86400 * 30), "/");

	session_destroy();

	//echo "Sesion acabada";
	header("Refresh:0; url='http://localhost/VScomponents/'");

?>
