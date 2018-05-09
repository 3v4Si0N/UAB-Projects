<!DOCTYPE html>
<html>
  <head>
    <title> UAB/Enginyeria </title> 
    <meta charset="utf-8"/> 
  </head> 
  <body> 
    <?php
      $nombre = $_POST['name'];
      $grado = $_POST['degrees'];
      $mencion = $_POST['mentions'];
    ?>
    <h2>Resum de les teves dades:</h2>
    <p>Nom: <?php echo $nombre; ?></p>
    <p>Grau: <?php echo $grado; ?></p>
    <p>Menció: <?php echo $mencion; ?></p>
  </body>
</html>  