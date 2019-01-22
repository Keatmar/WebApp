<%@page
	import="java.sql.*,java.io.InputStream,java.io.ByteArrayInputStream,javax.imageio.ImageIO,java.awt.image.BufferedImage,java.io.File"%>
<!DOCTYPE html>
<%@page import="java.util.*,model.Message"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title><% 
			String type = (String)request.getAttribute("type");	
			if(type.contains("Inbox")){  %>
			Inbox Messages
		<% }else{ %>
			Sended Messages
		<%} %>
</title>
</head>

<body background="./resources/images/background.jpg">
<!-- 	Menu Bar	 -->
	<%@ include file="./menu_bar.jsp"%>
	
	<div class="row">	
		<div class="col-xs-8 col-xs-offset-2">
			<% 
				List<Message> user_message_list = new ArrayList<Message>();
				if(type.contains("Inbox")){
					user_message_list = (List<Message>) request.getAttribute("users_inbox_messages");
				}else{
					user_message_list = (List<Message>) request.getAttribute("users_sended_messages");
				}
	         	if (!user_message_list.isEmpty()) { 
	         %>
	          <div class="box ">
	            <div class="box-header">
	              <h3 class="box-title">Message Bid Table</h3>
	
	              <div class="box-tools">
	                <div class="input-group input-group-sm" style="width: 150px;">
	                  <input name="table_search" class="form-control pull-right" placeholder="Search" type="text">
	
	                  <div class="input-group-btn">
	                    <button type="submit" class="btn btn-default"><i class="fa fa-search"></i></button>
	                  </div>
	                </div>
	              </div>
	            </div>
	            <!-- /.box-header -->
<!-- 	            <div class="box-body table-responsive no-padding"> -->
	              <table id="user_messages" class="table table-bordered table-striped">
	                <tbody><tr>
	                  <th>ID</th>
	                  <% if(type.contains("Inbox")){  %>
	                  	<th>Sender</th>
	                  <% }else{ %>
	                  	<th>Send To</th>
	                  <%} %>
	                  <th>Date_created</th>
	                  <th>Subject</th>
	                  <th>Message_Text</th>
	                  <th>New Message</th>
	                  <th>Delete</th>
	                 
	                </tr>
	                
	                  <%		
	                  		for(Message message : user_message_list){ 
	                  %>
	                  <tr>
	                  <td><%= message.getId()%></td>
	                   <% if(type.contains("Inbox")){  %>
	                  	 <td><span class="label label-success"><%= message.getSender().getUsername()%></span></td>
	                  <% }else{ %>
	                  	 <td><span class="label label-success"><%= message.getReceiver().getUsername()%></span></td>
	                  <%} %>
	                 
	                  <td><%= message.getDateCreated()%></td>
	                  <td><%= message.getSubject()%></td>
	                  <td><%= message.getText()%></td>
	                  <td>
	                  	<form id="send_message" class="form-horizontal " role="form" method="get" action="./New_Message" >
		      				<input type="hidden" name="receiver_id" 
      						    <% if(type.contains("Inbox")){  %>
      						   		value="<%= message.getSender().getId() %>" >
      						     <% }else{ %>
                 					value="<%= message.getReceiver().getId() %>" >
                  				<%} %>
		      				<button type="submit" class="btn btn-primary" form="send_message">Send New Message</button>
						</form>
	                  </td>
	                  <td>
	                  	<form id="delete_message" class="form-horizontal " role="form"  >
		      				<input type="hidden" id="message_id"  						   
      						   	value="<%= message.getId() %>" >     				
		      				<button type="button" id="" class="btn btn-danger" data-toggle="modal" data-target="#DeleteModal">Delete</button>
		      				
		      				<div class="example-modal" >
					        <div class="modal modal-danger" id="DeleteModal">
					          <div class="modal-dialog">
					            <div class="modal-content">
					              <div class="modal-header">
					                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
					                  <span aria-hidden="true">×</span></button>
					                <h4 class="modal-title">DELETE MESSAGE-<%=message.getId() %></h4>
					              </div>
					              <div class="modal-body">
					                <p>You are about to Delete a Message of yours. Are you sure that you want to Continue?</p>
					              </div>
					              <div class="modal-footer">
					                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
					                <button type="button" data-message-id="<%= message.getId() %>" id="delete_button" class="btn btn-warning" data-dismiss="modal">Delete</button>
					              </div>
					            </div>
					            <!-- /.modal-content -->
					          </div>
					          <!-- /.modal-dialog -->
					        </div>
					        <!-- /.modal -->
					      </div>
						</form>
	                  </td>
	                  </tr>
	                  	
	
	                  <% } %>
	                
	               
	              </tbody></table>
<!-- 	            </div> -->
	            <!-- /.box-body -->
	          </div>
	          <!-- /.box -->
	        
		
		<% } 
			else{ %>
				<div class="col-xs-6 col-xs-offset-3">
				<h3 style="text-align: center; color: #ffff;"><strong><em>User Has NO Messages</em></strong></h3>
				</div>
		<%} %>
		</div>
	</div>
	

	<!-- jQuery 2.2.3 -->
	<script src="./resources/adminLte/plugins/jQuery/jquery-2.2.3.min.js"></script>
	<!-- Bootstrap 3.3.6 -->
	<script src="./resources/bootstrap/js/bootstrap.min.js"></script>
		<!-- DataTables -->
	<script src="./resources/adminLte/plugins/datatables/jquery.dataTables.min.js"></script>
	<script src="./resources/adminLte/plugins/datatables/dataTables.bootstrap.min.js"></script>
	
	<script type="text/javascript">
	
		$(document).ready(function(){
			$("#delete_button").on('click',function(e){
				
				var message_id = $(this).attr("data-message-id");
				console.log(message_id);
				$.post('DeleteMessage',{"message_id":message_id},
					function() { // on success
							alert("The Message has been deleted successfuly");
							window.location.reload(true);
			           })
			           .fail(function() { //on failure
			               alert("Error on deleting Message");
			   	});
			});
		});
	</script>
	
</body>
</html>