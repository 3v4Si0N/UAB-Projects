<div class="form-group">
    <div class="col-xs-offset-2 col-xs-8 col-sm-offset-2 col-sm-8 col-md-offset-2 col-md-8 col-lg-offset-2 col-lg-8">
        <label for="specs" class="control-label">Specifications</label>
        <textarea class="form-control" name="specs" id="specs" rows="9"><?php echo $specs; ?></textarea>
    </div>
</div>

<div class="form-group">
    <div class="col-xs-offset-2 col-xs-8 col-sm-offset-2 col-sm-8 col-md-offset-2 col-md-8 col-lg-offset-2 col-lg-8">
        <label for="characs" class="control-label">Characteristics</label>
        <textarea class="form-control" name="characs" id="characs" rows="9"><?php echo $characteristics; ?></textarea>
    </div>
</div>


<div id="confirmEmail">
    <div class="form-group">
        <div class="col-xs-offset-2 col-xs-8 col-sm-offset-2 col-sm-8 col-md-offset-2 col-md-8 col-lg-offset-2 col-lg-8">
            <label for="stock" class="control-label">Stock</label>
            <input type="text" class="form-control" name="stock" id="stock" value="<?php echo $stock; ?>" required="true">
        </div>
    </div>
</div>

<div class="form-group">
    <div class="col-xs-offset-2 col-xs-8 col-sm-offset-2 col-sm-8 col-md-offset-2 col-md-8 col-lg-offset-2 col-lg-8">
        <label for="price" class="control-label">Price</label>
        <input type="text" class="form-control" name="price" id="price" value="<?php echo $price; ?>" required="true">
    </div>
</div>

<div class="form-group">
    <div class="col-xs-offset-2 col-xs-10 col-sm-offset-2 col-sm-10">
        <input type="submit" class="btn btn-success" value="Edit product" onclick="editOneProduct()">
    </div>
</div>

<div class="form-group">
    <div class="col-xs-offset-2 col-xs-8 col-sm-offset-2 col-sm-8">
        <div id="confirmation">

        </div>
    </div>
</div>