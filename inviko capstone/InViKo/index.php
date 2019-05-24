<?php
ob_start();
           session_start();   
include("conn.php");   

if(!empty($_SESSION['admin']))
 {
      header("location:announcement.php");
  }   

if(isset($_REQUEST['l']))
{
    

 $auser= $_REQUEST['username'];   
 $apass= $_REQUEST['password'];   
 
  $q2=mysqli_query($conn,"select * from admin where aname='$auser' AND apass='$apass'");
  
  
  $rows = mysqli_num_rows($q2);
  
  if ($rows == 1) {
 $f=mysqli_fetch_array($q2); 
           
$_SESSION['admin']=$f[1];      
header("location:announcement.php");
        
    
  }
     
       
       else 
    {
?>


 <script>

    alert("Username or Password is invalid!");
</script>
 <?php 
  
  }
}                
        


     


?>


<!DOCTYPE html>
  <html>


<head>
    <title>Login | Admin</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="shortcut icon" href="img/logo1.ico"/>
    <!--Global styles -->
    <link type="text/css" rel="stylesheet" href="css/components.css"/>
    <link type="text/css" rel="stylesheet" href="css/custom.css"/>
    <!--End of Global styles -->
    <!--Plugin styles-->
    <link type="text/css" rel="stylesheet" href="vendors/bootstrapvalidator/css/bootstrapValidator.min.css"/>
    <link type="text/css" rel="stylesheet" href="vendors/wow/css/animate.css"/>
    <!--End of Plugin styles-->
    <link type="text/css" rel="stylesheet" href="css/pages/login.css"/>
</head>
<body>
<div class="preloader" style=" position: fixed;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  z-index: 100000;
  backface-visibility: hidden;
  background: #ffffff;">
    <div class="preloader_img" style="width: 200px;
  height: 200px;
  position: absolute;
  left: 48%;
  top: 48%;
  background-position: center;
z-index: 999999">
        <img src="img/loader.gif" style=" width: 40px;" alt="loading...">
    </div>
</div>
<div class="container wow fadeInDown" data-wow-delay="0.5s" data-wow-duration="2s">
    <div class="row">
        <div class="col-lg-8 push-lg-2 col-md-10 push-md-1 col-sm-10 push-sm-1 login_top_bottom">
            <div class="row">
                <div class="col-lg-8 push-lg-2 col-md-10 push-md-1 col-sm-12">
                    <div class="login_logo login_border_radius1">
                       <h3 class="text-xs-center" style="padding-top: 3%;">
                           <span class="text-white"  >InViKo &nbsp;|
                                Login</span>
                        </h3>
                    </div>
                    <div class="bg-white login_content login_border_radius">
                        <form action="" method="post" class="login_validator">
                            <div class="form-group">
                                <label class="form-control-label"> User Name</label>
                                <div class="input-group">
                                    
                                    <input type="text" class="form-control  form-control-md" name="username" placeholder="User Name">
                                </div>
                            </div>
                            <!--</h3>-->
                            <div class="form-group">
                                <label class="form-control-label">Password</label>
                                <div class="input-group">
                                   
                                    <input type="password" class="form-control form-control-md" name="password" placeholder="Password">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="row">
                                    <div class="col-lg-12">
                                        <input type="submit" name="l" value="Log In" class="btn btn-primary btn-block login_button">
                                    </div>
                                </div>
                            </div>
                        </form>

                      
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- global js -->
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/tether.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<!-- end of global js-->
<!--Plugin js-->
<script type="text/javascript" src="vendors/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="vendors/wow/js/wow.min.js"></script>
<!--End of plugin js-->
<script type="text/javascript" src="js/pages/login.js"></script>
</body>



</html>
