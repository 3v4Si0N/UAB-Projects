<?php
	require_once("connectDB.php");  
	$conn = connectDB();

	$degree = $_GET['degree'];

	$sql_mentionsByDegree = "SELECT id,nom FROM `mencions` WHERE grau=".$degree;
	$result = $conn->query($sql_mentionsByDegree);

	
	if (mysqli_num_rows($result) > 0)
	{
		while($row = $result->fetch_assoc())
	  	{
			echo "<option value='".$row['id']."'>".$row['nom']."</option>";
		}
	}
    else
    {
      echo "0 results";
    }

	$conn->close();
?>