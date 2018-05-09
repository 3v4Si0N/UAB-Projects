<?php
  echo $messageError;
?>
<form method="POST" enctype="multipart/form-data" action="create/product" id="formPost" class="form-horizontal col-xs-offset-1 col-xs-10 col-sm-offset-2 col-sm-8 col-md-offset-2 col-md-8 col-lg-offset-2 col-lg-8 thumbnail">
    <h4 class="col-xs-offset-2 col-xs-8 col-sm-offset-2 col-sm-8 col-md-offset-2 col-md-8 col-lg-offset-2 col-lg-8 text-center h4-register">Create new product</h4>
    <h5 class="col-xs-offset-2 col-xs-8 col-sm-offset-2 col-sm-8 col-md-offset-2 col-md-8 col-lg-offset-2 col-lg-8 text-left text-danger">* required field.</h5>

    <!--
        sub_category_id
        name
        specs
        characteristics
        stock
        price
        product_image
        brand_image
     -->

     <div class="form-group">
        <div class="col-xs-offset-2 col-xs-8 col-sm-offset-2 col-sm-8 col-md-offset-2 col-md-8 col-lg-offset-2 col-lg-8">
            <select class="form-control" name="category" id="category" onchange="loadSubcategory(this.value);">
                <option hidden>* Select category product</option>
<?php
                foreach($categories as $row_category)
                {
?>
                   <option value="<?php echo $row_category['id']; ?>"><?php echo $row_category['name']; ?></option>
<?php
                }
?>
            </select>
        </div>
    </div>

    <div class="form-group">
        <div class="col-xs-offset-2 col-xs-8 col-sm-offset-2 col-sm-8 col-md-offset-2 col-md-8 col-lg-offset-2 col-lg-8">
            <select class="form-control" name="subcategory" id="subcategory">
                <option hidden>* Select subcategory product</option>
<?php
                foreach($subcategories as $row_subcategory)
                {
?>
                   <option value="<?php echo $row_subcategory['id'] ?>"><?php echo $row_subcategory['name'] ?></option>
<?php
                }
?>
            </select>
        </div>
    </div>

    <div class="form-group">
        <div class="col-xs-offset-2 col-xs-8 col-sm-offset-2 col-sm-8 col-md-offset-2 col-md-8 col-lg-offset-2 col-lg-8">
            <input type="text" class="form-control" name="name" id="name" placeholder="* Name" required="true">
        </div>
    </div>

    <div class="form-group">
        <div class="col-xs-offset-2 col-xs-8 col-sm-offset-2 col-sm-8 col-md-offset-2 col-md-8 col-lg-offset-2 col-lg-8">
            <input type="text" class="form-control" name="brandName" id="brandName" placeholder="Brand Name">
        </div>
    </div>

   <div class="form-group">
        <div class="col-xs-offset-2 col-xs-8 col-sm-offset-2 col-sm-8 col-md-offset-2 col-md-8 col-lg-offset-2 col-lg-8">
            <input type="text" class="form-control" name="specs" id="specs" placeholder="Specifications" placeholder="Specifications">
        </div>
    </div>

    <div class="form-group">
        <div class="col-xs-offset-2 col-xs-8 col-sm-offset-2 col-sm-8 col-md-offset-2 col-md-8 col-lg-offset-2 col-lg-8">
            <input type="text" class="form-control" name="characs" id="characs" placeholder="Characteristics">
        </div>
    </div>


    <div id="confirmEmail">
        <div class="form-group">
            <div class="col-xs-offset-2 col-xs-8 col-sm-offset-2 col-sm-8 col-md-offset-2 col-md-8 col-lg-offset-2 col-lg-8">
                <input type="text" class="form-control" name="stock" id="stock" placeholder="* Stock" required="true">
            </div>
        </div>
   </div>

    <div class="form-group">
        <div class="col-xs-offset-2 col-xs-8 col-sm-offset-2 col-sm-8 col-md-offset-2 col-md-8 col-lg-offset-2 col-lg-8">
            <input type="text" class="form-control" name="price" id="price" placeholder="* Price" required="true">
        </div>
    </div>

    <div class="form-group">
        <div class="col-xs-offset-2 col-xs-8 col-sm-offset-2 col-sm-8 col-md-offset-2 col-md-8 col-lg-offset-2 col-lg-8">
            <input type="file" name="productImage" id="productImage" required="true"><text class="text-danger">* Product image</text>
        </div>
    </div>

    <div class="form-group">
        <div class="col-xs-offset-2 col-xs-8 col-sm-offset-2 col-sm-8 col-md-offset-2 col-md-8 col-lg-offset-2 col-lg-8">
            <input type="file" name="brandImage" id="brandImage"><text class="text-danger">* Brand image</text>
        </div>
    </div>

    <div class="form-group">
        <div class="col-xs-offset-2 col-xs-10 col-sm-offset-2 col-sm-10">
            <input type="submit" class="btn btn-success" value="Create product">
        </div>
    </div>

    <div class="form-group">
        <div class="col-xs-offset-2 col-xs-8 col-sm-offset-2 col-sm-8">
            <div id="confirmation">

            </div>
        </div>
    </div>
</form>
