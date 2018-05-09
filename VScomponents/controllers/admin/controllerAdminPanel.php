<?php
	
	session_start();

	if(isset($_SESSION['adminUser']) && isset($_SESSION['adminPassword']))
	{
	    require("views/admin/viewAdminPanel.php");
	}
	else
	{
		require("views/viewForbidden.html");
	}
?>