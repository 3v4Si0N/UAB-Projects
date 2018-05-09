<?php

    echo $message;
    if($message == 'Compra realizada correctamente')
    {
        header("Refresh:1; url='http://localhost/VScomponents/users/panel/data'");
    }

?>