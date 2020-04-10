<html>
	<head>
		<title>MY First JSP in SpringBoot</title>
	</head>
	<body>
		<div style="color:red">${errorMessage}</div>
		<form method="post">
			Name: <input type="text" name="user"/>
			Password: <input type="password" name="password" />
			<input type="submit" />
		</form>		
	</body>
</html>