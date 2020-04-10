<html>
	<head>
		<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
		<title>Add/Update Todo</title>
	</head>
	
	<body>	
		<!--  webjars -->
		<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
		<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	
		<div class="container">
			<h1>Todo for ${user}</h1>		
			<form method="post">
				<fieldset class="form-group">
					<label>Description:</label>
					<input type="text" name="description" 
					       class="form-control" required="required"/>
				</fieldset>
				 
				<button type="submit" class="btn btn-success">Add</button>
			</form>
		</div>
	</body>
</html>