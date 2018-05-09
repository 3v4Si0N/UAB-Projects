/**
 * Function that builds an xmlhttp object deppending on the browser type
 * @return an xmlhttp object.
 */
function getXMLHTTP() {
    var xmlhttp;

    if (window.XMLHttpRequest) {
        // code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    } else {
        // code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    return xmlhttp;
}

function getURL()
{
    document.links["url"].href = "http://localhost/";
}


function getUrlbase()
{
    return "/VScomponents/";
}


function loadProducts(subCategoryID)
{
	var xmlhttp = getXMLHTTP();

	/* What to do when we get the asynchormous response from the server */
	/* What to do when we get the asynchormous response from the server */
	xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            document.getElementById("sectionProduct").innerHTML = xmlhttp.responseText;
		}
	};

	/* Sending the asynchronous request to the server */
	xmlhttp.open("GET", "products/read/"+subCategoryID, true);
	xmlhttp.send();
}


function validateLoginForm()
{
    var xmlhttp = getXMLHTTP();
    var formLogin = new FormData(document.getElementById("formLogin"));

	/* What to do when we get the asynchormous response from the server */
	/* What to do when we get the asynchormous response from the server */
	xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            $response = xmlhttp.responseText;
            if ($response == "success")
            {
                window.location.href="users/panel/data";
            }
            else
            {
                document.getElementById("Wcredentials").innerHTML = xmlhttp.responseText;
            }
		}
	};

    /* Sending the asynchronous request to the server */
    // access
	xmlhttp.open("POST", "controllers/controllerAccess.php", true);
    xmlhttp.send(formLogin);
}


function validateRegisterForm()
{
    var xmlhttp = getXMLHTTP();
    var formRegister = new FormData(document.getElementById("formRegister"));

	/* What to do when we get the asynchormous response from the server */
	/* What to do when we get the asynchormous response from the server */
	xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            document.getElementById("confirmation").innerHTML = xmlhttp.responseText;
		}
	};

    /* Sending the asynchronous request to the server */
	xmlhttp.open("POST", "users/register", true);
    xmlhttp.send(formRegister);
}


// AJAX code to check input field values when onblur event triggerd.
function validateInputField(field, query, divId)
{
    var xmlhttp = getXMLHTTP();

    /* What to do when we get the asynchormous response from the server */
	/* What to do when we get the asynchormous response from the server */
    xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            document.getElementById(divId).innerHTML = xmlhttp.responseText;
        }
    };

    xmlhttp.open("GET", getUrlbase() + "users/validate/"+field+"/"+query, true);
    xmlhttp.send();
}


function validateUpdateData()
{
    var xmlhttp = getXMLHTTP();
    var formUserData = new FormData(document.getElementById("formUserData"));

    /* What to do when we get the asynchormous response from the server */
	/* What to do when we get the asynchormous response from the server */
    xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            document.getElementById("confirmation").innerHTML = xmlhttp.responseText;
        }
    };

    /* Sending the asynchronous request to the server */
    xmlhttp.open("POST", "update-data", true);
    xmlhttp.send(formUserData);
}


function showTable(tableID)
{
    var xmlhttp = getXMLHTTP();

    /* What to do when we get the asynchormous response from the server */
	/* What to do when we get the asynchormous response from the server */
    xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            document.getElementById("table").innerHTML = xmlhttp.responseText;
        }
    };

    /* Sending the asynchronous request to the server */
    xmlhttp.open("GET", "show/"+tableID, true);
    xmlhttp.send();
}


function loadSubcategory(categoryID)
{
  var xmlhttp = getXMLHTTP();
  //var degressTag = document.getElementById("degress");

  /* What to do when we get the asynchormous response from the server */
  xmlhttp.onreadystatechange = function()
  {
    if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
    {
      document.getElementById("subcategory").innerHTML = xmlhttp.responseText;
    }
  };

  /* Sending the asynchronous request to the server */
  xmlhttp.open("GET", "read/category/"+categoryID, true);
  xmlhttp.send();
}


function loadListProducts(subCategoryID)
{
	var xmlhttp = getXMLHTTP();

	/* What to do when we get the asynchormous response from the server */
	/* What to do when we get the asynchormous response from the server */
	xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            document.getElementById("products").innerHTML = xmlhttp.responseText;
		}
	};

	/* Sending the asynchronous request to the server */
	xmlhttp.open("GET", "read/subcategory/"+subCategoryID, true);
	xmlhttp.send();
}


function loadProductData(productID)
{
    var xmlhttp = getXMLHTTP();

	/* What to do when we get the asynchormous response from the server */
	/* What to do when we get the asynchormous response from the server */
	xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            document.getElementById("divProduct").innerHTML = xmlhttp.responseText;
		}
	};

	/* Sending the asynchronous request to the server */
	xmlhttp.open("GET", "read/product/"+productID, true);
	xmlhttp.send();
}


function createNewProduct()
{
    var xmlhttp = getXMLHTTP();
    var data = new FormData(document.getElementById("formPost"));

    /* What to do when we get the asynchormous response from the server */
	/* What to do when we get the asynchormous response from the server */
    xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            document.getElementById("confirmation").innerHTML = xmlhttp.responseText;
        }
    };

    /* Sending the asynchronous request to the server */
    xmlhttp.open("POST", "create", false);
    xmlhttp.send(data);
}


function editOneProduct()
{
    var xmlhttp = getXMLHTTP();
    var data = new FormData(document.getElementById("formPost"));

    /* What to do when we get the asynchormous response from the server */
	/* What to do when we get the asynchormous response from the server */
    xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            document.getElementById("confirmation").innerHTML = xmlhttp.responseText;
        }
    };

    /* Sending the asynchronous request to the server */
    xmlhttp.open("POST", "edit", false);
    xmlhttp.send(data);
}


function deleteOneProduct()
{
    var xmlhttp = getXMLHTTP();

    /* What to do when we get the asynchormous response from the server */
	/* What to do when we get the asynchormous response from the server */
    xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            document.getElementById("confirmation").innerHTML = xmlhttp.responseText;
        }
    };

    /* Sending the asynchronous request to the server */
    xmlhttp.open("POST", "delete", false);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send(getProductData());
}

function getProductData()
{
    var productID = document.getElementById("products");

    return "products=" + encodeURIComponent(productID.value);
}


// CARRITO DE COMPRAS

function productToCart(option, value)
{
    var xmlhttp = getXMLHTTP();
    
	xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            if(option == 1)
            {
                alertify.success("Product has been added to cart");
            }
            else if(option == 2)
            {
                alertify.error("Product has been deleted");
            }
            document.getElementById("viewCart").innerHTML = xmlhttp.responseText;
		}
	};

	xmlhttp.open("GET", getUrlbase() + "cart/to/"+option+"/"+value, true);
	xmlhttp.send();
}

function cancelOrder()
{
    var xmlhttp = getXMLHTTP();

	xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            alertify.success("The order has been cancelled"); 
            document.getElementById("viewCart").innerHTML = xmlhttp.responseText;
		}
	};

	xmlhttp.open("GET", getUrlbase() + "cart/cancel/3", true);
	xmlhttp.send();
}

function updateQuantity(quantity, idProductCart)
{
    var xmlhttp = getXMLHTTP();

    xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            document.getElementById("updateQuantity").innerHTML = xmlhttp.responseText;
        }
    };

    xmlhttp.open("GET", getUrlbase() + "cart/update/4/"+quantity+"/"+idProductCart, true);
    xmlhttp.send();
}


// Funcion para mostrar un producto en específico
function loadProduct(productID)
{
    window.location.href= getUrlbase() + "products/load/"+productID;
}


product_cart_id = 0;
values = [];

function setValue(value)
{
    product_cart_id = value;
}

function getValue()
{
    return product_cart_id;
}

function setValues(value, name)
{
    values = [value, name];
}

function getValues()
{
    return values;
}


// JQUERY

// MUNU DESPLEGABLE NAV PRINCIPAL
$(document).ready(function()
{
   // Muestra y oculta los menús
   $('.li-dropdown').hover(
      function(e)
      {
         $(this).find('.ul-dropdown-content').css({display: "block"});
      },
      function(e)
      {
         $(this).find('.ul-dropdown-content').css({display: "none"});
      }
   );
});


// Agregar un producto al carrito
/*$(function(){
	
	$(".btn-agregar-producto").off("click");
	$(".btn-agregar-producto").on("click", function(e) {
        var value = getValue();
		$.ajax({
			url: getAbsoluteURL() + 'controllers/controllerCart.php',
			type: 'get',
			data: {'option':1, 'value':value},
			dataType: 'json'
		}).done(function(data){
			if(data.success==true){
				//$(".btn-agregar-producto").val('');
				alertify.success(data.msj);
				$("#viewCart").load(getAbsoluteURL() + 'views/viewCart.php');
			}else{
				alertify.error(data.msj);
			}
		})
	});
}); */   
	
// Eliminar un producto del carrito
/*$(function(){

	$(".btn-eliminar-producto").off("click");
	$(".btn-eliminar-producto").on("click", function(e) {
        var value = getValue();
		$.ajax({
			url: getAbsoluteURL() + 'controllers/controllerCart.php',
			type: 'get',
			data: {'option':2, 'value':value},
			dataType: 'json'
		}).done(function(data){
			if(data.success==true){
				alertify.error(data.msj);
				$("#viewCart").load(getAbsoluteURL() + 'views/viewCart.php');
			}else{
				alertify.error(data.msj);
			}
		})
	});
});
*/

// Actualizar cantidad en el carrito
/*$(function(){

	$(".btn-update-quantity").off("blur");
	$(".btn-update-quantity").on("blur", function(e) {
        var values = getValues();
		$.ajax({
			url: getAbsoluteURL() + 'controllers/controllerCart.php',
			type: 'get',
			data: {'option':4, 'quantity':values[0], 'idProductCart':values[1]},
			dataType: 'json'
		}).done(function(data){
			if(data.success==true){
				$("#updateQuantity").load(getAbsoluteURL() + 'views/viewCart.php');
			}else{
				alertify.error(data.msj);
			}
		})
	});
});*/


// Vaciar carrito
/*$(function(){
	
	$(".btn-vaciar-carrito").off("click");
	$(".btn-vaciar-carrito").on("click", function(e) {
		$.ajax({
			url: getAbsoluteURL() + 'controllers/controllerCart.php',
			type: 'get',
			data: {'option':3},
			dataType: 'json'
		}).done(function(data){
			if(data.success==true){
				alertify.success(data.msj);
				$("#viewCart").load(getAbsoluteURL() + 'views/viewCart.php');
			}else{
				alertify.error(data.msj);
			}
		})
	});
});*/