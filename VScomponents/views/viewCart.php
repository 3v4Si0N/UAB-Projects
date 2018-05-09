<div id="updateQuantity">
<?php
    error_reporting(0);
    // Tratamos aqui cualquier mensaje de error.
    echo $messageError;
    if(count($_SESSION["detalle"])>0)
    {
?>
       <table class="table table-condensed">
            <thead>
                <tr>
                    <th class="text-center">Product</th>
                    <th class="text-center">Quantity</th>
                    <th class="text-center">Price</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
<?php
                // $sessionDetalle = $_SESSION['detalle']
                foreach($_SESSION["detalle"] as $k => $detalle)
                {
?>
                    <tr>
                        <td>
                            <h5><?php echo $detalle['name']; ?></h5>
                        </td>
                        <td>
                            <input type="number" name="<?php echo $detalle['id']; ?>" id="quantity" class="form-control text-center btn-update-quantity" value="<?php echo $detalle['quantity']; ?>" onblur="updateQuantity(this.value, this.name)" min="1" max="10">
                        </td>

                        <td class="text-center">
                        <?php echo $detalle['price']; ?>€
                        </td>
                        <td>
                            <center>
                                <button class="btn-eliminar-producto" value="<?php echo $detalle['id']; ?>" onclick="productToCart(2,this.value)">
                                    <span class="glyphicon glyphicon-trash" style="color:#d9534f;"></span>
                                </button>
                            </center>
                        </td>
                    </tr>
                    <?php $totalPrice += $detalle['price'] * $detalle['quantity']; ?>
<?php
                }
?>
            </tbody>
            <tfoot>
                <tr>
                    <td><a href="/VScomponents/buy" class="btn btn-success" onload="getURL();">Make the purchase</a></td>
                    <td></td>
                    <td class="text-center"><strong>Total <?php echo $totalPrice; ?>€</strong></td>
                    <td><input type="button" class="btn btn-warning btn-block btn-vaciar-carrito" value="Cancel" onclick="cancelOrder()"></td>
                </tr>
            </tfoot>
        </table>
<?php
    }
    else
    {
?>
        <text> There are no products</text>
<?php
    }
?>
</div>
