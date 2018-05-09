<?php
	function getMentionsByDegree($conn)
	{
		error_reporting(0);
		$degree = $_GET['degree'];

		$sql_mentionsByDegree = "SELECT id,nom FROM `mencions` WHERE grau=".$degree;
		$result = $conn->query($sql_mentionsByDegree);
	    return $result;
	}

	function getDegrees($conn)
	{
	    $sql_degress = "SELECT id,nom FROM `graus`";
	    $result_degress = $conn->query($sql_degress);
	    return $result_degress;
	}

	function getMentions($conn)
	{
	    $sql_mentions = "SELECT id,nom FROM `mencions` WHERE grau=1";
	    $result_mentions = $conn->query($sql_mentions);     
	    return $result_mentions;
	}
?>