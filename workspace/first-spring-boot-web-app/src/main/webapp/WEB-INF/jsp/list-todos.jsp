<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<h1>Todos for ${user}</h1>
	<table class="table table-striped">
		<caption>Your Todos are:</caption>
		<thead>
			<tr>
				<th>Description</th>
				<th>Target Date</th>
				<th>Is it done?</th>
				<th>Update</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>
			<!-- jstl for loops -->
			<c:forEach items="${todos}" var="todo">
				<tr>
					<td>${todo.description}</td>
					<td>
						<!-- fmt:parseDate works with java.util.Date but not with with java.time.LocalDate
					     first, I need to parse date and then change format --> 
						<fmt:parseDate value="${todo.targetDate}" type="date" pattern="yyyy-MM-dd" var="parsedDate" /> 
						<fmt:formatDate value="${parsedDate}" type="date" pattern="dd/MM/yyyy" var="targetDateFormatted" />
						${targetDateFormatted}
					</td>
					<td>${todo.done}</td>
					<td>
						<a type="button" class="btn btn-success" href="update-todo?id=${todo.id}">Update</a>
					</td>
					<td>
						<a type="button" class="btn btn-warning" href="delete-todo?id=${todo.id}">Delete</a>
					</td>
				</tr>
			</c:forEach>			
		</tbody>
	</table>
	
	<div>
		<button class="button">
			<a href="/add-todo">Add Todo</a>
		</button>
	</div>
	
</div>

<%@ include file="common/footer.jspf" %>