<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="./resources/bootstrap/css/bootstrap.min.css">
<style>
.container .row .toppad{
	margin-top: 50px;
}
</style>
<title>Import-Export</title>
</head>
<body background="./resources/images/background.jpg">

	<!-- 	Menu Bar	 -->
	<%@ include file="./menu_bar.jsp"%>

	<div class="container ">
		<div class="row">
			<div
				class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">
				<div class="panel panel-info">
					<div class="panel-heading">
						<div class="col-md-offset-4">
							<h3 class="panel-title">Import-Export</h3>
						</div>
					</div>
					<div class="panel-body">
						<div class=" col-md-9">
							<form action="./Import_Export" method="post"  enctype="multipart/form-data">
								<table class="table table-user-information">
									<tbody>
										<tr>
											<td><h4>Import File</h4></td>
											<td><input class="btn btn-primary" type="file"
												name="Import" /></td>
										</tr>
										<tr>
											<td><h4>Export File</h4></td>
											<td><input type="file" class="btn btn-primary"
												name="Export" /></td>


										</tr>

									</tbody>
								</table>
								<div class="col-md-8 col-md-offset-4">
									<input type="submit" class="btn btn-block btn-success"
										value="Accept" />
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>