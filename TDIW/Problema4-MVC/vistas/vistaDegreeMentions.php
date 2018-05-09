<?php
	if (mysqli_num_rows($mention) > 0)
    {
	    while($row = $mention->fetch_assoc())
	    {
	    	echo "<option value='".$row['id']."'>".$row['nom']."</option>";
	    }
	}
	else
	{
		echo "0 results";
	}
?>