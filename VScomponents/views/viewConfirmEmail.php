<?php
    if (filter_var($value, FILTER_VALIDATE_EMAIL))
    {
?>
        <div class="form-group has-success has-feedback">
            <div class="col-xs-offset-2 col-xs-8 col-sm-offset-2 col-sm-8 col-md-offset-2 col-md-8 col-lg-offset-2 col-lg-8">
                <input type="text" class="form-control" name="email" id="email" value="<?php echo $value ?>" onblur="validateInputField('email', this.value, 'confirmEmail')">
                <span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>
            </div>
        </div>
<?php
    } else {
?>
        <div class="form-group has-error has-feedback">
            <div class="col-xs-offset-2 col-xs-8 col-sm-offset-2 col-sm-8 col-md-offset-2 col-md-8 col-lg-offset-2 col-lg-8">
                <input type="text" class="form-control" name="email" id="email" value="<?php echo $value ?>" onblur="validateInputField('email', this.value, 'confirmEmail')">
                <span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
            </div>
        </div>
<?php
    }
?>