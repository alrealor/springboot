<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container" >
	<font color="red">${errorMessage}</font>
	<form method="post">
		Name: <input type="text" name="user" value="usuario" /> 
		Password: <input type="password" name="password" /> 
		<input type="submit" />
	</form>
</div>	

<%@ include file="common/footer.jspf"%>