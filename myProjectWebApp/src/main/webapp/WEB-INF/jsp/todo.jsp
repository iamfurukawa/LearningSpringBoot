<%@ include file="commons/header.jspf" %>
<%@ include file="commons/navigation.jspf" %>
<div class="container">
	<h1>Add/Edit a Todo</h1>
	<form:form method="post" modelAttribute="todo">
		<form:hidden path="id"/>	
		<fieldset class="form-group">
			<form:label path="desc">Description</form:label>
			<form:input path="desc" class="form-control" name="desc" type="text" required="required"/>
			<form:errors path="desc" cssClass="text-warning"></form:errors>
		</fieldset>
		<fieldset class="form-group">
			<form:label path="targetDate">Target Date</form:label>
			<form:input path="targetDate" class="form-control" name="desc" type="text" required="required"/>
			<form:errors path="targetDate" cssClass="text-warning"></form:errors>
		</fieldset> 
		<button class="btn btn-success" type="submit">Add </button>
	</form:form>
</div>
<%@ include file="commons/footer.jspf" %>