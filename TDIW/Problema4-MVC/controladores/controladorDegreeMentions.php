<?php
	require("../connectDB.php");
	$conn = connectDB();
	require("../modelo/consultas.php");
	$mention = getMentionsByDegree($conn);
	$conn->close();
    require("../vistas/vistaDegreeMentions.php");
?>