<?php

    function connectDB(){
        $servername = "localhost";
        $username = "3v4Si0N";
        $password = "xkzfmxp9t";
        $dbname = "test";

        // Create connection
        $conn = new mysqli($servername, $username, $password, $dbname);

        if ($conn->connect_error)
            die("Connection failed: " . $conn->connect_error); // Check connection

        // setting the return characters from the database php queries to  utf8 !!!!!    
        $conn->set_charset("utf8");
        return $conn;
    }
?>