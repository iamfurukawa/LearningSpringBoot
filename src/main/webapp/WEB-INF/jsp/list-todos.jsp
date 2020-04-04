<%@ include file="commons/header.jspf" %>
<%@ include file="commons/navigation.jspf" %>
<div class="container">
	<h1>${name}'s Todo</h1>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>Description</th>
				<th>Target Date</th>
				<th>Is it Done?</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${todos}" var="todo">
			<tr>
				<td>${todo.desc}</td>
				<td><fmt:formatDate value="${todo.targetDate}" pattern="dd/MM/yyyy"/></td>
				<td>${todo.done}</td>
				<td><a class="btn btn-danger" href="/delete-todo?id=${todo.id} ">Delete</a></td>
				<td><a class="btn btn-warning" href="/update-todo?id=${todo.id} ">Update</a></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>
		<a class="button" href="/add-todos">Add a Todo</a>
	</div>
</div>
<%@ include file="commons/footer.jspf" %>