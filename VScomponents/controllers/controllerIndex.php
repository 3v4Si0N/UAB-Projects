<?php

    error_reporting(0); // Elimina mensajes de error/warnings/notice
    session_start();
    
    if(!isset($_SESSION['detalle']))
    {
        $_SESSION['detalle'] = [];
    }

	require("model/model.php");
    $messageError        = '';
	$result_category     = $DB->sqlCategory();
	$result_subcategory  = $DB->sqlSubCategory();
	$result_num_Products = $DB->sqlNumProducts();
	$num_total_registros = $result_num_Products->num_rows;

    // PAGINACIÓN
    $tam_pagina = 8; //Limito la busqueda

    //examino la página a mostrar y el inicio del registro a mostrar
    if(isset($pageNumber))
    {
        $pagina = $pageNumber;
    }
    else
    {
        $pagina = 1;
    }

    if (!$pagina)
    {
       $inicio = 0;
       $pagina = 1;
    }
    else
    {
       $inicio = ($pagina - 1) * $tam_pagina;
    }
    //calculo el total de páginas
    $total_paginas   = ceil($num_total_registros / $tam_pagina);
    $result_products = $DB->sqlProducts($pagina, $inicio, $tam_pagina);


    //Cálculo de las categorias de productos de la barra de navegación
    if($result_category->num_rows > 0)
    {
        $categories = [];
        // output data of each row
        while($row_c = $result_category->fetch_assoc())
        {
            $categories[] = $row_c;
        }
    }
    else
    {
        $messageError = '0 results';
    }

    // Cálculo de las subcategorias de productos de la barra de navegación
    if($result_subcategory->num_rows > 0)
    {
        $subcategorias = [];
        while($row_s = $result_subcategory->fetch_assoc())
        {
            $subcategorias[] = $row_s;
        }
        $DB->closeConnectionDB();
    }
    else
    {
      $messageError = '0 results';
    }

    // Cálculo de los productos insertados dinámicamente
    if(is_numeric($pagina)) // Anti SQLi
    {
        if($result_products->num_rows > 0)
        {
            $products = [];
            $num      = 0;
            while($row_p = $result_products->fetch_assoc())
            {
                $products[] = $row_p;
            }
        }
        else
        {
          $messageError = '0 results';
        }
    }
    else
    {
        $products = 0;
    }

    $cookieUsername = $_COOKIE["username"];
    $cookiePassword = $_COOKIE["password"];
    $sessionDetalle = $_SESSION["detalle"];
	require("views/viewIndex.php");
?>
