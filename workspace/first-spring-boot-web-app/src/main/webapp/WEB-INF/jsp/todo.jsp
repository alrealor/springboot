<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf"%>

<div class="container">

	<h1>Todo for ${user}</h1>

	<form:form method="post" modelAttribute="todo">
		<!-- We need to Map the todo.id in order to it is passed to the controller -->
		<form:hidden path="id" />

		<fieldset class="form-group">
			<form:label path="description">Description:</form:label>
			<form:input path="description" type="text" class="form-control"
				required="required" />
			<form:errors path="description" cssClass="text-warning" />
		</fieldset>

		<fieldset class="form-group">
			<form:label path="targetDate">Target Date:</form:label>
			<form:input path="targetDate" type="text" class="form-control"
				required="required" />
			<form:errors path="targetDate" cssClass="text-warning" />
		</fieldset>

		<button type="submit" class="btn btn-success">Add</button>
	</form:form>

</div>

<%@ include file="common/footer.jspf" %>