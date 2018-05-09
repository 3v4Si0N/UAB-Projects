/**
 * Function that builds an xmlhttp object deppending on the browser type
 * @return an xmlhttp object.
 */
function getXMLHTTP()
{
  var xmlhttp;
  
  if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
    xmlhttp = new XMLHttpRequest();
  }
  else
  {// code for IE6, IE5
    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
  }
  return xmlhttp;
}

/**
* Function that modifies the content of the mentions select box with the mentions of 
* the selected degree.
*/
function loadMentions(degressTag){
  var xmlhttp = getXMLHTTP();
  //var degressTag = document.getElementById("degress");

  /* What to do when we get the asynchormous response from the server */
  xmlhttp.onreadystatechange = function()
  {
    if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
    {
      document.getElementById("mentions").innerHTML = xmlhttp.responseText;
    }
  };

  /* Sending the asynchronous request to the server */
  xmlhttp.open("GET", "controladores/controladorDegreeMentions.php?degree="+degressTag, true);
  xmlhttp.send();
}