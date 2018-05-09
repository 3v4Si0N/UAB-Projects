<?php

    error_reporting(0);
    $table = $_GET['table'];

    if(is_numeric($table))
    {
        switch($table)
        {
            case 1:
                require("controllerCreateTable.php");
                break;
            case 2:
                require("controllerEditTable.php");
                break;
            case 3:
                require("controllerDeleteTable.php");
                break;
            default:
                $messageError = '0 results';
                break;
        }
    }
    else
    {
        require("views/viewForbidden.html");
    }
?>
