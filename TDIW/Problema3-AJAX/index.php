<!DOCTYPE html>
<html>
<head>
  <title> UAB/Enginyeria </title> 
  <meta charset="utf-8"/> 
  <link rel="stylesheet" type="text/css" href="css/styles.css">
  <script src="js/functions.js"></script>
</head> 
<body> 
  <?php
    require_once("connectDB.php"); 
    $conn = connectDB(); 

    $sql_degress = "SELECT id,nom FROM `graus`";
    $sql_mentions = "SELECT id,nom FROM `mencions` WHERE grau=1";

    $result_degress = $conn->query($sql_degress);
    $result_mentions = $conn->query($sql_mentions);

    $conn->close();  
  ?>
  <section>
    <header class="fancy">
      <h3>Registra't com a alumne en un grau: </h3>        
    </header>
    <div id="formDiv">
      <p> Si us plau facilita'ns les teves dades </p>
      <form method="POST" action="register.php">
        Nom complet: <input type="text" name="name" /><br />
        Password: <input type="password" name="passwd" /><br />
        Grau: 
        <select name="degrees" id="degrees" onchange="loadMentions(this.value);">
          <?php
            if (mysqli_num_rows($result_degress) > 0)
            {
              while($row = $result_degress->fetch_assoc())
              {
                echo "<option value='".$row['id']."'>".$row['nom']."</option>";
              }
            }
            else
            {
              echo "0 results";
            }
          ?>                           
        </select>            
        <p>Tria la menció que t'atreu més:<p>
        <select name="mentions" id="mentions">
          <?php
            if (mysqli_num_rows($result_mentions) > 0)
            {
              while($row = $result_mentions->fetch_assoc())
              {
                echo "<option value='".$row['id']."'>".$row['nom']."</option>";
              }
            }
            else
            {
              echo "0 results";
            }
          ?>
        </select>
        <br />
        <br />
        <input type="submit" value="Registrar-me" >
      </form>  
    </div>                        
  </section>   
</body>
</html>

