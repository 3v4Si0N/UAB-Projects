<?php
	session_start();

	if($_POST)
	{
		$adminUser     = $_POST["adminUser"];
		$adminPassword = sha1($_POST["adminPassword"]);

		if (isset($adminUser) && isset($adminPassword))
		{
			require("model/model.php");
			$stmt = $DB->adminAccess($adminUser);

			/* vincular variables a la sentencia preparada */
		    $stmt->bind_result($bd_adminUser, $bd_adminPassword);

		    /* obtener valores */
		    while ($stmt->fetch())
		    {
		        if(($adminUser == $bd_adminUser) && ($adminPassword == $bd_adminPassword))
						{
							$_SESSION["adminUser"] = $adminUser;
							$_SESSION["adminPassword"] = $adminPassword;

							//redireccionar cuando se haga login
							header('location: panel');
						}
						else
						{
							require("views/viewForbidden.html");
						}
		    }
		    $stmt->close();
		    $DB->closeConnectionDB();
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
