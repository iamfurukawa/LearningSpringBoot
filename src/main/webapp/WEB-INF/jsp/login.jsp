<%@ include file="commons/header.jspf" %>
<%@ include file="commons/navigation.jspf" %>
<div class="container">
	<font color="red">${errorMessage}</font>
	<form method="post" action="">
		<div>
			<fieldset class="form-group">
				<label>Name</label>
				<input type="text" name="name">
			</fieldset>
			<fieldset class="form-group">
			<label>Password</label>
			<input type="password" name="password">
			</fieldset>
			<input type="submit">
		</div>
	</form>
</div>
<%@ include file="commons/footer.jspf" %>