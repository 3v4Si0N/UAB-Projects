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
				if (mysqli_num_rows($degree) > 0)
			    {
					while($row = $degree->fetch_assoc())
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
				require("vistas/vistaDegreeMentions.php");            
			?>
        </select>
        <br />
        <br />
        <input type="submit" value="Registrar-me" >
      </form>  
    </div>                        
</section>