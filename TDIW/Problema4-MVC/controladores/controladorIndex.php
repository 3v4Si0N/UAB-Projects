<?php
	require("connectDB.php");
	require("modelo/consultas.php");
	$conn = connectDB();
	$mention = getMentionsByDegree($conn);
	$degree = getDegrees($conn);
	$mention = getMentions($conn);
	$conn->close();
    require("vistas/vistaIndex.php");
?>