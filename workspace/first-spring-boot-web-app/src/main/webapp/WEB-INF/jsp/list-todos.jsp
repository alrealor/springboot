<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>List of Todos</title>
		<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">			
	</head>
	
	<body>
		<div class="container">
			<h1>Todos for ${user}</h1>
			<table class="table table-striped">
				<caption>Your Todos are:</caption>
				<thead>
					<tr>
						<th>Description</th>
						<th>Target Date</th>
						<th>Is it done?</th>
						<th>Delete</th>
					</tr>
				</thead>
				<tbody>
					<!-- jstl for loops -->
					<c:forEach items="${todos}" var="todo">
						<tr>
							<td>${todo.description}</td>
							<td>${todo.targetDate}</td>
							<td>${todo.done}</td>
							<td><a type="button" class="btn btn-warning" href="delete-todo?id=${todo.id}">Delete</a></td>
						</tr>
					</c:forEach>
	
				</tbody>
			</table>
	
			<br>
			<div>
				<button class="button"><a href="/add-todo">Add Todo</a></button>
			</div>
	
			<!--  webjars -->
			<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
			<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
		</div>
	</body>
</html>