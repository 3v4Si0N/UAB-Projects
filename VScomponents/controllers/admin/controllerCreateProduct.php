<?php

    if($_POST)
    {
        $messageToUser = 'The process has been completed <strong>successfully</strong>';
        $uploadOk      = 1;

        $subcategory = $_POST['subcategory'];
        $name        = $_POST['name'];
        $specs       = $_POST['specs'];
        $characs     = $_POST['characs'];
        $stock       = $_POST['stock'];
        $price       = $_POST['price'];
        $brandName   = $_POST['brandName'];
        //$brandImage = "../../images/aaa.jpg";
        //$productImage = "../../images/aaaaa.jpg";

        /*
        $subcategory = filter_var($subcategory, FILTER_VALIDATE_INT);
        $name = filter_var($name, FILTER_SANITIZE_SPECIAL_CHARS);
        $specs = filter_var($specs, FILTER_VALIDATE_STRING);
        $characs = filter_var($characs, FILTER_VALIDATE_STRING);
        $stock = filter_var($stock, FILTER_VALIDATE_INT);
        $price = filter_var($price, FILTER_VALIDATE_INT);
        $productImage = filter_var($productImage, FILTER_VALIDATE_STRING);
        $brandImage = filter_var($brandImage, FILTER_VALIDATE_STRING);
        */

        if($subcategory && $name && $stock && $price)
        {
            $target_dir   = "images/";
            $target_file  = $target_dir . basename($_FILES["productImage"]["name"]);
            $target_file2 = $target_dir . basename($_FILES["brandImage"]["name"]);

            //$uploadOk = 1;
            $imageFileType  = pathinfo($target_file,PATHINFO_EXTENSION);
            $imageFileType2 = pathinfo($target_file2,PATHINFO_EXTENSION);
            // Check if image file is a actual image or fake image
            if(isset($_POST["submit"])) {
                $check  = getimagesize($_FILES["productImage"]["tmp_name"]);
                $check2 = getimagesize($_FILES["brandImage"]["tmp_name"]);
                if($check !== false || $check2 !== false) {
                    //$messageSuccess = "File/s is/are an image - " . $check["mime"] . ".";
                    //$uploadOk     = 1;
                } else {
                    $messageToUser = "File/s is/are not an image.";
                    $uploadOk = 0;
                }
            }
            // Check if file already exists
            if (file_exists($target_file) || file_exists($target_file2)) {
                $messageToUser = "Sorry, file/s already exists.";
                $uploadOk = 0;
            }
            // Check file size
            if (($_FILES["productImage"]["size"] > 500000) || ($_FILES["brandImage"]["size"] > 500000)) {
                $messageToUser = "Sorry, your file/s is/are too large.";
                $uploadOk = 0;
            }
            // Allow certain file formats
            if($imageFileType != "jpg" && $imageFileType2 != "jpg" && $imageFileType != "png"
            && $imageFileType2 != "png" && $imageFileType != "jpeg" && $imageFileType2 != "jpeg"
            && $imageFileType != "gif" && $imageFileType2 != "gif") {
                $messageToUser = "Sorry, only JPG, JPEG, PNG & GIF files are allowed.";
                $uploadOk = 0;
            }
            // Check if $uploadOk is set to 0 by an error
            if ($uploadOk == 0) {
                $messageError = "Sorry, your file was not uploaded.";
            // if everything is ok, try to upload file
            } else {
                if (move_uploaded_file($_FILES["productImage"]["tmp_name"], $target_file)
                && move_uploaded_file($_FILES["brandImage"]["tmp_name"], $target_file2)) {
                    //$messageError = "The file " . basename( $_FILES["productImage"]["name"]) . " and the file " . basename( $_FILES["brandImage"]["name"]) . " has been uploaded.";
                } else {
                    $messageToUser = "Sorry, there was an error uploading your file.";
                }
            }

            $productImage = "images/" . basename($_FILES["productImage"]["name"]);
            $brandImage   = "images/" . basename($_FILES["brandImage"]["name"]);

            require("model/model.php");
            $stmt = $DB->insertNewProduct($subcategory, $name, $specs, $characs, $stock, $price, $productImage, $brandImage, $brandName);

            $DB->closeConnectionDB();
            require("views/viewConfirmation.php");
        }
        else
        {
            require("views/viewWrongField.html");
        }
    }
    else
    {
        require("views/viewForbidden.html");
    }

?>
