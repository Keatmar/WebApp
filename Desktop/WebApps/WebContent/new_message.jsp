<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@page
	import="java.sql.*,java.io.InputStream,java.io.ByteArrayInputStream,javax.imageio.ImageIO,java.awt.image.BufferedImage,java.io.File"%>
<%@page import="java.util.*,model.User"%>
<html>
<head>
 


<title>Messages</title>

</head>
<body background="./resources/images/background.jpg">

	<!-- 	Menu Bar	 -->
	<%@ include file="./menu_bar.jsp"%>

	
	<%
		User receiver = (User) request.getAttribute("receiver");
	%>
		<div class="row ">
		 <div class="col-md-9 col-md-offset-2 vcenter" style="display: inline-block; vertical-align: middle;">
          <div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Compose New Message</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <div class="form-group">
                <input id="receiver_input" class="form-control" value="<%= receiver.getEmail() %>" placeholder="<%= receiver.getEmail()%>or <%=receiver.getUsername() %>"
                 		data-parsley-trigger="change" data-parsley-type="email">
              </div>
              <div class="form-group">
                <input id="subject_text" class="form-control" placeholder="Subject:">
              </div>
              <div class="form-group">
                    <textarea id="compose-textarea" class="form-control" style="height: 300px">
                      
                    </textarea>
              </div>
              <div class="form-group">
                <div class="btn btn-default btn-file">
                  <i class="fa fa-paperclip"></i> Attachment
                  <input type="file" name="attachment">
                </div>
                <p class="help-block">Max. 32MB</p>
              </div>
            </div>
            <!-- /.box-body -->
            <div class="box-footer">
              <div class="pull-right">
                <button type="button" class="btn btn-default"><i class="fa fa-pencil"></i> Draft</button>
                <button type="submit" class="btn btn-primary" id="submit_button"><i class="fa fa-envelope-o"></i> Send</button>
              </div>
              <button type="reset" class="btn btn-default" ><i class="fa fa-times"></i> Discard</button>
            </div>
            <!-- /.box-footer -->
          </div>
          <!-- /. box -->
        </div>
        <!-- /.col -->
	</div>

			
				
				
<!-- jQuery 2.2.3 -->
<script src="./resources/adminLte/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="./resources/bootstrap/js/bootstrap.min.js"></script>

<!-- FastClick -->
<script src="./resources/adminLte/plugins/fastclick/fastclick.js"></script>
				
<!-- Bootstrap WYSIHTML5 -->
<script src="./resources/adminLte/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>				
<!-- AdminLTE App -->
<!-- <script src="./resources/adminLte/dist/js/app.min.js"></script> -->

<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/parsley.js/2.2.0-rc4/parsley.min.js"></script>

<script type="text/javascript">
  $(document).ready(function () {
    // Replace the <textarea id="editor1"> with a CKEditor
    // instance, using default configuration.
    //bootstrap WYSIHTML5 - text editor
    $("#compose-textarea").wysihtml5();
    $("#submit_button").on("click", function(event){
    	// Get the text from the two inputs.
    	var receiver_input = $("#receiver_input").val();
    	var subject = $("#subject_text").val();
    	var message_text = $("#compose-textarea").val();
    	
    	if (receiver_input === "") {
            alert("Please Insert an email Address");
            return;
        }
    	
    	if (message_text === "") {
            alert("Please Insert an a text");
            return;
        }
    	
    	$.post('MessagingServlet',{"receiver_input": receiver_input, "subject": subject,"message_text":message_text},
	           function() { // on success
	               alert("Your Message has been sended successfully!");
	               location.href ='./GetUsersAuctions';
	           })
	           .fail(function() { //on failure
	               alert("Wrong email or userame submited.");
	    	   });
    });
  });
</script>
</body>
</html>

