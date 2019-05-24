<?php
 include("conn.php");
  
 if(isset($_REQUEST['adelete']))
   { 
 $k=$_REQUEST['adelete'];
 mysqli_query($conn,"update dashboard_annoucement set status='0' where dasboard_annouce_id='$k'");
 header("location:announcement.php"); 
   }
   
 if(isset($_REQUEST['ndelete']))
   { 
 $k=$_REQUEST['ndelete'];
 mysqli_query($conn,"update dashboard_notification set status='0' where dasboard_notify_id='$k'");
 header("location:notification.php"); 
   }
   
  
?>
