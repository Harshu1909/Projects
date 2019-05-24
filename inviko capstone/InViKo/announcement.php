<?php 
ob_start(); 
     session_start();
     //error_reporting(0);
     
include("conn.php"); 

  if(!$_SESSION['admin'])
  {
      header("location:index.php");
  }
            
if(isset($_REQUEST['post']))
{ 
 $role = $_REQUEST['role'];
 $announcement= $_REQUEST['announcement'];
                                           
 $q1="insert into dashboard_annoucement values('','$announcement','$role','1')";
 mysqli_query($conn,$q1) or die(mysqli_error());        
  
  
}  

$grid="select * from dashboard_annoucement where status='1'";
$execute_grid=mysqli_query($conn,$grid);   
      
?>


<!doctype html>
<html class="no-js" lang="en">


<head>
    <meta charset="UTF-8">
    <title>Announcement</title>
    <!--IE Compatibility modes-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!--Mobile first-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="img/logo1.ico"/>
    <!-- global styles-->
    <link type="text/css" rel="stylesheet" href="css/components.css"/>
    <link type="text/css" rel="stylesheet" href="css/custom.css"/>
    <!-- end fo global styles-->
    <!-- plugin styles
    <link type="text/css" rel="stylesheet" href="vendors/jasny-bootstrap/css/jasny-bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet" href="vendors/bootstrapvalidator/css/bootstrapValidator.min.css"/>
    <!--end of page level css
    <link type="text/css" rel="stylesheet" href="#" id="skin_change"/> --> 
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
<div class="bg-dark" id="wrap">
    
    
     <?php
    
    include("header.php");
    
    
    
?>
    
    
        <!-- /#top -->
    <div class="wrapper">
  <?php
    
    include("leftmenu.php");
    
    
    
?>
               <!-- #menu -->
            <ul id="menu" class="bg-blue dker">
                             
           
                  <li class="active">
                    <a href="announcement.php">
                        <i class="fa fa-user"></i>
                        <span class="link-title">&nbsp; Announcement</span>
                        </span>
                    </a>
                    
                </li>
                <li>
                    <a href="notification.php">
                        <i class="fa fa-user"></i>
                        <span class="link-title">&nbsp; Notification</span>
                        </span>
                    </a>
                    
                </li>
                

            </ul>
            <!-- /#menu -->
        </div>
          
    
    
    
        <!-- /#left -->
        <div id="content" class="bg-container">
            <header class="head">
                <div class="main-bar row">
                    <div class="col-lg-6">
                        <h4 class="nav_top_align skin_txt">
                            <i class="fa fa-user"></i>
                            Add Announcement
                        </h4>
                    </div>

                </div>
            </header>
            <div class="outer">
                <div class="inner bg-container">
                    <div class="card">

                        <div class="card-block m-t-35">
                            
                            <form class="form-horizontal login_validator" id="tryitForm" name="myform" action=""
                                  method="post" enctype='application/json'>
                                <!--<form role="form" id="tryitForm" action="add_user.html" class="form-horizontal" method="post" auete="on">-->
                                <div class="row">
                                    <div class="col-xs-12">
                                            

                                        <div class="form-group row m-t-25">
                                            <div class="col-lg-3 text-lg-right">
                                                <label for="productType" class="form-control-label">Role
                                                    *</label>
                                            </div>
                                            <div class="col-xl-6 col-lg-8">
                                                <div class="input-group">
                                    <span class="input-group-addon"> <i class="fa fa-binoculars text-primary"></i>
                                    </span>
                                    <select name="role" class="form-control">
                                                    <option value="Nurse">Nurse</option>
                                                    <option value="Doctor">Doctor</option>
                                                    <option value="Receptionist">Receptionist</option>
                                                    <option value="Patient">Patient</option>
                                                </select>
                                                </div>

                                            </div>
                                        </div>
                                        

                                        
                                        
                                          
                                            <div class="form-group row">
                                            <div class="col-lg-3 text-lg-right">
                                                <label for="Announcement" class="form-control-label">Announcement
                                                    *</label>
                                            </div>
                                            <div class="col-xl-6 col-lg-8">
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="fa fa-plus text-primary"></i></span>
                                                    <textarea rows="5" cols="50" placeholder=" "  id="announcement" name="announcement" class="form-control">
                                                    </textarea>
                                                </div>
                                            </div>
                                        </div>
                                                    
                                        <div class="form-group row">
                                            <div class="col-lg-9 push-lg-3">
                                               
                                             
                                                
                                                       <button class="btn btn-primary" type="submit" name="post">
                                                    <i class="fa fa-user"></i>
                                                    Post
                                                </button>
                                                
                                                 
                                                
                                                <button class="btn btn-warning" type="reset" id="clear">
                                                    <i class="fa fa-refresh"></i>
                                                    Reset
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    
                                    
                                                                            
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <!-- /.inner -->
            </div>
            <!-- /.outer -->
                            <div class="outer">
                    <div class="inner bg-container">
                        <div class="card">
                            <div class="card-header bg-white">
                                Announcement Grid
                            </div>
                            <div class="card-block m-t-35" id="user_body">
                                <div class="table-toolbar">
                                    <div class="btn-group float-xs-right users_grid_tools">
                                        <div class="tools"></div>
                                    </div>
                                </div>
                                <div>
                                    <div>
                                    
                                    
                                        <table class="table  table-striped table-bordered table-hover dataTable no-footer" id="editable_table" role="grid">
                                            <thead>
                                            
                                                <tr role="row">                                                                 
                                                    <th class="sorting wid-25" tabindex="0" rowspan="1" colspan="1">Announcement</th>
                                                    <th class="sorting wid-10" tabindex="0" rowspan="1" colspan="1">Role</th>                                                  
                                                    <th class="sorting wid-10" tabindex="0" rowspan="1" colspan="1">Actions</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            
                                            <?php 
                                    while($f=mysqli_fetch_array($execute_grid))
                                    {                         
                                                                          
                                    ?>
                                            <tr role="row" class="even">
                                                                                           
                                            <td><?php echo $f[1]; ?></td>
                                           <td><?php echo $f[2]; ?></td>    
                                            <td>
                                            <a class="delete hidden-xs hidden-sm" data-toggle="tooltip" 
                                            data-placement="top" title="Delete" href="delete.php?adelete=<?php echo $f[0];?>">
                                            <i class="fa fa-trash text-danger"></i></a></td>
                                              
                                        <?php  } ?>    
                                            </tbody>
                                        </table>                                                                                                                  
                                    </div>
                                </div>
                                <!-- END EXAMPLE TABLE PORTLET-->
                            </div>
                        </div>
                    </div>
                    <!-- /.inner -->
                </div>
            <!-- # right side -->
        </div>
        <!-- /#content -->
    </div>
    <!--wrapper-->
    
     
   <?php
    
   // include("sidebar.php");
    
    
    
?>
 
 
   
   
        <!-- # right side -->
    </div>
    
    
<!-- /#wrap -->

<!-- global scripts-->
<script type="text/javascript" src="js/components.js"></script>
<script type="text/javascript" src="js/custom.js"></script>
<!-- end of global scripts-->
<!-- plugin scripts-->
<script type="text/javascript" src="js/pluginjs/jasny-bootstrap.js"></script>             
<!-- end of plugin scripts-->
<script type="text/javascript" src="js/pages/validation.js"></script>
</body>
                                                                                                                                       
</html>