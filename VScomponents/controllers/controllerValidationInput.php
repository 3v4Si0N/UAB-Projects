<?php

    error_reporting(0); // Elimina mensajes de error/warning/notice
    $value = $_GET['query'];
    $formfield = $_GET['field'];
    // Check Valid or Invalid user name when user enters user name in username input field.
    if ($formfield == "username") {
        require("model/model.php");
        $value = preg_replace('([^A-Za-z0-9_])', '', $value); // Elimina caracteres especiales menos _
        $contador = $DB->validUsername($value);
        $DB->closeConnectionDB();

        require("views/viewConfirmUsername.php");
    }

    // Check Valid or Invalid email when user enters email in email input field.
    if ($formfield == "email") {
        require("views/viewConfirmEmail.php");
    }

?>