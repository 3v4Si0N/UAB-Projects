<?php
    echo $messageError;
    if($categoryProducts != 0)
    {
        foreach($categoryProducts as $row)
        {
            if($num == 0)
            {
        ?>
                <div class="row">
<?php
            }
            if($num < 4)
            {
?>
                <article class="col-xs-12 col-sm-6 col-md-6 col-lg-3">
                    <div class="thumbnail">
                        <a href="#"><img src="<?php echo $row['product_image'];?>" alt="<?php echo $row["id"]; ?>" class="img-responsive" onclick="loadProduct(this.alt)" width="220" height="220"></a>
                        <div class="caption">
                            <h4><?php echo $row["name"];?></h4>
                            <p><?php echo $row["price"]." €";?></p>
                            <button type="button" class="btn btn-success btn-sm" value="<?php echo $row["id"]; ?>" onclick="productToCart(1, this.value);">Add</button>
                        </div>
                    </div>
                </article>
<?php
            }
            $num++;
            if($num == 4)
            {
                $num = 0;
?>
                </div>
<?php
            }
        }
    }
?>
