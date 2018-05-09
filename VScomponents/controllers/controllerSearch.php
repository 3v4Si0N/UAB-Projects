<?php
    session_start();
    //error_reporting(0);
    if(isset($_REQUEST['q']))
    {
        $search = $_GET['q'];
        
        $search = trim($search);
        $search = stripslashes($search);
        $search = htmlspecialchars($search);

        /*explode cuando buscas algo reconoce cada palabra si pones un espacio entre ellas, si no pones espacios lo reconoce como 1 sola palabra.*/
        $terms  = explode(" ", $search);

        require("model/model.php");
        $result_search = $DB->getSearchProducts($terms);
        $DB->closeConnectionDB();

        if(($result_search->num_rows > 0) && ($search != ""))
        {
            $products = array();
            while($row_search = $result_search->fetch_assoc())
            {
                $products[] = $row_search;
            }
        }
        else
        {
            $result = "Sorry, no results were found. Try another similar or simpler search.";
        }
    }
    else
    {
        $result = "Sorry, no results were found. Try another similar or simpler search.";
    }

    require("views/viewSearch.php");
?>
